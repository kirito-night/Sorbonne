from data import Environment, Equation, OccurCheck
from prolog_ast.ast import (
    Term,
    TermVariable,
    TermPredicate,
    Predicate,
    DeclAssertion,
    DeclGoal,
)
from exc import NotUnifiable
from typing import List, Tuple
import copy


def subst(term: Term, env: Environment) -> Term:
    """Fonction de substitution
    Effectue toutes les substitutions possible pour un terme associé a un environnement

    Args:
        term: le terme dans lequel on souhaite faire des substitutions
        env: l'environnement associé

    Returns:
        le terme avec les substitutions effectuées
    """
    for k in env.keys():
        term = replace(term, k, env.get(k))
    for k in env.keys():
        term = replace(term, k, env.get(k))
    return term


# remplace old par new dans term
def replace(term: Term, old: Term, new: Term) -> Term:
    """Fonction utilitaire permettant de remplacer un terme par un autre dans un terme

    Args:
        term: le terme dans lequel remplacer des éléments
        old: le terme a remplacer
        new: le nouveau terme remplacant l'ancien

    Returns:
        le terme avec les replacements effectués

    """
    new = copy.copy(new)
    term = copy.copy(term)
    if isinstance(term, TermVariable):
        return new if term == old else term
    elif isinstance(term, TermPredicate):
        for i in range(len(term.pred.args)):
            term.pred.args[i] = replace(term.pred.args[i], old, new)
        return term


def unif(env: Environment, equ: List[Equation], first=True) -> Environment:
    """Fonction d'unification

    Args:
        env: l'environnement de base
        equ: une liste d'equations a resoudre
        first: booléen premettant de différencier un premier appel d'un appel récursif

    Returns:
        un environnement permettant de resoudre le systeme d'equations

    Raises:
        NotUnifiable: si il est impossible de résoudre le systeme d'equations

    """

    for i in range(len(equ)):

        t1 = subst(equ[i].term1, env)  # effectuer les substitution  possible,
        t2 = subst(equ[i].term2, env)
        if isinstance(t1, TermVariable) and isinstance(
            t2, TermVariable
        ):  # si on a des variables a gauche et a droite
            if t1 == t2:  # si egal on efface
                pass
                # del equ[i]
            else:
                env.add(t1, t2)  # sinon on ajoute dans l'environnement
        elif isinstance(t1, TermVariable) and isinstance(
            t2, TermPredicate
        ):  # ajout dans l'environnement
            env.add(t1, t2)
        elif isinstance(t1, TermPredicate) and isinstance(
            t2, TermVariable
        ):  # ajout dans l'environnment et orientant
            env.add(t2, t1)
        elif isinstance(t1, TermPredicate) and isinstance(
            t2, TermPredicate
        ):  # si 2 cote des predicats
            if t1.pred.symbol == t2.pred.symbol:
                equations = []  # liste d'equation a resoudre avec ajout par boucle
                for j in range(min(len(t1.pred.args), len(t2.pred.args))):
                    equations.append(Equation(t1.pred.args[j], t2.pred.args[j]))
                env.update(unif(env, equations, False))  # appel recursif
            else:
                raise NotUnifiable

    if first:
        # first est utilisé ici pour différencier le premier appel de la fonction, d'un appel recursif
        # si c'est le premier appel, toutes les recursions possibles ont deja ete effectuées, on a donc le resultat final
        # on peut donc verifier si l'environnement permet de resoudre le systeme d'equation
        subst(equ[i].term1, env)
        subst(equ[i].term2, env)

        keys = env.keys()

        for k, v in env.env.items():
            # pas de variable a droite
            if isinstance(v, TermVariable):
                # raise NotUnifiable()
                pass
            else:
                # pas de clé dans les predicats a droite
                for k2 in keys:
                    if OccurCheck(v, k2):
                        raise NotUnifiable()

    return env


def rename(n: int, res, first=True):
    """Fonction de renommage
    Ajoute la valeur de n en suffixe a toutes les noms de variables

    Args:
        n: la valeur a ajouter en suffixe
        res: l'element dans lequel rechercher des variables
        first: argument permettant de distinguer un premier appel d'un appel recursif

    Returns:
        l'element passé en argument (r) avec les noms de variables remplacés
    """
    if first:
        res = copy.deepcopy(res)  # ajout d'un deep copy apres avoir lue les feed back

    if isinstance(res, DeclAssertion):
        res.head = rename(n, res.head, False)
        for i in range(len(res.preds)):
            res.preds[i] = rename(n, res.preds[i], False)
    elif isinstance(res, Predicate):
        for i in range(len(res.args)):
            res.args[i] = rename(n, res.args[i], False)
    elif isinstance(res, TermPredicate):
        for i in range(len(res.pred.args)):
            res.pred.args[i] = rename(n, res.pred.args[i], False)
    elif isinstance(res, TermVariable):
        res = TermVariable(res.name + str(n))
    return res


def choose(n: int, env: Environment, goal: Predicate, rules) -> Tuple[Environment, list]:
    """Fonction permettant de trouver des regles et un environnement permettant d'unifier un goal

    Args:
        n: la valeur du compteur (pour le renommage)
        env: l'environnement a utiliser
        goal: le goal a unifier
        rules: une liste de regles

    Returns:
        env: l'environnement
        body: liste de regles
    """
    body = None

    regles = copy.copy(rules)
    cpt = 0
    for i in regles:
        try:
            if isinstance(i, DeclAssertion):
                r = rename(n, i)
                env = unif(env, [Equation(TermPredicate(r.head), TermPredicate(goal))])

                body = r.preds
        except NotUnifiable:
            cpt += 1
            continue

    if cpt == len(regles):
        raise NotUnifiable()

    return env, body


