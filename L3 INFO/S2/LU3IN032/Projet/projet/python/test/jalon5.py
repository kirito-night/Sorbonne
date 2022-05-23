import unittest
from interp_v3 import interprete4
from exc import NotUnifiable
from test.debug import TestDebug
from utils import filter
from prolog_parser import parser
from print_ast import print_ast
from data import Environment
from prolog_ast.ast import (
    TermVariable,
    TermPredicate,
    Predicate,
    DeclGoal,
    DeclAssertion,
)
class TestBase(unittest.TestCase, TestDebug):
    def setUp(self) -> None:
        super().setUp()
        a = TermVariable("a")
        b = TermVariable("b")
        c = TermVariable("c")
        p1 = Predicate("p", [a, b])
        p2 = TermPredicate(Predicate("q", [b]))
        p3 = Predicate("r", [p2, a])
        self.assertion = DeclAssertion(head=a, preds=[b, p1, p3])

        self.prog1 = parser.parseFile("../exemples/jalon5/test1.pl")
        self.log("programme 1:", self.prog1, print_ast(self.prog1))
    
    # def __init__(self) : 
    #     self.prog1 = parser.parseFile("../exemples/jalon5/test1.pl")
    #     self.log("programme 1:", self.prog1, print_ast(self.prog1)) 

    def testInterpret4(self):
        # test de l'interpretation du programme de test 1
        #trouver la premiere  solution
        env0 = interprete4(self.prog1)
        env1 = filter(env0)
        self.log(str(env1))
        self.assertEqual(str(env1), "{X: a}")

        #trouver la 2eme solution        
        env3 = interprete4(self.prog1, False , [env0])
        env2 = filter(env3)
        self.log(str(env2))
        self.assertEqual(str(env2), "{X: b}")


    

        