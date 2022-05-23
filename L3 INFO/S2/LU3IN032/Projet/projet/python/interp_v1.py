from data import Equation, Environment, TermPredicate
from utils import unif
from prolog_ast.ast import Program, DeclAssertion, DeclGoal
from exc import NotUnifiable


def interprete0(ast: Program) -> Environment:
    """Premiere version de l'interpreteur
    Permet d'unifier un seul fait avec un seul but
    Si plusieurs sont présents, le premier sera sélectionné et les autres seront ignorés

    Args:
        ast: un AST contenant au moins une assertion et un but
    Returns:
        l'environnement unifiant la permiere assertion avec le premier but
    """
    terms = [None, None]
    for i in ast.decls:
        if isinstance(i, DeclAssertion) and terms[0] is None:
            terms[0] = TermPredicate(i.head)
        elif isinstance(i, DeclGoal) and terms[1] is None:
            terms[1] = TermPredicate(i.preds[0])

        if terms[0] is not None and terms[1] is not None:
            break
    return unif(Environment(), [Equation(*terms)])


def interprete1(ast: Program) -> Environment:
    """Seconde version de l'interpreteur
    Permet d'unifier un seul fait avec un seul but
    Si plusieurs faits sont présents, le premier fait ayant un symbole similaire au but sera sélectionné

    Args:
        ast: un AST contenant au moins une assertion et un but
    Returns:
        l'environnement unifiant la permiere assertion ayant un symbole similaire au but, avec le premier but

    Raises:
        NotUnifiable: si aucun fait n'a de symbole correspondant au but
    """

    goal = None
    fact = None
    for i in ast.decls:
        if isinstance(i, DeclGoal):
            goal = TermPredicate(i.preds[0])
            break

    for i in ast.decls:
        if isinstance(i, DeclAssertion):
            fact = TermPredicate(i.head)
            if fact.pred.symbol == goal.pred.symbol:
                return unif(Environment(), [Equation(goal, fact)])

    raise NotUnifiable("aucun fait n'a de symbole correspondant au but")


def interprete2(ast: Program) -> Environment:
    """Troisieme version de l'interpreteur
    Permet d'unifier plusieurs faits avec plusieurs buts

    Args:
        ast: un AST contenant au moins une assertion et un but
    Returns:
        l'environnement unifiant la permiere assertion ayant un symbole similaire au but, avec le premier but

    """
    equations = []
    goals = None
    rules = []
    for i in ast.decls:
        if isinstance(i, DeclAssertion):
            rules.append(i)
        if isinstance(i, DeclGoal):
            goals = i
    for i in goals.preds:
        for j in rules:
            if i.symbol == j.head.symbol:
                equations.append(Equation(TermPredicate(i), TermPredicate(j.head)))

    return unif(Environment(), equations)
