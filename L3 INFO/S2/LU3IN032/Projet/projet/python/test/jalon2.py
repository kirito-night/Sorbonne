import unittest
from prolog_parser import parser
from print_ast import print_ast
from test.debug import TestDebug
from interp_v1 import interprete0, interprete1, interprete2
from exc import NotUnifiable

class TestInterp(unittest.TestCase, TestDebug):
    def setUp(self) -> None:
        super().setUp()
        self.prog1 = parser.parseFile('../exemples/jalon2/test1.pl')
        self.log("programme 1:", self.prog1, print_ast(self.prog1))

        self.prog2 = parser.parseFile('../exemples/jalon2/test2.pl')
        self.log("programme 2:", self.prog1, print_ast(self.prog2))

        self.prog3 = parser.parseFile('../exemples/jalon2/test3.pl')
        self.log("programme 3:", self.prog1, print_ast(self.prog3))

        self.prog4 = parser.parseFile('../exemples/jalon2/test4.pl')
        self.log("programme 4:", self.prog1, print_ast(self.prog4))

        self.prog5 = parser.parseFile('../exemples/jalon2/test5.pl')
        self.log("programme 5:", self.prog1, print_ast(self.prog5))

    def testInterp0(self):
        self.assertEqual(str(interprete0(self.prog1)), "{Z: f(f(a)), Y: f(f(a)), W: f(a), X: f(a)}")
        self.assertEqual(str(interprete0(self.prog2)), "{A: Z, X: f(Z), U: g(Y)}")
        

    def testInterp1(self):        
        self.assertEqual(str(interprete1(self.prog1)), "{Z: f(f(a)), Y: f(f(a)), W: f(a), X: f(a)}")
        self.assertEqual(str(interprete1(self.prog3)), "{Z: f(f(a)), Y: f(f(a)), W: f(a), X: f(a)}")
        self.assertEqual(str(interprete1(self.prog4)), "{Z: f(f(a)), Y: f(f(a)), W: f(a), X: f(a)}")

    def testInterp2(self):

        self.assertEqual(str(interprete2(self.prog1)), "{Z: f(f(a)), Y: f(f(a)), W: f(a), X: f(a)}")
        self.assertEqual(str(interprete2(self.prog3)), "{Z: f(f(a)), Y: f(f(a)), W: f(a), X: f(a)}")
        with self.assertRaises(NotUnifiable):
            interprete2(self.prog5)