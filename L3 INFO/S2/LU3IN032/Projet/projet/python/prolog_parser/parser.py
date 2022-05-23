# PCOMP (LU3IN032), Licence 3, Sorbonne Université
# année 2021-2022
#
# Projet Prolog
#

# Utilitaires pour lire un programme texte depuis un fichier ou une chaîne,
# appeler le parser et retourner l'AST correspondant.

from antlr4 import FileStream, InputStream, CommonTokenStream, ParseTreeWalker
from prolog_parser.PrologLexer import PrologLexer
from prolog_parser.PrologParser import PrologParser
from prolog_parser.PrologListener import PrologListener
from prolog_parser.listener import Listener

def parse(stream):
    lexer = PrologLexer(stream)
    stream = CommonTokenStream(lexer)
    parser = PrologParser(stream)
    tree = parser.program()
    listener = Listener()
    walker = ParseTreeWalker()
    walker.walk(listener, tree)
    return tree.node

def parseString(s):
    return parse(InputStream(s))

def parseFile(filename):
    return parse(FileStream(filename, encoding='utf-8'))
