import unittest
from interp_v2 import interprete3
from test.debug import TestDebug
from utils import rename, choose, solve
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

        self.prog1 = parser.parseFile("../exemples/jalon3/test1.pl")
        self.prog2 = parser.parseFile("../exemples/jalon3/test2.pl")
        self.log("programme 1:", self.prog1, print_ast(self.prog1))

    def test_rename(self):
        self.assertEqual(self.assertion.head.name, "a")
        self.assertEqual(self.assertion.preds[0].name, "b")
        self.assertEqual(self.assertion.preds[1].args[0].name, "a")
        assertion = rename(1, self.assertion)
        self.assertEqual(assertion.head.name, "a1")
        self.assertEqual(assertion.preds[0].name, "b1")

        self.assertEqual(assertion.preds[1].args[0].name, "a1")
        self.log(self.assertion)
        self.log(assertion)
        self.log("test rename")
        self.log(self.assertion)

    def test_choose(self):
        goal = None
        regles =[]
        for i in self.prog1.decls:
            if isinstance(i, DeclGoal):
                goal = i
            if isinstance(i, DeclAssertion):
                regles.append(i)
        

        env, rules = choose(i, Environment(), goal.preds[0], regles)
        self.assertEqual(str(rules), "[r(X ?- p(f(X), h(Y, a), Y), q(a)., XX ?- p(f(X), h(Y, a), Y), q(a).), q(XX ?- p(f(X), h(Y, a), Y), q(a).)]")
        self.assertEqual(str(env), "{Z ?- p(f(X), h(Y, a), Y), q(a).: f(a), Y: f(a), W ?- p(f(X), h(Y, a), Y), q(a).: a, X: a}")
        self.log("test de choose :", str(env), str(rules))

    def test_solve(self):
        goals = []
        rules = []
        # separer les goals des assertions
        for i in self.prog2.decls:
            if isinstance(i, DeclGoal):
                goals.append(i)
            if isinstance(i, DeclAssertion):
                rules.append(i)
        # appeler solve sur les goals et les rules
        env = solve(goals[0], rules)
        self.log(str(env))
        # verifier que l'environnement retourné est correct
        self.assertEqual(str(env), "{Z0: f(f(a)), Y: f(f(a)), W0: f(a), X: f(a), Z1: f(W), X2: f(f(a)), Y2: f(f(a)), Z2: f(f(a))}")

    def testInterpret3(self):
        # test de l'interpretation du programme de test 1
        env = interprete3(self.prog2)
        #print(str(env))
        
        self.log(str(env))
        self.assertEqual(str(env), "{Z0: f(f(a)), Y: f(f(a)), W0: f(a), X: f(a), Z1: f(W), X2: f(f(a)), Y2: f(f(a)), Z2: f(f(a))}")