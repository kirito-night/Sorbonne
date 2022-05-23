from data import Environment
from utils import solve2
from prolog_ast.ast import Program, DeclAssertion, DeclGoal


def interprete4(ast: Program, trace=False, listDejaFait=[]) -> Environment:
    """quatrième version de l'interpreteur
    Permet d'interpreter un programme avec plusieurs buts

    Args:
        ast: un AST contenant un ou plusieurs but et une seule règle par symbole de prédica
        trace: si le mode trace doit etre active pour la fonction solve2
    Returns:
        l'environnement qui contient la solution de ce prolog
    """
    goals = []
    rules = []

    for i in ast.decls:
        if isinstance(i, DeclGoal):
            goals.append(i)
        if isinstance(i, DeclAssertion):
            rules.append(i)     
    return solve2(goals[0], rules, Environment(), 0, trace, listDejaFait)
