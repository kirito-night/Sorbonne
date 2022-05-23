// Generated from PrologANTLRGrammar.g4 by ANTLR 4.4

package pcomp.prolog.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrologANTLRGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__5=1, T__4=2, T__3=3, T__2=4, T__1=5, T__0=6, ATOM=7, VAR=8, LINE_COMMENT=9, 
		COMMENT=10, SPACE=11;
	public static final String[] tokenNames = {
		"<INVALID>", "':-'", "'('", "')'", "','", "'?-'", "'.'", "ATOM", "VAR", 
		"LINE_COMMENT", "COMMENT", "SPACE"
	};
	public static final int
		RULE_program = 0, RULE_clause = 1, RULE_predicate = 2, RULE_term = 3;
	public static final String[] ruleNames = {
		"program", "clause", "predicate", "term"
	};

	@Override
	public String getGrammarFileName() { return "PrologANTLRGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrologANTLRGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public pcomp.prolog.ast.Program node;
		public ClauseContext clause;
		public List<ClauseContext> clauses = new ArrayList<ClauseContext>();
		public TerminalNode EOF() { return getToken(PrologANTLRGrammarParser.EOF, 0); }
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==ATOM) {
				{
				{
				setState(8); ((ProgramContext)_localctx).clause = clause();
				((ProgramContext)_localctx).clauses.add(((ProgramContext)_localctx).clause);
				}
				}
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(14); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClauseContext extends ParserRuleContext {
		public pcomp.prolog.ast.Decl node;
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
	 
		public ClauseContext() { }
		public void copyFrom(ClauseContext ctx) {
			super.copyFrom(ctx);
			this.node = ctx.node;
		}
	}
	public static class GoalContext extends ClauseContext {
		public Token start;
		public PredicateContext predicate;
		public List<PredicateContext> body = new ArrayList<PredicateContext>();
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public GoalContext(ClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).enterGoal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).exitGoal(this);
		}
	}
	public static class AssertionContext extends ClauseContext {
		public PredicateContext head;
		public PredicateContext predicate;
		public List<PredicateContext> body = new ArrayList<PredicateContext>();
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public AssertionContext(ClauseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).enterAssertion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).exitAssertion(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clause);
		int _la;
		try {
			setState(41);
			switch (_input.LA(1)) {
			case ATOM:
				_localctx = new AssertionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(16); ((AssertionContext)_localctx).head = predicate();
				setState(26);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(17); match(T__5);
					setState(18); ((AssertionContext)_localctx).predicate = predicate();
					((AssertionContext)_localctx).body.add(((AssertionContext)_localctx).predicate);
					setState(23);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(19); match(T__2);
						setState(20); ((AssertionContext)_localctx).predicate = predicate();
						((AssertionContext)_localctx).body.add(((AssertionContext)_localctx).predicate);
						}
						}
						setState(25);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(28); match(T__0);
				}
				break;
			case T__1:
				_localctx = new GoalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(30); ((GoalContext)_localctx).start = match(T__1);
				setState(31); ((GoalContext)_localctx).predicate = predicate();
				((GoalContext)_localctx).body.add(((GoalContext)_localctx).predicate);
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(32); match(T__2);
					setState(33); ((GoalContext)_localctx).predicate = predicate();
					((GoalContext)_localctx).body.add(((GoalContext)_localctx).predicate);
					}
					}
					setState(38);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(39); match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public pcomp.prolog.ast.Predicate node;
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
	 
		public PredicateContext() { }
		public void copyFrom(PredicateContext ctx) {
			super.copyFrom(ctx);
			this.node = ctx.node;
		}
	}
	public static class StructureContext extends PredicateContext {
		public Token atom;
		public TermContext term;
		public List<TermContext> childs = new ArrayList<TermContext>();
		public TerminalNode ATOM() { return getToken(PrologANTLRGrammarParser.ATOM, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public StructureContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).enterStructure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).exitStructure(this);
		}
	}
	public static class AtomContext extends PredicateContext {
		public Token atom;
		public TerminalNode ATOM() { return getToken(PrologANTLRGrammarParser.ATOM, 0); }
		public AtomContext(PredicateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).exitAtom(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_predicate);
		int _la;
		try {
			setState(57);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new AtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(43); ((AtomContext)_localctx).atom = match(ATOM);
				}
				break;
			case 2:
				_localctx = new StructureContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(44); ((StructureContext)_localctx).atom = match(ATOM);
				setState(45); match(T__4);
				{
				setState(54);
				_la = _input.LA(1);
				if (_la==ATOM || _la==VAR) {
					{
					setState(46); ((StructureContext)_localctx).term = term();
					((StructureContext)_localctx).childs.add(((StructureContext)_localctx).term);
					setState(51);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(47); match(T__2);
						setState(48); ((StructureContext)_localctx).term = term();
						((StructureContext)_localctx).childs.add(((StructureContext)_localctx).term);
						}
						}
						setState(53);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				}
				setState(56); match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public pcomp.prolog.ast.Term node;
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	 
		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
			this.node = ctx.node;
		}
	}
	public static class VarContext extends TermContext {
		public Token var;
		public TerminalNode VAR() { return getToken(PrologANTLRGrammarParser.VAR, 0); }
		public VarContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).exitVar(this);
		}
	}
	public static class PredContext extends TermContext {
		public PredicateContext pred;
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public PredContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).enterPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PrologANTLRGrammarListener ) ((PrologANTLRGrammarListener)listener).exitPred(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_term);
		try {
			setState(61);
			switch (_input.LA(1)) {
			case VAR:
				_localctx = new VarContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(59); ((VarContext)_localctx).var = match(VAR);
				}
				break;
			case ATOM:
				_localctx = new PredContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(60); ((PredContext)_localctx).pred = predicate();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\rB\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\7\2\f\n\2\f\2\16\2\17\13\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\7\3\30\n\3\f\3\16\3\33\13\3\5\3\35\n\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\7\3%\n\3\f\3\16\3(\13\3\3\3\3\3\5\3,\n\3\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4\64\n\4\f\4\16\4\67\13\4\5\49\n\4\3\4\5\4<\n\4\3\5\3\5\5\5@\n\5\3\5"+
		"\2\2\6\2\4\6\b\2\2F\2\r\3\2\2\2\4+\3\2\2\2\6;\3\2\2\2\b?\3\2\2\2\n\f\5"+
		"\4\3\2\13\n\3\2\2\2\f\17\3\2\2\2\r\13\3\2\2\2\r\16\3\2\2\2\16\20\3\2\2"+
		"\2\17\r\3\2\2\2\20\21\7\2\2\3\21\3\3\2\2\2\22\34\5\6\4\2\23\24\7\3\2\2"+
		"\24\31\5\6\4\2\25\26\7\6\2\2\26\30\5\6\4\2\27\25\3\2\2\2\30\33\3\2\2\2"+
		"\31\27\3\2\2\2\31\32\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\34\23\3\2\2\2"+
		"\34\35\3\2\2\2\35\36\3\2\2\2\36\37\7\b\2\2\37,\3\2\2\2 !\7\7\2\2!&\5\6"+
		"\4\2\"#\7\6\2\2#%\5\6\4\2$\"\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'"+
		")\3\2\2\2(&\3\2\2\2)*\7\b\2\2*,\3\2\2\2+\22\3\2\2\2+ \3\2\2\2,\5\3\2\2"+
		"\2-<\7\t\2\2./\7\t\2\2/8\7\4\2\2\60\65\5\b\5\2\61\62\7\6\2\2\62\64\5\b"+
		"\5\2\63\61\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\669\3\2\2"+
		"\2\67\65\3\2\2\28\60\3\2\2\289\3\2\2\29:\3\2\2\2:<\7\5\2\2;-\3\2\2\2;"+
		".\3\2\2\2<\7\3\2\2\2=@\7\n\2\2>@\5\6\4\2?=\3\2\2\2?>\3\2\2\2@\t\3\2\2"+
		"\2\13\r\31\34&+\658;?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}