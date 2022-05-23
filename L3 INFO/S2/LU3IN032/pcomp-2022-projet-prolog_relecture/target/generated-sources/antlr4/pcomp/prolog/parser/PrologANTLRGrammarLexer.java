// Generated from PrologANTLRGrammar.g4 by ANTLR 4.4

package pcomp.prolog.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrologANTLRGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__5=1, T__4=2, T__3=3, T__2=4, T__1=5, T__0=6, ATOM=7, VAR=8, LINE_COMMENT=9, 
		COMMENT=10, SPACE=11;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'"
	};
	public static final String[] ruleNames = {
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "ATOM", "VAR", "LINE_COMMENT", 
		"COMMENT", "SPACE"
	};


	public PrologANTLRGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PrologANTLRGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\rU\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\7\b*\n\b\f\b\16\b-\13\b\3\t\3\t\7\t\61\n\t\f\t\16\t\64\13\t\3\n"+
		"\3\n\7\n8\n\n\f\n\16\n;\13\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\7\13"+
		"E\n\13\f\13\16\13H\13\13\3\13\3\13\3\13\3\13\3\13\3\f\6\fP\n\f\r\f\16"+
		"\fQ\3\f\3\f\2\2\r\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\3"+
		"\2\t\3\2c|\6\2\62;C\\aac|\3\2C\\\4\2\f\f\17\17\3\2\61\61\3\2,,\5\2\13"+
		"\f\17\17\"\"Z\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\3\31\3\2\2\2\5\34\3\2\2\2\7\36\3\2\2\2\t \3\2\2\2\13\"\3\2"+
		"\2\2\r%\3\2\2\2\17\'\3\2\2\2\21.\3\2\2\2\23\65\3\2\2\2\25>\3\2\2\2\27"+
		"O\3\2\2\2\31\32\7<\2\2\32\33\7/\2\2\33\4\3\2\2\2\34\35\7*\2\2\35\6\3\2"+
		"\2\2\36\37\7+\2\2\37\b\3\2\2\2 !\7.\2\2!\n\3\2\2\2\"#\7A\2\2#$\7/\2\2"+
		"$\f\3\2\2\2%&\7\60\2\2&\16\3\2\2\2\'+\t\2\2\2(*\t\3\2\2)(\3\2\2\2*-\3"+
		"\2\2\2+)\3\2\2\2+,\3\2\2\2,\20\3\2\2\2-+\3\2\2\2.\62\t\4\2\2/\61\t\3\2"+
		"\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\22\3\2\2\2"+
		"\64\62\3\2\2\2\659\7\'\2\2\668\n\5\2\2\67\66\3\2\2\28;\3\2\2\29\67\3\2"+
		"\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\b\n\2\2=\24\3\2\2\2>?\7\61\2\2?@"+
		"\7,\2\2@F\3\2\2\2AB\7,\2\2BE\n\6\2\2CE\n\7\2\2DA\3\2\2\2DC\3\2\2\2EH\3"+
		"\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7,\2\2JK\7\61\2\2KL\3"+
		"\2\2\2LM\b\13\2\2M\26\3\2\2\2NP\t\b\2\2ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2"+
		"QR\3\2\2\2RS\3\2\2\2ST\b\f\2\2T\30\3\2\2\2\t\2+\629DFQ\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}