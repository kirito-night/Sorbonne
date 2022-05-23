from prolog_ast.ast import (
    Program,
    Decl,
    DeclAssertion,
    Predicate,
    Term,
    TermPredicate,
    TermVariable,
)


def print_ast(ast: Program) -> str:
    """Fonction permettant d'afficher un AST sous forme d'arbre

    Args:
        ast: un AST (retourné par prolog_parser)

    Returns:
        la représentation textuelle de cet AST

    """
    ret = "Program:\n"
    for i in ast.decls:
        ret += print_line(1, i, print_decl(i, 1))

    return ret


def print_decl(decl: Decl, level: int = 0) -> str:
    ret = ""
    if isinstance(decl, DeclAssertion):
        ret += print_line(level + 1, decl.head, print_pred(decl.head, level + 1))

    for i in decl.preds:
        ret += print_line(level + 1, i, print_pred(i, level + 1))

    return ret + "\n"


def print_pred(pred: Predicate, level: int = 0) -> str:
    ret = ""
    for i in pred.args:
        ret += print_term(i, level)

    return ret


def print_term(term: Term, level: int = 0) -> str:
    if isinstance(term, TermPredicate):
        return print_line(level + 1, term, print_pred(term.pred, level + 1))
    elif isinstance(term, TermVariable):
        return print_line(level + 1, term)


def print_line(level: int, cls, data="") -> str:
    return "\t" * level + cls.__class__.__name__ + ": " + str(cls) + "\n" + data
