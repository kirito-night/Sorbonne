from prolog_ast.ast import (
    Term,
    TermVariable,
    TermPredicate
)
from typing import Dict, List
from exc import NotUnifiable


def OccurCheck(t: Term, v: TermVariable) -> bool:
    """Fonction permettant de tester l'occurence d'une variable dans un terme

    Args:
        t: le term dans lequel on veut verifier la presence d'une variable
        v: la variable dont on souhaite verifier la presence

    Returns:
        True si la variable est présente dans le terme

    """
    # on pourrait également utiliser du pattern-matching
    # mais cette fonctionnalité n'est disponible que pour python >= 3.10
    if isinstance(t, TermVariable):
        return t == v
    elif isinstance(t, TermPredicate):
        for i in t.pred.args:
            if OccurCheck(i, v):
                return True
    return False


# on utilise une liste pour représenter les différentes equations
# cela permet un ajout et une supression rapide et efficace de ces equations
# bien qu'elles puissent etre représentées par des simple tuples, nous avons choisi
# de representer les equations sous forme de classe pour une meilleure évolutivité
class Equation:
    """Classe représentant une équation

    Attributes:
        term1: terme a gauche de l'equation
        term2: terme a droite de l'equation
    """

    def __init__(self, t1: Term, t2: Term):
        """Constructeur de la classe
        Verifie le type des termes

        Args:
            t1: terme a gauche de l'equation
            t2: terme a droite de l'equation

        Raises:
            TypeError: si les valeurs passées ne sont pas des termes valides

        """
        if not isinstance(t1, Term):
            raise TypeError("t1 is not an instance of Term")
        if not isinstance(t2, Term):
            raise TypeError("t2 is not an instance of Term")
        self.term1 = t1
        self.term2 = t2

    def __str__(self) -> str:
        return str(self.term1) + " = " + str(self.term2)

    def __repr__(self):
        return self.__str__()


# l'environnement est représenté par une classe
# cela permet d'effectuer des tests additionnels avant d'ajouter des elements dans le dictionnaire
class Environment:
    """Classe représentant un environnement
    Cette classe stocke les données dans un dictionnaire mais permet des tests additionnels notamment pour l'ajout d'elements

    Attributes:
        env: Dictionnaire représentant l'environnement et associant des TermVariable a des Terms
    """

    def __init__(self) -> None:
        # on utilise un dictionnaire (hashmap) pour associer une variable
        # a un terme, c'est une structure idéale pour associer et retrouver rapidement des valeurs
        self.env: Dict[TermVariable, Term] = {}

    def add(self, var: TermVariable, value: Term) -> bool:
        """Methode d'ajout d'une variable a l'environnement

        Args:
            var: la variable a ajouter
            value: le terme représentant la valeur de cette variable

        Returns:
            True si la variable a bien été ajoutée (le test d'occurence est vérifié)
        """
        if not OccurCheck(value, var):
            self.env[var] = value
            return True
        # return False
        raise NotUnifiable()

    def get(self, var: TermVariable) -> Term:
        return self.env[var]

    def delete(self, var: TermVariable):
        del self.env[var]

    def keys(self) -> List[TermVariable]:
        return self.env.keys()

    def update(self, env):
        for k, v in env.env.items():
            self.env[k] = v

    def __str__(self) -> str:
        return str(self.env)
    
        
