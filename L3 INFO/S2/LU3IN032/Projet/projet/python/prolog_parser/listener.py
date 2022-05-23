# PCOMP (LU3IN032), Licence 3, Sorbonne Université
# année 2021-2022
#
# Projet Prolog
#

# Listener passé au parseur ANTLR 4 pour construire l'AST.

from prolog_parser.PrologListener import PrologListener
from prolog_ast import ast

class Listener(PrologListener):

    def exitProgram(self, ctx):
        l = map(lambda x: x.node, ctx.clauses)
        ctx.node = ast.Program(list(l))

    def exitAssertion(self, ctx):
        l = map(lambda x: x.node, ctx.body)
        pos = ast.Pos(ctx.head.atom.line, ctx.head.atom.column)
        ctx.node = ast.DeclAssertion(ctx.head.node, list(l), pos)

    def exitGoal(self, ctx):
        l = map(lambda x: x.node, ctx.body)
        pos = ast.Pos(ctx.begin.line, ctx.begin.column)
        ctx.node = ast.DeclGoal(list(l), pos)

    def exitAtom(self, ctx):
        pos = ast.Pos(ctx.atom.line, ctx.atom.column)
        ctx.node = ast.Predicate(ctx.getText(), [], pos)

    def exitStructure(self, ctx):
        l = map(lambda x: x.node, ctx.childs)
        pos = ast.Pos(ctx.atom.line, ctx.atom.column)
        ctx.node = ast.Predicate(ctx.atom.text, list(l), pos)

    def exitVar(self, ctx):
        pos = ast.Pos(ctx.var.line, ctx.var.column)
        ctx.node = ast.TermVariable(ctx.getText(), pos)

    def exitPred(self, ctx):
        pos = ast.Pos(ctx.pred.atom.line, ctx.pred.atom.column)
        ctx.node = ast.TermPredicate(ctx.pred.node, pos)
