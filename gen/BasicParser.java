// Generated from /Users/blancatebar/Documents/SecondYear/WACC/wacc_25/antlr_config/BasicParser.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DBL_QUOTES=1, QUOTE=2, COMMA=3, PLUS=4, MINUS=5, MULT=6, DIVIDE=7, MOD=8, 
		LESS=9, LESS_EQ=10, GREAT=11, GREAT_EQ=12, EQ=13, NOTEQ=14, AND=15, OR=16, 
		ZERO=17, B=18, T=19, SKIP_FUNC=20, BEGIN=21, END=22, WHILE=23, DO=24, 
		PRINT=25, READ=26, FREE=27, RETURN=28, EXIT=29, PRINTLN=30, IF=31, ELSE=32, 
		THEN=33, FI=34, NEWPAIR=35, FST=36, SND=37, PAIR=38, LEN=39, ORD=40, CHR=41, 
		EXCL=42, OPEN_PARENTHESES=43, CLOSE_PARENTHESES=44, OPEN_SQR_BRACKET=45, 
		CLOSE_SQR_BRACKET=46, UNDERSCORE=47, SEMICOLON=48, BACKSLASH=49, HASH=50, 
		NULL=51, INT=52, BOOL=53, CHAR=54, STRING=55, TRUE=56, FALSE=57, INTEGER=58, 
		IDENT_TAIL=59, IDENT=60, WHITESPACE=61, DIV=62, IS=63, SKIP=64, EQUAL=65, 
		DONE=66, CALL=67, N=68, F=69, R=70, EOL=71;
	public static final int
		RULE_binaryOper = 0, RULE_unaryOper = 1, RULE_strLiter = 2, RULE_charLiter = 3, 
		RULE_argList = 4, RULE_paramList = 5, RULE_param = 6, RULE_func = 7, RULE_stat = 8, 
		RULE_assignLHS = 9, RULE_assignRHS = 10, RULE_arrayElem = 11, RULE_pairElem = 12, 
		RULE_type = 13, RULE_baseType = 14, RULE_pairELemType = 15, RULE_pairType = 16, 
		RULE_expr = 17, RULE_bool = 18, RULE_intSign = 19, RULE_intLiter = 20, 
		RULE_arrayLiter = 21, RULE_character = 22, RULE_escChar = 23, RULE_pairLiter = 24, 
		RULE_comment = 25, RULE_prog = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"binaryOper", "unaryOper", "strLiter", "charLiter", "argList", "paramList", 
			"param", "func", "stat", "assignLHS", "assignRHS", "arrayElem", "pairElem", 
			"type", "baseType", "pairELemType", "pairType", "expr", "bool", "intSign", 
			"intLiter", "arrayLiter", "character", "escChar", "pairLiter", "comment", 
			"prog"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\"'", "'''", "','", "'+'", "'-'", "'*'", "'/'", "'%'", "'<'", 
			"'<='", "'>'", "'>='", "'=='", "'!='", "'&&'", "'||'", "'0'", "'b'", 
			"'t'", "'skip'", "'begin'", "'end'", "'while'", "'do'", "'print'", "'read'", 
			"'free'", "'return'", "'exit'", "'println'", "'if'", "'else'", "'then'", 
			"'fi'", "'newpair'", "'fst'", "'snd'", "'pair'", "'len'", "'ord'", "'chr'", 
			"'!'", "'('", "')'", "'['", "']'", "'_'", "';'", "'\\'", "'#'", "'null'", 
			"'int'", "'bool'", "'char'", "'string'", "'true'", "'false'", null, null, 
			null, "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DBL_QUOTES", "QUOTE", "COMMA", "PLUS", "MINUS", "MULT", "DIVIDE", 
			"MOD", "LESS", "LESS_EQ", "GREAT", "GREAT_EQ", "EQ", "NOTEQ", "AND", 
			"OR", "ZERO", "B", "T", "SKIP_FUNC", "BEGIN", "END", "WHILE", "DO", "PRINT", 
			"READ", "FREE", "RETURN", "EXIT", "PRINTLN", "IF", "ELSE", "THEN", "FI", 
			"NEWPAIR", "FST", "SND", "PAIR", "LEN", "ORD", "CHR", "EXCL", "OPEN_PARENTHESES", 
			"CLOSE_PARENTHESES", "OPEN_SQR_BRACKET", "CLOSE_SQR_BRACKET", "UNDERSCORE", 
			"SEMICOLON", "BACKSLASH", "HASH", "NULL", "INT", "BOOL", "CHAR", "STRING", 
			"TRUE", "FALSE", "INTEGER", "IDENT_TAIL", "IDENT", "WHITESPACE", "DIV", 
			"IS", "SKIP", "EQUAL", "DONE", "CALL", "N", "F", "R", "EOL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "BasicParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class BinaryOperContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode MOD() { return getToken(BasicParser.MOD, 0); }
		public TerminalNode MULT() { return getToken(BasicParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(BasicParser.DIV, 0); }
		public TerminalNode GREAT() { return getToken(BasicParser.GREAT, 0); }
		public TerminalNode GREAT_EQ() { return getToken(BasicParser.GREAT_EQ, 0); }
		public TerminalNode LESS() { return getToken(BasicParser.LESS, 0); }
		public TerminalNode LESS_EQ() { return getToken(BasicParser.LESS_EQ, 0); }
		public TerminalNode EQ() { return getToken(BasicParser.EQ, 0); }
		public TerminalNode NOTEQ() { return getToken(BasicParser.NOTEQ, 0); }
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public BinaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBinaryOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBinaryOper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperContext binaryOper() throws RecognitionException {
		BinaryOperContext _localctx = new BinaryOperContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_binaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << MULT) | (1L << MOD) | (1L << LESS) | (1L << LESS_EQ) | (1L << GREAT) | (1L << GREAT_EQ) | (1L << EQ) | (1L << NOTEQ) | (1L << AND) | (1L << OR) | (1L << DIV))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class UnaryOperContext extends ParserRuleContext {
		public TerminalNode LEN() { return getToken(BasicParser.LEN, 0); }
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
		public TerminalNode EXCL() { return getToken(BasicParser.EXCL, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public UnaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterUnaryOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitUnaryOper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperContext unaryOper() throws RecognitionException {
		UnaryOperContext _localctx = new UnaryOperContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << EXCL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class StrLiterContext extends ParserRuleContext {
		public List<TerminalNode> DBL_QUOTES() { return getTokens(BasicParser.DBL_QUOTES); }
		public TerminalNode DBL_QUOTES(int i) {
			return getToken(BasicParser.DBL_QUOTES, i);
		}
		public List<CharacterContext> character() {
			return getRuleContexts(CharacterContext.class);
		}
		public CharacterContext character(int i) {
			return getRuleContext(CharacterContext.class,i);
		}
		public StrLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strLiter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStrLiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStrLiter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStrLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrLiterContext strLiter() throws RecognitionException {
		StrLiterContext _localctx = new StrLiterContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_strLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(DBL_QUOTES);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << PLUS) | (1L << MINUS) | (1L << MULT) | (1L << DIVIDE) | (1L << MOD) | (1L << LESS) | (1L << LESS_EQ) | (1L << GREAT) | (1L << GREAT_EQ) | (1L << EQ) | (1L << NOTEQ) | (1L << AND) | (1L << OR) | (1L << ZERO) | (1L << B) | (1L << T) | (1L << SKIP_FUNC) | (1L << BEGIN) | (1L << END) | (1L << WHILE) | (1L << DO) | (1L << PRINT) | (1L << READ) | (1L << FREE) | (1L << RETURN) | (1L << EXIT) | (1L << PRINTLN) | (1L << IF) | (1L << ELSE) | (1L << THEN) | (1L << FI) | (1L << NEWPAIR) | (1L << FST) | (1L << SND) | (1L << PAIR) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << EXCL) | (1L << OPEN_PARENTHESES) | (1L << CLOSE_PARENTHESES) | (1L << OPEN_SQR_BRACKET) | (1L << CLOSE_SQR_BRACKET) | (1L << UNDERSCORE) | (1L << SEMICOLON) | (1L << BACKSLASH) | (1L << HASH) | (1L << NULL) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << IDENT_TAIL) | (1L << IDENT) | (1L << WHITESPACE) | (1L << DIV) | (1L << IS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SKIP - 64)) | (1L << (EQUAL - 64)) | (1L << (DONE - 64)) | (1L << (CALL - 64)) | (1L << (N - 64)) | (1L << (F - 64)) | (1L << (R - 64)) | (1L << (EOL - 64)))) != 0)) {
				{
				{
				setState(59);
				character();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(DBL_QUOTES);
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

	public static class CharLiterContext extends ParserRuleContext {
		public List<TerminalNode> QUOTE() { return getTokens(BasicParser.QUOTE); }
		public TerminalNode QUOTE(int i) {
			return getToken(BasicParser.QUOTE, i);
		}
		public CharacterContext character() {
			return getRuleContext(CharacterContext.class,0);
		}
		public CharLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charLiter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterCharLiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitCharLiter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitCharLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharLiterContext charLiter() throws RecognitionException {
		CharLiterContext _localctx = new CharLiterContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_charLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(QUOTE);
			setState(68);
			character();
			setState(69);
			match(QUOTE);
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

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			expr(0);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(72);
				match(COMMA);
				setState(73);
				expr(0);
				}
				}
				setState(78);
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

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			param();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(80);
				match(COMMA);
				setState(81);
				param();
				}
				}
				setState(86);
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

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			type(0);
			setState(88);
			match(IDENT);
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

	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			type(0);
			setState(91);
			match(IDENT);
			setState(92);
			match(OPEN_PARENTHESES);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) {
				{
				setState(93);
				paramList();
				}
			}

			setState(96);
			match(CLOSE_PARENTHESES);
			setState(97);
			match(IS);
			setState(98);
			stat(0);
			setState(99);
			match(END);
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

	public static class StatContext extends ParserRuleContext {
		public TerminalNode SKIP() { return getToken(BasicParser.SKIP, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode EQUAL() { return getToken(BasicParser.EQUAL, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public TerminalNode PRINTLN() { return getToken(BasicParser.PRINTLN, 0); }
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(BasicParser.FI, 0); }
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public TerminalNode DONE() { return getToken(BasicParser.DONE, 0); }
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode SEMICOLON() { return getToken(BasicParser.SEMICOLON, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKIP:
				{
				setState(102);
				match(SKIP);
				}
				break;
			case PAIR:
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(103);
				type(0);
				setState(104);
				match(IDENT);
				setState(105);
				match(EQUAL);
				setState(106);
				assignRHS();
				}
				break;
			case FST:
			case SND:
			case IDENT:
				{
				setState(108);
				assignLHS();
				setState(109);
				match(EQUAL);
				setState(110);
				assignRHS();
				}
				break;
			case READ:
				{
				setState(112);
				match(READ);
				setState(113);
				assignLHS();
				}
				break;
			case FREE:
				{
				setState(114);
				match(FREE);
				setState(115);
				expr(0);
				}
				break;
			case RETURN:
				{
				setState(116);
				match(RETURN);
				setState(117);
				expr(0);
				}
				break;
			case EXIT:
				{
				setState(118);
				match(EXIT);
				setState(119);
				expr(0);
				}
				break;
			case PRINT:
				{
				setState(120);
				match(PRINT);
				setState(121);
				expr(0);
				}
				break;
			case PRINTLN:
				{
				setState(122);
				match(PRINTLN);
				setState(123);
				expr(0);
				}
				break;
			case IF:
				{
				setState(124);
				match(IF);
				setState(125);
				expr(0);
				setState(126);
				match(THEN);
				setState(127);
				stat(0);
				setState(128);
				match(ELSE);
				setState(129);
				stat(0);
				setState(130);
				match(FI);
				}
				break;
			case WHILE:
				{
				setState(132);
				match(WHILE);
				setState(133);
				expr(0);
				setState(134);
				match(DO);
				setState(135);
				stat(0);
				setState(136);
				match(DONE);
				}
				break;
			case BEGIN:
				{
				setState(138);
				match(BEGIN);
				setState(139);
				stat(0);
				setState(140);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(144);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(145);
					match(SEMICOLON);
					setState(146);
					stat(2);
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLHSContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLHS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignLHS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignLHS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignLHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLHSContext assignLHS() throws RecognitionException {
		AssignLHSContext _localctx = new AssignLHSContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignLHS);
		try {
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(154);
				pairElem();
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

	public static class AssignRHSContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public TerminalNode NEWPAIR() { return getToken(BasicParser.NEWPAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignRHS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignRHS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignRHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assignRHS);
		int _la;
		try {
			setState(174);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DBL_QUOTES:
			case QUOTE:
			case PLUS:
			case MINUS:
			case LEN:
			case ORD:
			case CHR:
			case EXCL:
			case OPEN_PARENTHESES:
			case NULL:
			case TRUE:
			case FALSE:
			case INTEGER:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				expr(0);
				}
				break;
			case OPEN_SQR_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				arrayLiter();
				}
				break;
			case NEWPAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				match(NEWPAIR);
				setState(160);
				match(OPEN_PARENTHESES);
				setState(161);
				expr(0);
				setState(162);
				match(COMMA);
				setState(163);
				expr(0);
				setState(164);
				match(CLOSE_PARENTHESES);
				}
				break;
			case FST:
			case SND:
				enterOuterAlt(_localctx, 4);
				{
				setState(166);
				pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				match(CALL);
				setState(168);
				match(IDENT);
				setState(169);
				match(OPEN_PARENTHESES);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DBL_QUOTES) | (1L << QUOTE) | (1L << PLUS) | (1L << MINUS) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << EXCL) | (1L << OPEN_PARENTHESES) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << IDENT))) != 0)) {
					{
					setState(170);
					argList();
					}
				}

				setState(173);
				match(CLOSE_PARENTHESES);
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

	public static class ArrayElemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public List<TerminalNode> OPEN_SQR_BRACKET() { return getTokens(BasicParser.OPEN_SQR_BRACKET); }
		public TerminalNode OPEN_SQR_BRACKET(int i) {
			return getToken(BasicParser.OPEN_SQR_BRACKET, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_SQR_BRACKET() { return getTokens(BasicParser.CLOSE_SQR_BRACKET); }
		public TerminalNode CLOSE_SQR_BRACKET(int i) {
			return getToken(BasicParser.CLOSE_SQR_BRACKET, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArrayElem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArrayElem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(IDENT);
			setState(181); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(177);
					match(OPEN_SQR_BRACKET);
					setState(178);
					expr(0);
					setState(179);
					match(CLOSE_SQR_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(183); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class PairElemContext extends ParserRuleContext {
		public TerminalNode FST() { return getToken(BasicParser.FST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(BasicParser.SND, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairElem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairElem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pairElem);
		try {
			setState(189);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(FST);
				setState(186);
				expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(SND);
				setState(188);
				expr(0);
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

	public static class TypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_SQR_BRACKET() { return getToken(BasicParser.OPEN_SQR_BRACKET, 0); }
		public TerminalNode CLOSE_SQR_BRACKET() { return getToken(BasicParser.CLOSE_SQR_BRACKET, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				setState(192);
				baseType();
				}
				break;
			case PAIR:
				{
				setState(193);
				pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(201);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(196);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(197);
					match(OPEN_SQR_BRACKET);
					setState(198);
					match(CLOSE_SQR_BRACKET);
					}
					} 
				}
				setState(203);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BasicParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(BasicParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(BasicParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBaseType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class PairELemTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public PairELemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairELemType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairELemType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairELemType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairELemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairELemTypeContext pairELemType() throws RecognitionException {
		PairELemTypeContext _localctx = new PairELemTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pairELemType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			baseType();
			setState(207);
			type(0);
			setState(208);
			match(PAIR);
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

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<PairELemTypeContext> pairELemType() {
			return getRuleContexts(PairELemTypeContext.class);
		}
		public PairELemTypeContext pairELemType(int i) {
			return getRuleContext(PairELemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(PAIR);
			setState(211);
			match(OPEN_PARENTHESES);
			setState(212);
			pairELemType();
			setState(213);
			match(COMMA);
			setState(214);
			pairELemType();
			setState(215);
			match(CLOSE_PARENTHESES);
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

	public static class ExprContext extends ParserRuleContext {
		public IntLiterContext intLiter() {
			return getRuleContext(IntLiterContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public CharLiterContext charLiter() {
			return getRuleContext(CharLiterContext.class,0);
		}
		public StrLiterContext strLiter() {
			return getRuleContext(StrLiterContext.class,0);
		}
		public PairLiterContext pairLiter() {
			return getRuleContext(PairLiterContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public BinaryOperContext binaryOper() {
			return getRuleContext(BinaryOperContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(218);
				intLiter();
				}
				break;
			case 2:
				{
				setState(219);
				match(OPEN_PARENTHESES);
				setState(220);
				expr(0);
				setState(221);
				match(CLOSE_PARENTHESES);
				}
				break;
			case 3:
				{
				setState(223);
				bool();
				}
				break;
			case 4:
				{
				setState(224);
				charLiter();
				}
				break;
			case 5:
				{
				setState(225);
				strLiter();
				}
				break;
			case 6:
				{
				setState(226);
				pairLiter();
				}
				break;
			case 7:
				{
				setState(227);
				match(IDENT);
				}
				break;
			case 8:
				{
				setState(228);
				arrayElem();
				}
				break;
			case 9:
				{
				setState(229);
				unaryOper();
				setState(230);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(234);
					if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
					setState(235);
					binaryOper();
					setState(236);
					expr(11);
					}
					} 
				}
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BoolContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(BasicParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(BasicParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class IntSignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public IntSignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intSign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterIntSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitIntSign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntSignContext intSign() throws RecognitionException {
		IntSignContext _localctx = new IntSignContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_intSign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class IntLiterContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BasicParser.INTEGER, 0); }
		public IntSignContext intSign() {
			return getRuleContext(IntSignContext.class,0);
		}
		public IntLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterIntLiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitIntLiter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntLiterContext intLiter() throws RecognitionException {
		IntLiterContext _localctx = new IntLiterContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_intLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(247);
				intSign();
				}
			}

			setState(250);
			match(INTEGER);
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

	public static class ArrayLiterContext extends ParserRuleContext {
		public TerminalNode OPEN_SQR_BRACKET() { return getToken(BasicParser.OPEN_SQR_BRACKET, 0); }
		public TerminalNode CLOSE_SQR_BRACKET() { return getToken(BasicParser.CLOSE_SQR_BRACKET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArrayLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArrayLiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArrayLiter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiterContext arrayLiter() throws RecognitionException {
		ArrayLiterContext _localctx = new ArrayLiterContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			match(OPEN_SQR_BRACKET);
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DBL_QUOTES) | (1L << QUOTE) | (1L << PLUS) | (1L << MINUS) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << EXCL) | (1L << OPEN_PARENTHESES) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << IDENT))) != 0)) {
				{
				setState(253);
				expr(0);
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(254);
					match(COMMA);
					setState(255);
					expr(0);
					}
					}
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(263);
			match(CLOSE_SQR_BRACKET);
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

	public static class CharacterContext extends ParserRuleContext {
		public TerminalNode BACKSLASH() { return getToken(BasicParser.BACKSLASH, 0); }
		public TerminalNode QUOTE() { return getToken(BasicParser.QUOTE, 0); }
		public TerminalNode DBL_QUOTES() { return getToken(BasicParser.DBL_QUOTES, 0); }
		public EscCharContext escChar() {
			return getRuleContext(EscCharContext.class,0);
		}
		public CharacterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterCharacter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitCharacter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitCharacter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharacterContext character() throws RecognitionException {
		CharacterContext _localctx = new CharacterContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_character);
		int _la;
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COMMA:
			case PLUS:
			case MINUS:
			case MULT:
			case DIVIDE:
			case MOD:
			case LESS:
			case LESS_EQ:
			case GREAT:
			case GREAT_EQ:
			case EQ:
			case NOTEQ:
			case AND:
			case OR:
			case ZERO:
			case B:
			case T:
			case SKIP_FUNC:
			case BEGIN:
			case END:
			case WHILE:
			case DO:
			case PRINT:
			case READ:
			case FREE:
			case RETURN:
			case EXIT:
			case PRINTLN:
			case IF:
			case ELSE:
			case THEN:
			case FI:
			case NEWPAIR:
			case FST:
			case SND:
			case PAIR:
			case LEN:
			case ORD:
			case CHR:
			case EXCL:
			case OPEN_PARENTHESES:
			case CLOSE_PARENTHESES:
			case OPEN_SQR_BRACKET:
			case CLOSE_SQR_BRACKET:
			case UNDERSCORE:
			case SEMICOLON:
			case HASH:
			case NULL:
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
			case TRUE:
			case FALSE:
			case INTEGER:
			case IDENT_TAIL:
			case IDENT:
			case WHITESPACE:
			case DIV:
			case IS:
			case SKIP:
			case EQUAL:
			case DONE:
			case CALL:
			case N:
			case F:
			case R:
			case EOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				_la = _input.LA(1);
				if ( _la <= 0 || ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DBL_QUOTES) | (1L << QUOTE) | (1L << BACKSLASH))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case BACKSLASH:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				match(BACKSLASH);
				setState(267);
				escChar();
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

	public static class EscCharContext extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(BasicParser.ZERO, 0); }
		public TerminalNode B() { return getToken(BasicParser.B, 0); }
		public TerminalNode T() { return getToken(BasicParser.T, 0); }
		public TerminalNode N() { return getToken(BasicParser.N, 0); }
		public TerminalNode F() { return getToken(BasicParser.F, 0); }
		public TerminalNode R() { return getToken(BasicParser.R, 0); }
		public TerminalNode DBL_QUOTES() { return getToken(BasicParser.DBL_QUOTES, 0); }
		public TerminalNode QUOTE() { return getToken(BasicParser.QUOTE, 0); }
		public TerminalNode BACKSLASH() { return getToken(BasicParser.BACKSLASH, 0); }
		public EscCharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_escChar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterEscChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitEscChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEscChar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EscCharContext escChar() throws RecognitionException {
		EscCharContext _localctx = new EscCharContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_escChar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DBL_QUOTES) | (1L << QUOTE) | (1L << ZERO) | (1L << B) | (1L << T) | (1L << BACKSLASH))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (N - 68)) | (1L << (F - 68)) | (1L << (R - 68)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class PairLiterContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(BasicParser.NULL, 0); }
		public PairLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairLiter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairLiter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairLiter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairLiterContext pairLiter() throws RecognitionException {
		PairLiterContext _localctx = new PairLiterContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_pairLiter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			match(NULL);
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

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode HASH() { return getToken(BasicParser.HASH, 0); }
		public List<TerminalNode> EOL() { return getTokens(BasicParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(BasicParser.EOL, i);
		}
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(HASH);
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DBL_QUOTES) | (1L << QUOTE) | (1L << COMMA) | (1L << PLUS) | (1L << MINUS) | (1L << MULT) | (1L << DIVIDE) | (1L << MOD) | (1L << LESS) | (1L << LESS_EQ) | (1L << GREAT) | (1L << GREAT_EQ) | (1L << EQ) | (1L << NOTEQ) | (1L << AND) | (1L << OR) | (1L << ZERO) | (1L << B) | (1L << T) | (1L << SKIP_FUNC) | (1L << BEGIN) | (1L << END) | (1L << WHILE) | (1L << DO) | (1L << PRINT) | (1L << READ) | (1L << FREE) | (1L << RETURN) | (1L << EXIT) | (1L << PRINTLN) | (1L << IF) | (1L << ELSE) | (1L << THEN) | (1L << FI) | (1L << NEWPAIR) | (1L << FST) | (1L << SND) | (1L << PAIR) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << EXCL) | (1L << OPEN_PARENTHESES) | (1L << CLOSE_PARENTHESES) | (1L << OPEN_SQR_BRACKET) | (1L << CLOSE_SQR_BRACKET) | (1L << UNDERSCORE) | (1L << SEMICOLON) | (1L << BACKSLASH) | (1L << HASH) | (1L << NULL) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << IDENT_TAIL) | (1L << IDENT) | (1L << WHITESPACE) | (1L << DIV) | (1L << IS))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (SKIP - 64)) | (1L << (EQUAL - 64)) | (1L << (DONE - 64)) | (1L << (CALL - 64)) | (1L << (N - 64)) | (1L << (F - 64)) | (1L << (R - 64)))) != 0)) {
				{
				{
				setState(275);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==EOL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(281);
			match(EOL);
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

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DBL_QUOTES) | (1L << QUOTE) | (1L << PLUS) | (1L << MINUS) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << EXCL) | (1L << OPEN_PARENTHESES) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << INTEGER) | (1L << IDENT))) != 0)) {
				{
				{
				setState(283);
				expr(0);
				}
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(289);
			match(EOF);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 13:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 17:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3I\u0126\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\3\3\3\3\4\3\4\7\4?\n\4\f\4\16"+
		"\4B\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\7\6M\n\6\f\6\16\6P\13\6\3"+
		"\7\3\7\3\7\7\7U\n\7\f\7\16\7X\13\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\5\ta\n"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0091\n\n\3\n\3\n\3"+
		"\n\7\n\u0096\n\n\f\n\16\n\u0099\13\n\3\13\3\13\3\13\5\13\u009e\n\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00ae\n\f\3"+
		"\f\5\f\u00b1\n\f\3\r\3\r\3\r\3\r\3\r\6\r\u00b8\n\r\r\r\16\r\u00b9\3\16"+
		"\3\16\3\16\3\16\5\16\u00c0\n\16\3\17\3\17\3\17\5\17\u00c5\n\17\3\17\3"+
		"\17\3\17\7\17\u00ca\n\17\f\17\16\17\u00cd\13\17\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00eb\n\23\3\23"+
		"\3\23\3\23\3\23\7\23\u00f1\n\23\f\23\16\23\u00f4\13\23\3\24\3\24\3\25"+
		"\3\25\3\26\5\26\u00fb\n\26\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u0103\n"+
		"\27\f\27\16\27\u0106\13\27\5\27\u0108\n\27\3\27\3\27\3\30\3\30\3\30\5"+
		"\30\u010f\n\30\3\31\3\31\3\32\3\32\3\33\3\33\7\33\u0117\n\33\f\33\16\33"+
		"\u011a\13\33\3\33\3\33\3\34\7\34\u011f\n\34\f\34\16\34\u0122\13\34\3\34"+
		"\3\34\3\34\2\5\22\34$\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&("+
		"*,.\60\62\64\66\2\n\5\2\6\b\n\22@@\4\2\7\7),\3\2\669\3\2:;\3\2\6\7\4\2"+
		"\3\4\63\63\6\2\3\4\23\25\63\63FH\3\2II\2\u0134\28\3\2\2\2\4:\3\2\2\2\6"+
		"<\3\2\2\2\bE\3\2\2\2\nI\3\2\2\2\fQ\3\2\2\2\16Y\3\2\2\2\20\\\3\2\2\2\22"+
		"\u0090\3\2\2\2\24\u009d\3\2\2\2\26\u00b0\3\2\2\2\30\u00b2\3\2\2\2\32\u00bf"+
		"\3\2\2\2\34\u00c4\3\2\2\2\36\u00ce\3\2\2\2 \u00d0\3\2\2\2\"\u00d4\3\2"+
		"\2\2$\u00ea\3\2\2\2&\u00f5\3\2\2\2(\u00f7\3\2\2\2*\u00fa\3\2\2\2,\u00fe"+
		"\3\2\2\2.\u010e\3\2\2\2\60\u0110\3\2\2\2\62\u0112\3\2\2\2\64\u0114\3\2"+
		"\2\2\66\u0120\3\2\2\289\t\2\2\29\3\3\2\2\2:;\t\3\2\2;\5\3\2\2\2<@\7\3"+
		"\2\2=?\5.\30\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2"+
		"\2\2CD\7\3\2\2D\7\3\2\2\2EF\7\4\2\2FG\5.\30\2GH\7\4\2\2H\t\3\2\2\2IN\5"+
		"$\23\2JK\7\5\2\2KM\5$\23\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\13"+
		"\3\2\2\2PN\3\2\2\2QV\5\16\b\2RS\7\5\2\2SU\5\16\b\2TR\3\2\2\2UX\3\2\2\2"+
		"VT\3\2\2\2VW\3\2\2\2W\r\3\2\2\2XV\3\2\2\2YZ\5\34\17\2Z[\7>\2\2[\17\3\2"+
		"\2\2\\]\5\34\17\2]^\7>\2\2^`\7-\2\2_a\5\f\7\2`_\3\2\2\2`a\3\2\2\2ab\3"+
		"\2\2\2bc\7.\2\2cd\7A\2\2de\5\22\n\2ef\7\30\2\2f\21\3\2\2\2gh\b\n\1\2h"+
		"\u0091\7B\2\2ij\5\34\17\2jk\7>\2\2kl\7C\2\2lm\5\26\f\2m\u0091\3\2\2\2"+
		"no\5\24\13\2op\7C\2\2pq\5\26\f\2q\u0091\3\2\2\2rs\7\34\2\2s\u0091\5\24"+
		"\13\2tu\7\35\2\2u\u0091\5$\23\2vw\7\36\2\2w\u0091\5$\23\2xy\7\37\2\2y"+
		"\u0091\5$\23\2z{\7\33\2\2{\u0091\5$\23\2|}\7 \2\2}\u0091\5$\23\2~\177"+
		"\7!\2\2\177\u0080\5$\23\2\u0080\u0081\7#\2\2\u0081\u0082\5\22\n\2\u0082"+
		"\u0083\7\"\2\2\u0083\u0084\5\22\n\2\u0084\u0085\7$\2\2\u0085\u0091\3\2"+
		"\2\2\u0086\u0087\7\31\2\2\u0087\u0088\5$\23\2\u0088\u0089\7\32\2\2\u0089"+
		"\u008a\5\22\n\2\u008a\u008b\7D\2\2\u008b\u0091\3\2\2\2\u008c\u008d\7\27"+
		"\2\2\u008d\u008e\5\22\n\2\u008e\u008f\7\30\2\2\u008f\u0091\3\2\2\2\u0090"+
		"g\3\2\2\2\u0090i\3\2\2\2\u0090n\3\2\2\2\u0090r\3\2\2\2\u0090t\3\2\2\2"+
		"\u0090v\3\2\2\2\u0090x\3\2\2\2\u0090z\3\2\2\2\u0090|\3\2\2\2\u0090~\3"+
		"\2\2\2\u0090\u0086\3\2\2\2\u0090\u008c\3\2\2\2\u0091\u0097\3\2\2\2\u0092"+
		"\u0093\f\3\2\2\u0093\u0094\7\62\2\2\u0094\u0096\5\22\n\4\u0095\u0092\3"+
		"\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\23\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009e\7>\2\2\u009b\u009e\5\30\r"+
		"\2\u009c\u009e\5\32\16\2\u009d\u009a\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009c\3\2\2\2\u009e\25\3\2\2\2\u009f\u00b1\5$\23\2\u00a0\u00b1\5,\27"+
		"\2\u00a1\u00a2\7%\2\2\u00a2\u00a3\7-\2\2\u00a3\u00a4\5$\23\2\u00a4\u00a5"+
		"\7\5\2\2\u00a5\u00a6\5$\23\2\u00a6\u00a7\7.\2\2\u00a7\u00b1\3\2\2\2\u00a8"+
		"\u00b1\5\32\16\2\u00a9\u00aa\7E\2\2\u00aa\u00ab\7>\2\2\u00ab\u00ad\7-"+
		"\2\2\u00ac\u00ae\5\n\6\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b1\7.\2\2\u00b0\u009f\3\2\2\2\u00b0\u00a0\3\2"+
		"\2\2\u00b0\u00a1\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0\u00a9\3\2\2\2\u00b1"+
		"\27\3\2\2\2\u00b2\u00b7\7>\2\2\u00b3\u00b4\7/\2\2\u00b4\u00b5\5$\23\2"+
		"\u00b5\u00b6\7\60\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b8\u00b9"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\31\3\2\2\2\u00bb"+
		"\u00bc\7&\2\2\u00bc\u00c0\5$\23\2\u00bd\u00be\7\'\2\2\u00be\u00c0\5$\23"+
		"\2\u00bf\u00bb\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\33\3\2\2\2\u00c1\u00c2"+
		"\b\17\1\2\u00c2\u00c5\5\36\20\2\u00c3\u00c5\5\"\22\2\u00c4\u00c1\3\2\2"+
		"\2\u00c4\u00c3\3\2\2\2\u00c5\u00cb\3\2\2\2\u00c6\u00c7\f\5\2\2\u00c7\u00c8"+
		"\7/\2\2\u00c8\u00ca\7\60\2\2\u00c9\u00c6\3\2\2\2\u00ca\u00cd\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\35\3\2\2\2\u00cd\u00cb\3\2\2"+
		"\2\u00ce\u00cf\t\4\2\2\u00cf\37\3\2\2\2\u00d0\u00d1\5\36\20\2\u00d1\u00d2"+
		"\5\34\17\2\u00d2\u00d3\7(\2\2\u00d3!\3\2\2\2\u00d4\u00d5\7(\2\2\u00d5"+
		"\u00d6\7-\2\2\u00d6\u00d7\5 \21\2\u00d7\u00d8\7\5\2\2\u00d8\u00d9\5 \21"+
		"\2\u00d9\u00da\7.\2\2\u00da#\3\2\2\2\u00db\u00dc\b\23\1\2\u00dc\u00eb"+
		"\5*\26\2\u00dd\u00de\7-\2\2\u00de\u00df\5$\23\2\u00df\u00e0\7.\2\2\u00e0"+
		"\u00eb\3\2\2\2\u00e1\u00eb\5&\24\2\u00e2\u00eb\5\b\5\2\u00e3\u00eb\5\6"+
		"\4\2\u00e4\u00eb\5\62\32\2\u00e5\u00eb\7>\2\2\u00e6\u00eb\5\30\r\2\u00e7"+
		"\u00e8\5\4\3\2\u00e8\u00e9\5$\23\3\u00e9\u00eb\3\2\2\2\u00ea\u00db\3\2"+
		"\2\2\u00ea\u00dd\3\2\2\2\u00ea\u00e1\3\2\2\2\u00ea\u00e2\3\2\2\2\u00ea"+
		"\u00e3\3\2\2\2\u00ea\u00e4\3\2\2\2\u00ea\u00e5\3\2\2\2\u00ea\u00e6\3\2"+
		"\2\2\u00ea\u00e7\3\2\2\2\u00eb\u00f2\3\2\2\2\u00ec\u00ed\f\f\2\2\u00ed"+
		"\u00ee\5\2\2\2\u00ee\u00ef\5$\23\r\u00ef\u00f1\3\2\2\2\u00f0\u00ec\3\2"+
		"\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"%\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\t\5\2\2\u00f6\'\3\2\2\2\u00f7"+
		"\u00f8\t\6\2\2\u00f8)\3\2\2\2\u00f9\u00fb\5(\25\2\u00fa\u00f9\3\2\2\2"+
		"\u00fa\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\7<\2\2\u00fd+\3\2"+
		"\2\2\u00fe\u0107\7/\2\2\u00ff\u0104\5$\23\2\u0100\u0101\7\5\2\2\u0101"+
		"\u0103\5$\23\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2"+
		"\2\2\u0104\u0105\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0107"+
		"\u00ff\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a\7\60"+
		"\2\2\u010a-\3\2\2\2\u010b\u010f\n\7\2\2\u010c\u010d\7\63\2\2\u010d\u010f"+
		"\5\60\31\2\u010e\u010b\3\2\2\2\u010e\u010c\3\2\2\2\u010f/\3\2\2\2\u0110"+
		"\u0111\t\b\2\2\u0111\61\3\2\2\2\u0112\u0113\7\65\2\2\u0113\63\3\2\2\2"+
		"\u0114\u0118\7\64\2\2\u0115\u0117\n\t\2\2\u0116\u0115\3\2\2\2\u0117\u011a"+
		"\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011b\u011c\7I\2\2\u011c\65\3\2\2\2\u011d\u011f\5$\23\2"+
		"\u011e\u011d\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u0124\7\2\2\3\u0124"+
		"\67\3\2\2\2\27@NV`\u0090\u0097\u009d\u00ad\u00b0\u00b9\u00bf\u00c4\u00cb"+
		"\u00ea\u00f2\u00fa\u0104\u0107\u010e\u0118\u0120";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}