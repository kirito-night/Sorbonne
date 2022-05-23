import unittest
from interp_v3 import interprete4
from exc import NotUnifiable
from test.debug import TestDebug
from utils import solve2 , filter
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

        self.prog1 = parser.parseFile("../exemples/jalon4/test1.pl")
        self.prog2 = parser.parseFile("../exemples/jalon4/test2.pl")
        self.prog3 = parser.parseFile("../exemples/classification.pl")
        self.prog4 =  parser.parseFile("../exemples/jalon4/test3.pl")
        self.log("programme 1:", self.prog1, print_ast(self.prog1))

    def test_solve2(self):
        goals = []
        rules = []
        # separer les goals des assertions
        for i in self.prog1.decls:
            if isinstance(i, DeclGoal):
                goals.append(i)
            if isinstance(i, DeclAssertion):
                rules.append(i)
        # appeler solve sur les goals et les rules
        env = solve2(goals[0], rules, Environment())
        env = filter(env)
        self.log(str(env))
        # verifier que l'environnement retourné est correct
        self.assertEqual(str(env), "{X: a}")
        goals = []
        rules = []
        # separer les goals des assertions
        for i in self.prog2.decls:
            if isinstance(i, DeclGoal):
                goals.append(i)
            if isinstance(i, DeclAssertion):
                rules.append(i)
        # appeler solve sur les goals et les rules
        env = solve2(goals[0], rules, Environment())

        self.log(str(env))
        # verifier que l'environnement retourné est correct
        self.assertEqual(str(env), "{}")

    def testInterpret4(self):
        # test de l'interpretation du programme de test 1
        
        env = interprete4(self.prog1)
        env1 = filter(env)
        self.log(str(env1))
        self.assertEqual(str(env1), "{X: a}")

        env = interprete4(self.prog2)
        env1 = filter(env)
        self.log(str(env1))
        self.assertEqual(str(env1), "{}")


        
        env = interprete4(self.prog3)
        env2 = filter(env)
        self.log(env2)
        self.log(str(env2))
        self.assertEqual(str(env2), "{}") 

        with self.assertRaises(NotUnifiable):
            interprete4(self.prog4)