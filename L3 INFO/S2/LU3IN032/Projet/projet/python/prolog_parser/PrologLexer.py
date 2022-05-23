# Generated from Prolog.g4 by ANTLR 4.9.2
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
    from typing import TextIO
else:
    from typing.io import TextIO



def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\r")
        buf.write("U\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7")
        buf.write("\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\3\2\3\2\3\2")
        buf.write("\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\7")
        buf.write("\b*\n\b\f\b\16\b-\13\b\3\t\3\t\7\t\61\n\t\f\t\16\t\64")
        buf.write("\13\t\3\n\3\n\7\n8\n\n\f\n\16\n;\13\n\3\n\3\n\3\13\3\13")
        buf.write("\3\13\3\13\3\13\3\13\7\13E\n\13\f\13\16\13H\13\13\3\13")
        buf.write("\3\13\3\13\3\13\3\13\3\f\6\fP\n\f\r\f\16\fQ\3\f\3\f\2")
        buf.write("\2\r\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27")
        buf.write("\r\3\2\t\3\2c|\6\2\62;C\\aac|\3\2C\\\4\2\f\f\17\17\3\2")
        buf.write("\61\61\3\2,,\5\2\13\f\17\17\"\"\2Z\2\3\3\2\2\2\2\5\3\2")
        buf.write("\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2")
        buf.write("\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2")
        buf.write("\27\3\2\2\2\3\31\3\2\2\2\5\34\3\2\2\2\7\36\3\2\2\2\t ")
        buf.write("\3\2\2\2\13#\3\2\2\2\r%\3\2\2\2\17\'\3\2\2\2\21.\3\2\2")
        buf.write("\2\23\65\3\2\2\2\25>\3\2\2\2\27O\3\2\2\2\31\32\7<\2\2")
        buf.write("\32\33\7/\2\2\33\4\3\2\2\2\34\35\7.\2\2\35\6\3\2\2\2\36")
        buf.write("\37\7\60\2\2\37\b\3\2\2\2 !\7A\2\2!\"\7/\2\2\"\n\3\2\2")
        buf.write("\2#$\7*\2\2$\f\3\2\2\2%&\7+\2\2&\16\3\2\2\2\'+\t\2\2\2")
        buf.write("(*\t\3\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\20")
        buf.write("\3\2\2\2-+\3\2\2\2.\62\t\4\2\2/\61\t\3\2\2\60/\3\2\2\2")
        buf.write("\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\22\3\2\2")
        buf.write("\2\64\62\3\2\2\2\659\7\'\2\2\668\n\5\2\2\67\66\3\2\2\2")
        buf.write("8;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<")
        buf.write("=\b\n\2\2=\24\3\2\2\2>?\7\61\2\2?@\7,\2\2@F\3\2\2\2AB")
        buf.write("\7,\2\2BE\n\6\2\2CE\n\7\2\2DA\3\2\2\2DC\3\2\2\2EH\3\2")
        buf.write("\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7,\2\2")
        buf.write("JK\7\61\2\2KL\3\2\2\2LM\b\13\2\2M\26\3\2\2\2NP\t\b\2\2")
        buf.write("ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\b")
        buf.write("\f\2\2T\30\3\2\2\2\t\2+\629DFQ\3\b\2\2")
        return buf.getvalue()


class PrologLexer(Lexer):

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    T__0 = 1
    T__1 = 2
    T__2 = 3
    T__3 = 4
    T__4 = 5
    T__5 = 6
    ATOM = 7
    VAR = 8
    LINE_COMMENT = 9
    COMMENT = 10
    SPACE = 11

    channelNames = [ u"DEFAULT_TOKEN_CHANNEL", u"HIDDEN" ]

    modeNames = [ "DEFAULT_MODE" ]

    literalNames = [ "<INVALID>",
            "':-'", "','", "'.'", "'?-'", "'('", "')'" ]

    symbolicNames = [ "<INVALID>",
            "ATOM", "VAR", "LINE_COMMENT", "COMMENT", "SPACE" ]

    ruleNames = [ "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "ATOM", 
                  "VAR", "LINE_COMMENT", "COMMENT", "SPACE" ]

    grammarFileName = "Prolog.g4"

    def __init__(self, input=None, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.9.2")
        self._interp = LexerATNSimulator(self, self.atn, self.decisionsToDFA, PredictionContextCache())
        self._actions = None
        self._predicates = None


