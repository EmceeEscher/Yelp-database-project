// Generated from Query.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.Query;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IN=1, CATEGORY=2, RATING=3, PRICE=4, NAME=5, LPAREN=6, RPAREN=7, OR=8, 
		AND=9, STRING=10, NUMBER=11, TO=12, WHITESPACE=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'"
	};
	public static final String[] ruleNames = {
		"IN", "CATEGORY", "RATING", "PRICE", "NAME", "LPAREN", "RPAREN", "OR", 
		"AND", "STRING", "NUMBER", "TO", "WHITESPACE"
	};


	    // This method makes the lexer or parser stop running if it encounters
	    // invalid input and throw a RuntimeException.
	    public void reportErrorsAsExceptions() {
	        //removeErrorListeners();
	        
	        addErrorListener(new ExceptionThrowingErrorListener());
	    }
	    
	    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
	        @Override
	        public void syntaxError(Recognizer<?, ?> recognizer,
	                Object offendingSymbol, int line, int charPositionInLine,
	                String msg, RecognitionException e) {
	            throw new RuntimeException(msg);
	        }
	    }


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17Z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\7\13H\n\13"+
		"\f\13\16\13K\13\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\6\16U\n\16\r\16"+
		"\16\16V\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\3\2\5\3\2\"\u0080\3\2\63\67\5\2\13\f\17\17\"\"[\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5 \3\2\2\2\7)\3\2\2\2\t\60\3\2\2\2"+
		"\13\66\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23B\3\2\2\2\25E\3\2"+
		"\2\2\27N\3\2\2\2\31P\3\2\2\2\33T\3\2\2\2\35\36\7k\2\2\36\37\7p\2\2\37"+
		"\4\3\2\2\2 !\7e\2\2!\"\7c\2\2\"#\7v\2\2#$\7g\2\2$%\7i\2\2%&\7q\2\2&\'"+
		"\7t\2\2\'(\7{\2\2(\6\3\2\2\2)*\7t\2\2*+\7c\2\2+,\7v\2\2,-\7k\2\2-.\7p"+
		"\2\2./\7i\2\2/\b\3\2\2\2\60\61\7r\2\2\61\62\7t\2\2\62\63\7k\2\2\63\64"+
		"\7e\2\2\64\65\7g\2\2\65\n\3\2\2\2\66\67\7p\2\2\678\7c\2\289\7o\2\29:\7"+
		"g\2\2:\f\3\2\2\2;<\7*\2\2<\16\3\2\2\2=>\7+\2\2>\20\3\2\2\2?@\7~\2\2@A"+
		"\7~\2\2A\22\3\2\2\2BC\7(\2\2CD\7(\2\2D\24\3\2\2\2EI\7$\2\2FH\t\2\2\2G"+
		"F\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7$\2\2M"+
		"\26\3\2\2\2NO\t\3\2\2O\30\3\2\2\2PQ\7\60\2\2QR\7\60\2\2R\32\3\2\2\2SU"+
		"\t\4\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\b\16\2\2"+
		"Y\34\3\2\2\2\5\2IV\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}