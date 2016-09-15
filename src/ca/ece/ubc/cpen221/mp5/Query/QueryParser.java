// Generated from Query.g4 by ANTLR 4.4

package ca.ece.ubc.cpen221.mp5.Query;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IN=1, CATEGORY=2, RATING=3, PRICE=4, NAME=5, LPAREN=6, RPAREN=7, OR=8, 
		AND=9, STRING=10, NUMBER=11, TO=12, WHITESPACE=13;
	public static final String[] tokenNames = {
		"<INVALID>", "'in'", "'category'", "'rating'", "'price'", "'name'", "'('", 
		"')'", "'||'", "'&&'", "STRING", "NUMBER", "'..'", "WHITESPACE"
	};
	public static final int
		RULE_orExpr = 0, RULE_andExpr = 1, RULE_atom = 2, RULE_in = 3, RULE_category = 4, 
		RULE_name = 5, RULE_rating = 6, RULE_price = 7, RULE_range = 8, RULE_leftnum = 9, 
		RULE_rightnum = 10;
	public static final String[] ruleNames = {
		"orExpr", "andExpr", "atom", "in", "category", "name", "rating", "price", 
		"range", "leftnum", "rightnum"
	};

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public QueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class OrExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public TerminalNode EOF() { return getToken(QueryParser.EOF, 0); }
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(QueryParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(QueryParser.OR, i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); andExpr();
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(23); match(OR);
				setState(24); andExpr();
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30); match(EOF);
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

	public static class AndExprContext extends ParserRuleContext {
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public TerminalNode AND(int i) {
			return getToken(QueryParser.AND, i);
		}
		public List<TerminalNode> AND() { return getTokens(QueryParser.AND); }
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); atom();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(33); match(AND);
				setState(34); atom();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class AtomContext extends ParserRuleContext {
		public InContext in() {
			return getRuleContext(InContext.class,0);
		}
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public PriceContext price() {
			return getRuleContext(PriceContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public RatingContext rating() {
			return getRuleContext(RatingContext.class,0);
		}
		public CategoryContext category() {
			return getRuleContext(CategoryContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atom);
		try {
			setState(49);
			switch (_input.LA(1)) {
			case IN:
				enterOuterAlt(_localctx, 1);
				{
				setState(40); in();
				}
				break;
			case CATEGORY:
				enterOuterAlt(_localctx, 2);
				{
				setState(41); category();
				}
				break;
			case RATING:
				enterOuterAlt(_localctx, 3);
				{
				setState(42); rating();
				}
				break;
			case PRICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(43); price();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 5);
				{
				setState(44); name();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(45); match(LPAREN);
				setState(46); orExpr();
				setState(47); match(RPAREN);
				}
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

	public static class InContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public TerminalNode IN() { return getToken(QueryParser.IN, 0); }
		public InContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterIn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitIn(this);
		}
	}

	public final InContext in() throws RecognitionException {
		InContext _localctx = new InContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_in);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); match(IN);
			setState(52); match(LPAREN);
			setState(53); match(STRING);
			setState(54); match(RPAREN);
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

	public static class CategoryContext extends ParserRuleContext {
		public TerminalNode CATEGORY() { return getToken(QueryParser.CATEGORY, 0); }
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitCategory(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); match(CATEGORY);
			setState(57); match(LPAREN);
			setState(58); match(STRING);
			setState(59); match(RPAREN);
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

	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(QueryParser.NAME, 0); }
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode STRING() { return getToken(QueryParser.STRING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(NAME);
			setState(62); match(LPAREN);
			setState(63); match(STRING);
			setState(64); match(RPAREN);
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

	public static class RatingContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode RATING() { return getToken(QueryParser.RATING, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public RatingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterRating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitRating(this);
		}
	}

	public final RatingContext rating() throws RecognitionException {
		RatingContext _localctx = new RatingContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_rating);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(RATING);
			setState(67); match(LPAREN);
			setState(68); range();
			setState(69); match(RPAREN);
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

	public static class PriceContext extends ParserRuleContext {
		public TerminalNode PRICE() { return getToken(QueryParser.PRICE, 0); }
		public TerminalNode LPAREN() { return getToken(QueryParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(QueryParser.RPAREN, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public PriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_price; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterPrice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitPrice(this);
		}
	}

	public final PriceContext price() throws RecognitionException {
		PriceContext _localctx = new PriceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); match(PRICE);
			setState(72); match(LPAREN);
			setState(73); range();
			setState(74); match(RPAREN);
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

	public static class RangeContext extends ParserRuleContext {
		public LeftnumContext leftnum() {
			return getRuleContext(LeftnumContext.class,0);
		}
		public RightnumContext rightnum() {
			return getRuleContext(RightnumContext.class,0);
		}
		public TerminalNode TO() { return getToken(QueryParser.TO, 0); }
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitRange(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); leftnum();
			setState(77); match(TO);
			setState(78); rightnum();
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

	public static class LeftnumContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(QueryParser.NUMBER, 0); }
		public LeftnumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftnum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterLeftnum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitLeftnum(this);
		}
	}

	public final LeftnumContext leftnum() throws RecognitionException {
		LeftnumContext _localctx = new LeftnumContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_leftnum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(NUMBER);
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

	public static class RightnumContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(QueryParser.NUMBER, 0); }
		public RightnumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightnum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).enterRightnum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QueryListener ) ((QueryListener)listener).exitRightnum(this);
		}
	}

	public final RightnumContext rightnum() throws RecognitionException {
		RightnumContext _localctx = new RightnumContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_rightnum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(NUMBER);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17W\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\3\3\3\3\3\3\7\3"+
		"&\n\3\f\3\16\3)\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\64\n\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\2\2"+
		"\r\2\4\6\b\n\f\16\20\22\24\26\2\2R\2\30\3\2\2\2\4\"\3\2\2\2\6\63\3\2\2"+
		"\2\b\65\3\2\2\2\n:\3\2\2\2\f?\3\2\2\2\16D\3\2\2\2\20I\3\2\2\2\22N\3\2"+
		"\2\2\24R\3\2\2\2\26T\3\2\2\2\30\35\5\4\3\2\31\32\7\n\2\2\32\34\5\4\3\2"+
		"\33\31\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36 \3\2\2\2\37"+
		"\35\3\2\2\2 !\7\2\2\3!\3\3\2\2\2\"\'\5\6\4\2#$\7\13\2\2$&\5\6\4\2%#\3"+
		"\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2)\'\3\2\2\2*\64\5\b\5"+
		"\2+\64\5\n\6\2,\64\5\16\b\2-\64\5\20\t\2.\64\5\f\7\2/\60\7\b\2\2\60\61"+
		"\5\2\2\2\61\62\7\t\2\2\62\64\3\2\2\2\63*\3\2\2\2\63+\3\2\2\2\63,\3\2\2"+
		"\2\63-\3\2\2\2\63.\3\2\2\2\63/\3\2\2\2\64\7\3\2\2\2\65\66\7\3\2\2\66\67"+
		"\7\b\2\2\678\7\f\2\289\7\t\2\29\t\3\2\2\2:;\7\4\2\2;<\7\b\2\2<=\7\f\2"+
		"\2=>\7\t\2\2>\13\3\2\2\2?@\7\7\2\2@A\7\b\2\2AB\7\f\2\2BC\7\t\2\2C\r\3"+
		"\2\2\2DE\7\5\2\2EF\7\b\2\2FG\5\22\n\2GH\7\t\2\2H\17\3\2\2\2IJ\7\6\2\2"+
		"JK\7\b\2\2KL\5\22\n\2LM\7\t\2\2M\21\3\2\2\2NO\5\24\13\2OP\7\16\2\2PQ\5"+
		"\26\f\2Q\23\3\2\2\2RS\7\r\2\2S\25\3\2\2\2TU\7\r\2\2U\27\3\2\2\2\5\35\'"+
		"\63";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}