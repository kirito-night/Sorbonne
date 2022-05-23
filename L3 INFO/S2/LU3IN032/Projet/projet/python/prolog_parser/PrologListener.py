# Generated from Prolog.g4 by ANTLR 4.9.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .PrologParser import PrologParser
else:
    from PrologParser import PrologParser

# This class defines a complete listener for a parse tree produced by PrologParser.
class PrologListener(ParseTreeListener):

    # Enter a parse tree produced by PrologParser#program.
    def enterProgram(self, ctx:PrologParser.ProgramContext):
        pass

    # Exit a parse tree produced by PrologParser#program.
    def exitProgram(self, ctx:PrologParser.ProgramContext):
        pass


    # Enter a parse tree produced by PrologParser#Assertion.
    def enterAssertion(self, ctx:PrologParser.AssertionContext):
        pass

    # Exit a parse tree produced by PrologParser#Assertion.
    def exitAssertion(self, ctx:PrologParser.AssertionContext):
        pass


    # Enter a parse tree produced by PrologParser#Goal.
    def enterGoal(self, ctx:PrologParser.GoalContext):
        pass

    # Exit a parse tree produced by PrologParser#Goal.
    def exitGoal(self, ctx:PrologParser.GoalContext):
        pass


    # Enter a parse tree produced by PrologParser#Atom.
    def enterAtom(self, ctx:PrologParser.AtomContext):
        pass

    # Exit a parse tree produced by PrologParser#Atom.
    def exitAtom(self, ctx:PrologParser.AtomContext):
        pass


    # Enter a parse tree produced by PrologParser#Structure.
    def enterStructure(self, ctx:PrologParser.StructureContext):
        pass

    # Exit a parse tree produced by PrologParser#Structure.
    def exitStructure(self, ctx:PrologParser.StructureContext):
        pass


    # Enter a parse tree produced by PrologParser#Var.
    def enterVar(self, ctx:PrologParser.VarContext):
        pass

    # Exit a parse tree produced by PrologParser#Var.
    def exitVar(self, ctx:PrologParser.VarContext):
        pass


    # Enter a parse tree produced by PrologParser#Pred.
    def enterPred(self, ctx:PrologParser.PredContext):
        pass

    # Exit a parse tree produced by PrologParser#Pred.
    def exitPred(self, ctx:PrologParser.PredContext):
        pass



del PrologParser