def solve(buts: DeclGoal, rules: list) -> Environment:
    """Fonction permettant de prouver successivement chaque but de la liste goals à l'aide d'une suite de regles rules

    Args:
        goals: une liste de buts a prouver
        rules: une liste de regles

    Returns:
        env: un environnement permettant de verifier tous les buts de goals

    Raises:
        NotUnifiable: si aucun environnement ne permet de verifier tous les buts

    """
    env = Environment()
    goals = buts.preds
    i = 0
    while goals != []:
        old = len(goals)
        but = goals[0]
        env, body = choose(i, env, but, rules)
        i += 1
        goals.extend(body)
        del goals[0]
        if old == len(goals):
            raise NotUnifiable()

    return env


class Contexte:
    """
    Structure de donne arbonrescent permettant de faire le backtracking
    
    Attributes:
        goals: une liste de buts a prouver
        rules: une liste de regles
        env: Environnement a utiliser
        Lcontext : liste de contexte fils du noeud
        choix : regle choisie
    """

    def __init__(self, goals: DeclGoal, rules: list, env: Environment):
        """
        Constructeur de la classe contexte

        Args:
            goals: une liste de buts a prouver
            rules: une liste de regles
            env: Environnement a utiliser
        """
        self.goals = copy.copy(goals)
        self.rules = copy.copy(rules)
        self.env = copy.copy(env)
        self.Lcontexte: List[Contexte] = []
        self.choix = None

    def backtrack(self, numbut, goal: Predicate) -> Tuple[Environment, list]:
        """
        Fonction permettant de faire l'unification avec choose
        Args:
            numbut: numero servant pour renomer les variables des regles, incremente enfonction des recursion
            goal: but
        Returns:
            env: l'environnement
            body: liste de regles
        """
        tmp_env, body = choose(numbut, self.env, goal, self.choix)
        return tmp_env, body


def solve2(
    goals: DeclGoal,
    rules: list,
    env: Environment,
    numbut=0,
    trace=False,
    listDejafait=[],
) -> Environment:
    """Fonction permettant de prouver successivement chaque but de la liste goals à l'aide d'une suite de regles rules et de faire du backtracking en cas d'erreur
    Args:
        goals: une liste de buts a prouver
        rules: une liste de regles
        env: Environnement a utiliser
        numbut: numero servant pour renomer les variables des regles, incremente enfonction des recursion
        trace: si le mode trace doit etre active

    Returns:
        env: un environnement permettant de verifier tous les buts de goals
    Raises:
        NotUnifiable: si aucun environnement ne permet de verifier tous les buts
    """

    # noeud a chaque recursion
    racine = Contexte(goals, rules, env)
    # liste des buts a resoudre
    buts = copy.copy(racine.goals.preds)
    cpt = 0

    res = None

    if buts == []:
        # environnment trouver

        res = racine.env

        # code qui nous permet de sauter resultat deja trouver afin de trouver le Next
        for t in listDejafait:

            if str(res) == str(t):
                if trace:
                    print("revient sur l'ancient resultat")
                res = None
                # exception de resulta deja trouer

                raise NotUnifiable()

        return res

    # code qui nous permet de sauter resultat deja trouver afin de trouver le Next , en enlevant l'ancient resultat dans l'env
    tmp_list = [filter(i) for i in listDejafait]

    for e in tmp_list:

        for k, v in e.items():
            if k in env.env and v == env.env[k]:
                env.delete(k)
    # tant que la liste des buts n'est pas vide
    while buts != []:
        for i in range(len(racine.rules)):  # pour chaque regle

            try:
                racine.choix = [racine.rules[i]]  # regle i comme choix
                tmp_env, body = racine.backtrack(
                    numbut, buts[0]
                )  # effectue l'unification avec choose
                tmp_buts = copy.copy(buts)  # nouveau but
                del tmp_buts[0]
                for b in body:
                    tmp_buts.insert(
                        0, b
                    )  # insertion en tete , des nouveau buts a resoudre
                fils = Contexte(DeclGoal(tmp_buts), rules, tmp_env)
                if trace:
                    print(
                        f"trace: nouveau contexte\n\t rules: {rules}\n\t goals: {DeclGoal(tmp_buts)}\n"
                    )

                racine.Lcontexte.append(fils)  # ajout dans les fils

            except NotUnifiable:  # si execption non unifiable
                cpt += 1  # increment d'un compteur et on regarde le regle prochain
                continue
        del buts[0]  # supprime le but courrent

        # increment de facteur de recursion qui permet de faire le rennomage
        numbut += 1
        if cpt == len(rules):
            if trace:
                print("trace: pas de solution trouvée")
            # aucun regle permet l'unification

            raise NotUnifiable()

        check = 0

        for fils in racine.Lcontexte:  # parcours de l'arbre recursivement
            try:
                cpt += 1
                if trace:
                    print("trace: backtracking")
                res = solve2(
                    fils.goals, fils.rules, fils.env, numbut, trace, listDejafait
                )
                if res != None:  # si un resultat trouver
                    break

            except NotUnifiable:  # si fils non unifiable et on regarde le fils prochain
                check += 1
                continue
        if (
            check == len(racine.Lcontexte) and res == None
        ):  # si aucun fils est unifiable
            # aucun fils unifible"

            raise NotUnifiable()

        if res != None:

            break
    if trace:
        print("trace: solution trouvée")
    return res


def filter(env: Environment) -> Environment:
    """Fonction permettant de filter les variables dans l'environnement pour ne garder que celles qui ne sont pas prefixées afin de garder ques les variables des buts
    Args:
        env: un environnement
    Returns:
        Environment
    """
    ret = {}
    for key, val in env.env.items():
        if len(key.name) == 1:
            ret[key] = val
    return ret