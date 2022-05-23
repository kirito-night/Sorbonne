# Generated from Prolog.g4 by ANTLR 4.9.2
# encoding: utf-8
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r")
        buf.write("B\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\3\2\7\2\f\n\2\f\2\16")
        buf.write("\2\17\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3\30\n\3\f\3")
        buf.write("\16\3\33\13\3\5\3\35\n\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3%")
        buf.write("\n\3\f\3\16\3(\13\3\3\3\3\3\5\3,\n\3\3\4\3\4\3\4\3\4\3")
        buf.write("\4\3\4\7\4\64\n\4\f\4\16\4\67\13\4\5\49\n\4\3\4\5\4<\n")
        buf.write("\4\3\5\3\5\5\5@\n\5\3\5\2\2\6\2\4\6\b\2\2\2F\2\r\3\2\2")
        buf.write("\2\4+\3\2\2\2\6;\3\2\2\2\b?\3\2\2\2\n\f\5\4\3\2\13\n\3")
        buf.write("\2\2\2\f\17\3\2\2\2\r\13\3\2\2\2\r\16\3\2\2\2\16\20\3")
        buf.write("\2\2\2\17\r\3\2\2\2\20\21\7\2\2\3\21\3\3\2\2\2\22\34\5")
        buf.write("\6\4\2\23\24\7\3\2\2\24\31\5\6\4\2\25\26\7\4\2\2\26\30")
        buf.write("\5\6\4\2\27\25\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31")
        buf.write("\32\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\34\23\3\2\2\2")
        buf.write("\34\35\3\2\2\2\35\36\3\2\2\2\36\37\7\5\2\2\37,\3\2\2\2")
        buf.write(" !\7\6\2\2!&\5\6\4\2\"#\7\4\2\2#%\5\6\4\2$\"\3\2\2\2%")
        buf.write("(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(&\3\2\2\2)*")
        buf.write("\7\5\2\2*,\3\2\2\2+\22\3\2\2\2+ \3\2\2\2,\5\3\2\2\2-<")
        buf.write("\7\t\2\2./\7\t\2\2/8\7\7\2\2\60\65\5\b\5\2\61\62\7\4\2")
        buf.write("\2\62\64\5\b\5\2\63\61\3\2\2\2\64\67\3\2\2\2\65\63\3\2")
        buf.write("\2\2\65\66\3\2\2\2\669\3\2\2\2\67\65\3\2\2\28\60\3\2\2")
        buf.write("\289\3\2\2\29:\3\2\2\2:<\7\b\2\2;-\3\2\2\2;.\3\2\2\2<")
        buf.write("\7\3\2\2\2=@\7\n\2\2>@\5\6\4\2?=\3\2\2\2?>\3\2\2\2@\t")
        buf.write("\3\2\2\2\13\r\31\34&+\658;?")
        return buf.getvalue()


