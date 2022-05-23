import unittest
from data import *
from utils import *
from prolog_ast.ast import *
from test.debug import TestDebug


class TestBase(unittest.TestCase, TestDebug):
    def setUp(self) -> None:
        super().setUp()
        self.a = TermVariable("a")
        self.b = TermVariable("b")
        self.c = TermVariable("c")
        self.p = TermPredicate(Predicate("p", [self.a, self.b]))

    def test_equ(self):
        equ: List[Equation] = []
        self.assertEqual(len(equ), 0)
        e1 = Equation(self.p, self.c)
        equ.append(e1)
        self.assertEqual(len(equ), 1)
        e2 = Equation(self.a, self.c)
        equ.append(e2)
        self.assertEqual(len(equ), 2)
        self.assertEqual(equ[0], e1)

        self.log("test equ")
        self.log(equ)

    def test_occur_check(self):
        self.assertEqual(OccurCheck(self.a, self.a), True)
        self.assertEqual(OccurCheck(self.c, self.a), False)
        self.assertEqual(OccurCheck(self.p, self.a), True)
        self.assertEqual(OccurCheck(self.p, self.c), False)

    def test_env(self):
        env: Environment = Environment()
        self.assertEqual(len(env.env), 0)
        self.assertEqual(env.add(self.c, self.p), True)
        self.assertEqual(env.add(self.a, self.b), True)
        with self.assertRaises(NotUnifiable):
            env.add(self.b, self.b)
        self.assertEqual(len(env.env), 2)

        env.add(self.a, self.b)
        self.assertEqual(len(env.env), 2)
        self.log("test_env:")
        self.log(env)


class TestSubst(unittest.TestCase, TestDebug):
    def setUp(self) -> None:
        super().setUp()
        self.env = Environment()
        self.z = TermVariable("Z")
        self.w = TermVariable("W")
        self.y = TermVariable("Y")
        self.env.add(self.z, TermPredicate(Predicate("f", [self.w])))
        self.env.add(self.w, self.y)

        self.t = TermPredicate(
            Predicate(
                "p",
                [
                    self.z,
                    TermPredicate(Predicate("h", [self.z, self.w])),
                    TermPredicate(Predicate("f", [self.w])),
                ],
            )
        )

    def test_subst(self):
        self.assertEqual(str(self.t), "p(Z, h(Z, W), f(W))")
        self.assertEqual(str(subst(self.t, self.env)), "p(f(Y), h(f(Y), Y), f(Y))")


class TestUnif(unittest.TestCase, TestDebug):
    def setUp(self) -> None:
        super().setUp()
        self.p1 = TermPredicate(
            Predicate(
                "p",
                [
                    TermVariable("z"),
                    TermPredicate(
                        Predicate("h", [TermVariable("z"), TermVariable("w")])
                    ),
                    TermPredicate(Predicate("f", [TermVariable("w")])),
                ],
            )
        )

        self.p2 = TermPredicate(
            Predicate(
                "p",
                [
                    TermPredicate(Predicate("f", [TermVariable("x")])),
                    TermPredicate(
                        Predicate(
                            "h",
                            [
                                TermVariable("y"),
                                TermPredicate(Predicate("f", [TermVariable("a")])),
                            ],
                        )
                    ),
                    TermVariable("y"),
                ],
            )
        )

        self.p3 = TermPredicate(
            Predicate(
                "q",
                [
                    TermPredicate(Predicate("f", [TermVariable("a")])),
                    TermPredicate(Predicate("g", [TermVariable("x")])),
                ],
            )
        )

        self.p4 = TermPredicate(Predicate("q", [TermVariable("y"), TermVariable("y")]))

    def test_unif(self):
        e = Equation(self.p1, self.p2)
        self.log(e)
        r = str(unif(Environment(), [e]))
        self.log(r)
        self.assertEqual(r, "{z: f(f(a)), y: f(f(a)), w: f(a), x: f(a)}")

        with self.assertRaises(NotUnifiable):
           unif(Environment(), [Equation(self.p3, self.p4)])