class PrologParser ( Parser ):

    grammarFileName = "Prolog.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "':-'", "','", "'.'", "'?-'", "'('", "')'" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "ATOM", "VAR", 
                      "LINE_COMMENT", "COMMENT", "SPACE" ]

    RULE_program = 0
    RULE_clause = 1
    RULE_predicate = 2
    RULE_term = 3

    ruleNames =  [ "program", "clause", "predicate", "term" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    T__5=6
    ATOM=7
    VAR=8
    LINE_COMMENT=9
    COMMENT=10
    SPACE=11

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.9.2")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class ProgramContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser
            self._clause = None # ClauseContext
            self.clauses = list() # of ClauseContexts

        def EOF(self):
            return self.getToken(PrologParser.EOF, 0)

        def clause(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(PrologParser.ClauseContext)
            else:
                return self.getTypedRuleContext(PrologParser.ClauseContext,i)


        def getRuleIndex(self):
            return PrologParser.RULE_program

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterProgram" ):
                listener.enterProgram(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitProgram" ):
                listener.exitProgram(self)




    def program(self):

        localctx = PrologParser.ProgramContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_program)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 11
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while _la==PrologParser.T__3 or _la==PrologParser.ATOM:
                self.state = 8
                localctx._clause = self.clause()
                localctx.clauses.append(localctx._clause)
                self.state = 13
                self._errHandler.sync(self)
                _la = self._input.LA(1)

            self.state = 14
            self.match(PrologParser.EOF)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class ClauseContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return PrologParser.RULE_clause

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class GoalContext(ClauseContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a PrologParser.ClauseContext
            super().__init__(parser)
            self.begin = None # Token
            self._predicate = None # PredicateContext
            self.body = list() # of PredicateContexts
            self.copyFrom(ctx)

        def predicate(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(PrologParser.PredicateContext)
            else:
                return self.getTypedRuleContext(PrologParser.PredicateContext,i)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterGoal" ):
                listener.enterGoal(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitGoal" ):
                listener.exitGoal(self)


    class AssertionContext(ClauseContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a PrologParser.ClauseContext
            super().__init__(parser)
            self.head = None # PredicateContext
            self._predicate = None # PredicateContext
            self.body = list() # of PredicateContexts
            self.copyFrom(ctx)

        def predicate(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(PrologParser.PredicateContext)
            else:
                return self.getTypedRuleContext(PrologParser.PredicateContext,i)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAssertion" ):
                listener.enterAssertion(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAssertion" ):
                listener.exitAssertion(self)



    def clause(self):

        localctx = PrologParser.ClauseContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_clause)
        self._la = 0 # Token type
        try:
            self.state = 41
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [PrologParser.ATOM]:
                localctx = PrologParser.AssertionContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 16
                localctx.head = self.predicate()
                self.state = 26
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if _la==PrologParser.T__0:
                    self.state = 17
                    self.match(PrologParser.T__0)
                    self.state = 18
                    localctx._predicate = self.predicate()
                    localctx.body.append(localctx._predicate)
                    self.state = 23
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)
                    while _la==PrologParser.T__1:
                        self.state = 19
                        self.match(PrologParser.T__1)
                        self.state = 20
                        localctx._predicate = self.predicate()
                        localctx.body.append(localctx._predicate)
                        self.state = 25
                        self._errHandler.sync(self)
                        _la = self._input.LA(1)



                self.state = 28
                self.match(PrologParser.T__2)
                pass
            elif token in [PrologParser.T__3]:
                localctx = PrologParser.GoalContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 30
                localctx.begin = self.match(PrologParser.T__3)
                self.state = 31
                localctx._predicate = self.predicate()
                localctx.body.append(localctx._predicate)
                self.state = 36
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==PrologParser.T__1:
                    self.state = 32
                    self.match(PrologParser.T__1)
                    self.state = 33
                    localctx._predicate = self.predicate()
                    localctx.body.append(localctx._predicate)
                    self.state = 38
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                self.state = 39
                self.match(PrologParser.T__2)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class PredicateContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return PrologParser.RULE_predicate

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class StructureContext(PredicateContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a PrologParser.PredicateContext
            super().__init__(parser)
            self.atom = None # Token
            self._term = None # TermContext
            self.childs = list() # of TermContexts
            self.copyFrom(ctx)

        def ATOM(self):
            return self.getToken(PrologParser.ATOM, 0)
        def term(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(PrologParser.TermContext)
            else:
                return self.getTypedRuleContext(PrologParser.TermContext,i)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterStructure" ):
                listener.enterStructure(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitStructure" ):
                listener.exitStructure(self)


    class AtomContext(PredicateContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a PrologParser.PredicateContext
            super().__init__(parser)
            self.atom = None # Token
            self.copyFrom(ctx)

        def ATOM(self):
            return self.getToken(PrologParser.ATOM, 0)

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterAtom" ):
                listener.enterAtom(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitAtom" ):
                listener.exitAtom(self)



    def predicate(self):

        localctx = PrologParser.PredicateContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_predicate)
        self._la = 0 # Token type
        try:
            self.state = 57
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,7,self._ctx)
            if la_ == 1:
                localctx = PrologParser.AtomContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 43
                localctx.atom = self.match(PrologParser.ATOM)
                pass

            elif la_ == 2:
                localctx = PrologParser.StructureContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 44
                localctx.atom = self.match(PrologParser.ATOM)
                self.state = 45
                self.match(PrologParser.T__4)

                self.state = 54
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if _la==PrologParser.ATOM or _la==PrologParser.VAR:
                    self.state = 46
                    localctx._term = self.term()
                    localctx.childs.append(localctx._term)
                    self.state = 51
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)
                    while _la==PrologParser.T__1:
                        self.state = 47
                        self.match(PrologParser.T__1)
                        self.state = 48
                        localctx._term = self.term()
                        localctx.childs.append(localctx._term)
                        self.state = 53
                        self._errHandler.sync(self)
                        _la = self._input.LA(1)



                self.state = 56
                self.match(PrologParser.T__5)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class TermContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return PrologParser.RULE_term

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class VarContext(TermContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a PrologParser.TermContext
            super().__init__(parser)
            self.var = None # Token
            self.copyFrom(ctx)

        def VAR(self):
            return self.getToken(PrologParser.VAR, 0)

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterVar" ):
                listener.enterVar(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitVar" ):
                listener.exitVar(self)


    class PredContext(TermContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a PrologParser.TermContext
            super().__init__(parser)
            self.pred = None # PredicateContext
            self.copyFrom(ctx)

        def predicate(self):
            return self.getTypedRuleContext(PrologParser.PredicateContext,0)


        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterPred" ):
                listener.enterPred(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitPred" ):
                listener.exitPred(self)



    def term(self):

        localctx = PrologParser.TermContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_term)
        try:
            self.state = 61
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [PrologParser.VAR]:
                localctx = PrologParser.VarContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 59
                localctx.var = self.match(PrologParser.VAR)
                pass
            elif token in [PrologParser.ATOM]:
                localctx = PrologParser.PredContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 60
                localctx.pred = self.predicate()
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





