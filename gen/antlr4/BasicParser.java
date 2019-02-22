// Generated from /home/nahida/Documents/Year2/WACC/wacc_25/src/main/antlr4/BasicParser.g4 by ANTLR 4.7.2
package antlr4;
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
		COMMENT=1, WS=2, EOL=3, SKIP_FUNC=4, BEGIN=5, END=6, WHILE=7, DO=8, DONE=9, 
		IS=10, PRINT=11, READ=12, FREE=13, RETURN=14, EXIT=15, PRINTLN=16, CALL=17, 
		IF=18, ELSE=19, THEN=20, FI=21, NEWPAIR=22, FST=23, SND=24, PAIR=25, INT=26, 
		BOOL=27, CHAR=28, STRING=29, BOOL_LIT=30, TRUE=31, FALSE=32, NOT=33, LEN=34, 
		ORD=35, CHR=36, MULT=37, DIV=38, MOD=39, PLUS=40, MINUS=41, LESS=42, LESS_EQ=43, 
		GREAT=44, GREAT_EQ=45, EQ=46, NOTEQ=47, AND=48, OR=49, OPEN_PARENTHESES=50, 
		CLOSE_PARENTHESES=51, OPEN_SQR_BRACKET=52, CLOSE_SQR_BRACKET=53, SEMICOLON=54, 
		EQUAL=55, COMMA=56, BACKSLASH=57, HASH=58, NULL=59, IDENT=60, INT_LIT=61, 
		ESC_CHAR=62, CHAR_LIT=63, STR_LIT=64;
	public static final int
		RULE_prog = 0, RULE_func = 1, RULE_paramList = 2, RULE_param = 3, RULE_stat = 4, 
		RULE_assignLHS = 5, RULE_assignRHS = 6, RULE_argList = 7, RULE_pairElem = 8, 
		RULE_type = 9, RULE_base_type = 10, RULE_pair_type = 11, RULE_pairElemType = 12, 
		RULE_binaryOper = 13, RULE_addSub = 14, RULE_multDiv = 15, RULE_eq_Op = 16, 
		RULE_boolOp = 17, RULE_expr = 18, RULE_ident = 19, RULE_unaryOper = 20, 
		RULE_arrayElem = 21, RULE_arrayLiter = 22, RULE_pair_Lit = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "func", "paramList", "param", "stat", "assignLHS", "assignRHS", 
			"argList", "pairElem", "type", "base_type", "pair_type", "pairElemType", 
			"binaryOper", "addSub", "multDiv", "eq_Op", "boolOp", "expr", "ident", 
			"unaryOper", "arrayElem", "arrayLiter", "pair_Lit"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'skip'", "'begin'", "'end'", "'while'", "'do'", 
			"'done'", "'is'", "'print'", "'read'", "'free'", "'return'", "'exit'", 
			"'println'", "'call'", "'if'", "'else'", "'then'", "'fi'", "'newpair'", 
			"'fst'", "'snd'", "'pair'", "'int'", "'bool'", "'char'", "'string'", 
			null, "'true'", "'false'", "'!'", "'len'", "'ord'", "'chr'", "'*'", "'/'", 
			"'%'", "'+'", "'-'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'&&'", 
			"'||'", "'('", "')'", "'['", "']'", "';'", "'='", "','", "'\\'", "'#'", 
			"'null'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "WS", "EOL", "SKIP_FUNC", "BEGIN", "END", "WHILE", "DO", 
			"DONE", "IS", "PRINT", "READ", "FREE", "RETURN", "EXIT", "PRINTLN", "CALL", 
			"IF", "ELSE", "THEN", "FI", "NEWPAIR", "FST", "SND", "PAIR", "INT", "BOOL", 
			"CHAR", "STRING", "BOOL_LIT", "TRUE", "FALSE", "NOT", "LEN", "ORD", "CHR", 
			"MULT", "DIV", "MOD", "PLUS", "MINUS", "LESS", "LESS_EQ", "GREAT", "GREAT_EQ", 
			"EQ", "NOTEQ", "AND", "OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQR_BRACKET", "CLOSE_SQR_BRACKET", "SEMICOLON", "EQUAL", "COMMA", 
			"BACKSLASH", "HASH", "NULL", "IDENT", "INT_LIT", "ESC_CHAR", "CHAR_LIT", 
			"STR_LIT"
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

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
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
		enterRule(_localctx, 0, RULE_prog);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(BEGIN);
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(49);
					func();
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(55);
			stat(0);
			setState(56);
			match(END);
			setState(57);
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
		enterRule(_localctx, 2, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			type(0);
			setState(60);
			match(IDENT);
			setState(61);
			match(OPEN_PARENTHESES);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIR) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << NULL))) != 0)) {
				{
				setState(62);
				paramList();
				}
			}

			setState(65);
			match(CLOSE_PARENTHESES);
			setState(66);
			match(IS);
			setState(67);
			stat(0);
			setState(68);
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
		enterRule(_localctx, 4, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			param();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(71);
				match(COMMA);
				setState(72);
				param();
				}
				}
				setState(77);
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
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			type(0);
			setState(79);
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

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReturnContext extends StatContext {
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementContext extends StatContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public StatementContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclContext extends StatContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode EQUAL() { return getToken(BasicParser.EQUAL, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public DeclContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends StatContext {
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode DONE() { return getToken(BasicParser.DONE, 0); }
		public WhileContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FreeContext extends StatContext {
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FreeContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterFree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitFree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFree(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReadContext extends StatContext {
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public ReadContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitRead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitRead(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfCondContext extends StatContext {
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(BasicParser.FI, 0); }
		public IfCondContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterIfCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitIfCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIfCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintContext extends StatContext {
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatListContext extends StatContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(BasicParser.SEMICOLON, 0); }
		public StatListContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStatList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStatList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStatList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SkipContext extends StatContext {
		public TerminalNode SKIP_FUNC() { return getToken(BasicParser.SKIP_FUNC, 0); }
		public SkipContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterSkip(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitSkip(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitSkip(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintlnContext extends StatContext {
		public TerminalNode PRINTLN() { return getToken(BasicParser.PRINTLN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintlnContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPrintln(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPrintln(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPrintln(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignContext extends StatContext {
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(BasicParser.EQUAL, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public AssignContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExitContext extends StatContext {
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExitContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterExit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitExit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExit(this);
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
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKIP_FUNC:
				{
				_localctx = new SkipContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(82);
				match(SKIP_FUNC);
				}
				break;
			case PAIR:
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
			case NULL:
				{
				_localctx = new DeclContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				type(0);
				setState(84);
				match(IDENT);
				setState(85);
				match(EQUAL);
				setState(86);
				assignRHS();
				}
				break;
			case FST:
			case SND:
			case IDENT:
				{
				_localctx = new AssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				assignLHS();
				setState(89);
				match(EQUAL);
				setState(90);
				assignRHS();
				}
				break;
			case READ:
				{
				_localctx = new ReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				match(READ);
				setState(93);
				assignLHS();
				}
				break;
			case FREE:
				{
				_localctx = new FreeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				match(FREE);
				setState(95);
				expr(0);
				}
				break;
			case RETURN:
				{
				_localctx = new ReturnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				match(RETURN);
				setState(97);
				expr(0);
				}
				break;
			case EXIT:
				{
				_localctx = new ExitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98);
				match(EXIT);
				setState(99);
				expr(0);
				}
				break;
			case PRINT:
				{
				_localctx = new PrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100);
				match(PRINT);
				setState(101);
				expr(0);
				}
				break;
			case PRINTLN:
				{
				_localctx = new PrintlnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102);
				match(PRINTLN);
				setState(103);
				expr(0);
				}
				break;
			case IF:
				{
				_localctx = new IfCondContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				match(IF);
				setState(105);
				expr(0);
				setState(106);
				match(THEN);
				setState(107);
				stat(0);
				setState(108);
				match(ELSE);
				setState(109);
				stat(0);
				setState(110);
				match(FI);
				}
				break;
			case WHILE:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(WHILE);
				setState(113);
				expr(0);
				setState(114);
				match(DO);
				setState(115);
				stat(0);
				setState(116);
				match(DONE);
				}
				break;
			case BEGIN:
				{
				_localctx = new StatementContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				match(BEGIN);
				setState(119);
				stat(0);
				setState(120);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatListContext(new StatContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(124);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(125);
					match(SEMICOLON);
					setState(126);
					stat(2);
					}
					} 
				}
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
		public AssignLHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLHS; }
	 
		public AssignLHSContext() { }
		public void copyFrom(AssignLHSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignL_ArrayContext extends AssignLHSContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public AssignL_ArrayContext(AssignLHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignL_Array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignL_Array(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignL_Array(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignL_IdenContext extends AssignLHSContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public AssignL_IdenContext(AssignLHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignL_Iden(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignL_Iden(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignL_Iden(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignL_PairelemContext extends AssignLHSContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignL_PairelemContext(AssignLHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignL_Pairelem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignL_Pairelem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignL_Pairelem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLHSContext assignLHS() throws RecognitionException {
		AssignLHSContext _localctx = new AssignLHSContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignLHS);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new AssignL_IdenContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				match(IDENT);
				}
				break;
			case 2:
				_localctx = new AssignL_ArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				arrayElem();
				}
				break;
			case 3:
				_localctx = new AssignL_PairelemContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
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
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
	 
		public AssignRHSContext() { }
		public void copyFrom(AssignRHSContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignR_ArrayLContext extends AssignRHSContext {
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public AssignR_ArrayLContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_ArrayL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_ArrayL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_ArrayL(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_PairContext extends AssignRHSContext {
		public TerminalNode NEWPAIR() { return getToken(BasicParser.NEWPAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public AssignR_PairContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_Pair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_Pair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_Pair(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_ExpContext extends AssignRHSContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignR_ExpContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_Exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_Exp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_Exp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_Pair_ElemContext extends AssignRHSContext {
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignR_Pair_ElemContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_Pair_Elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_Pair_Elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_Pair_Elem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignR_CallContext extends AssignRHSContext {
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public AssignR_CallContext(AssignRHSContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAssignR_Call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAssignR_Call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignR_Call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_assignRHS);
		int _la;
		try {
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL_LIT:
			case NOT:
			case LEN:
			case ORD:
			case CHR:
			case PLUS:
			case MINUS:
			case OPEN_PARENTHESES:
			case NULL:
			case IDENT:
			case INT_LIT:
			case CHAR_LIT:
			case STR_LIT:
				_localctx = new AssignR_ExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				expr(0);
				}
				break;
			case OPEN_SQR_BRACKET:
				_localctx = new AssignR_ArrayLContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				arrayLiter();
				}
				break;
			case NEWPAIR:
				_localctx = new AssignR_PairContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				match(NEWPAIR);
				setState(140);
				match(OPEN_PARENTHESES);
				setState(141);
				expr(0);
				setState(142);
				match(COMMA);
				setState(143);
				expr(0);
				setState(144);
				match(CLOSE_PARENTHESES);
				}
				break;
			case FST:
			case SND:
				_localctx = new AssignR_Pair_ElemContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				pairElem();
				}
				break;
			case CALL:
				_localctx = new AssignR_CallContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(147);
				match(CALL);
				setState(148);
				match(IDENT);
				setState(149);
				match(OPEN_PARENTHESES);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (BOOL_LIT - 30)) | (1L << (NOT - 30)) | (1L << (LEN - 30)) | (1L << (ORD - 30)) | (1L << (CHR - 30)) | (1L << (PLUS - 30)) | (1L << (MINUS - 30)) | (1L << (OPEN_PARENTHESES - 30)) | (1L << (NULL - 30)) | (1L << (IDENT - 30)) | (1L << (INT_LIT - 30)) | (1L << (CHAR_LIT - 30)) | (1L << (STR_LIT - 30)))) != 0)) {
					{
					setState(150);
					argList();
					}
				}

				setState(153);
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
		enterRule(_localctx, 14, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			expr(0);
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(157);
				match(COMMA);
				setState(158);
				expr(0);
				}
				}
				setState(163);
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
		enterRule(_localctx, 16, RULE_pairElem);
		try {
			setState(168);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(FST);
				setState(165);
				expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(SND);
				setState(167);
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
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_SQR_BRACKET() { return getToken(BasicParser.OPEN_SQR_BRACKET, 0); }
		public TerminalNode CLOSE_SQR_BRACKET() { return getToken(BasicParser.CLOSE_SQR_BRACKET, 0); }
		public ArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairTypeContext extends TypeContext {
		public Pair_typeContext pair_type() {
			return getRuleContext(Pair_typeContext.class,0);
		}
		public PairTypeContext(TypeContext ctx) { copyFrom(ctx); }
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
	public static class BaseTypeContext extends TypeContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public BaseTypeContext(TypeContext ctx) { copyFrom(ctx); }
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

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case CHAR:
			case STRING:
				{
				_localctx = new BaseTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(171);
				base_type();
				}
				break;
			case PAIR:
			case NULL:
				{
				_localctx = new PairTypeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172);
				pair_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(180);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArrayTypeContext(new TypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(175);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(176);
					match(OPEN_SQR_BRACKET);
					setState(177);
					match(CLOSE_SQR_BRACKET);
					}
					} 
				}
				setState(182);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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

	public static class Base_typeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BasicParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(BasicParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(BasicParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public Base_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBase_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBase_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBase_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Base_typeContext base_type() throws RecognitionException {
		Base_typeContext _localctx = new Base_typeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_base_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
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

	public static class Pair_typeContext extends ParserRuleContext {
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public Pair_LitContext pair_Lit() {
			return getRuleContext(Pair_LitContext.class,0);
		}
		public Pair_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPair_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPair_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPair_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pair_typeContext pair_type() throws RecognitionException {
		Pair_typeContext _localctx = new Pair_typeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pair_type);
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAIR:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(PAIR);
				setState(186);
				match(OPEN_PARENTHESES);
				setState(187);
				pairElemType();
				setState(188);
				match(COMMA);
				setState(189);
				pairElemType();
				setState(190);
				match(CLOSE_PARENTHESES);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				pair_Lit();
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

	public static class PairElemTypeContext extends ParserRuleContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairElemType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairElemType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pairElemType);
		try {
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				base_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				type(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				match(PAIR);
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

	public static class BinaryOperContext extends ParserRuleContext {
		public AddSubContext addSub() {
			return getRuleContext(AddSubContext.class,0);
		}
		public MultDivContext multDiv() {
			return getRuleContext(MultDivContext.class,0);
		}
		public Eq_OpContext eq_Op() {
			return getRuleContext(Eq_OpContext.class,0);
		}
		public BoolOpContext boolOp() {
			return getRuleContext(BoolOpContext.class,0);
		}
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
		enterRule(_localctx, 26, RULE_binaryOper);
		try {
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				addSub();
				}
				break;
			case MULT:
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				multDiv();
				}
				break;
			case LESS:
			case LESS_EQ:
			case GREAT:
			case GREAT_EQ:
			case EQ:
			case NOTEQ:
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				eq_Op();
				}
				break;
			case AND:
			case OR:
				enterOuterAlt(_localctx, 4);
				{
				setState(203);
				boolOp();
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

	public static class AddSubContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public AddSubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addSub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddSubContext addSub() throws RecognitionException {
		AddSubContext _localctx = new AddSubContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_addSub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
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

	public static class MultDivContext extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(BasicParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(BasicParser.DIV, 0); }
		public MultDivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multDiv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterMultDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitMultDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitMultDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultDivContext multDiv() throws RecognitionException {
		MultDivContext _localctx = new MultDivContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_multDiv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_la = _input.LA(1);
			if ( !(_la==MULT || _la==DIV) ) {
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

	public static class Eq_OpContext extends ParserRuleContext {
		public TerminalNode GREAT() { return getToken(BasicParser.GREAT, 0); }
		public TerminalNode GREAT_EQ() { return getToken(BasicParser.GREAT_EQ, 0); }
		public TerminalNode LESS() { return getToken(BasicParser.LESS, 0); }
		public TerminalNode LESS_EQ() { return getToken(BasicParser.LESS_EQ, 0); }
		public TerminalNode EQ() { return getToken(BasicParser.EQ, 0); }
		public TerminalNode NOTEQ() { return getToken(BasicParser.NOTEQ, 0); }
		public Eq_OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eq_Op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterEq_Op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitEq_Op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEq_Op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Eq_OpContext eq_Op() throws RecognitionException {
		Eq_OpContext _localctx = new Eq_OpContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_eq_Op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << LESS_EQ) | (1L << GREAT) | (1L << GREAT_EQ) | (1L << EQ) | (1L << NOTEQ))) != 0)) ) {
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

	public static class BoolOpContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public BoolOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBoolOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBoolOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBoolOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOpContext boolOp() throws RecognitionException {
		BoolOpContext _localctx = new BoolOpContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Array_ElemContext extends ExprContext {
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public Array_ElemContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterArray_Elem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitArray_Elem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArray_Elem(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultDivOpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultDivContext multDiv() {
			return getRuleContext(MultDivContext.class,0);
		}
		public MultDivOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterMultDivOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitMultDivOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitMultDivOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharLitContext extends ExprContext {
		public TerminalNode CHAR_LIT() { return getToken(BasicParser.CHAR_LIT, 0); }
		public CharLitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterCharLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitCharLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitCharLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PairLitContext extends ExprContext {
		public Pair_LitContext pair_Lit() {
			return getRuleContext(Pair_LitContext.class,0);
		}
		public PairLitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPairLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPairLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubOpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubContext addSub() {
			return getRuleContext(AddSubContext.class,0);
		}
		public AddSubOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterAddSubOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitAddSubOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAddSubOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqOpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Eq_OpContext eq_Op() {
			return getRuleContext(Eq_OpContext.class,0);
		}
		public EqOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterEqOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitEqOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEqOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnOpContext extends ExprContext {
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterUnOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitUnOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolOperContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BoolOpContext boolOp() {
			return getRuleContext(BoolOpContext.class,0);
		}
		public BoolOperContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBoolOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBoolOper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBoolOper(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdContext extends ExprContext {
		public IdentContext ident() {
			return getRuleContext(IdentContext.class,0);
		}
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLitContext extends ExprContext {
		public TerminalNode INT_LIT() { return getToken(BasicParser.INT_LIT, 0); }
		public IntLitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterIntLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitIntLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIntLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLitContext extends ExprContext {
		public TerminalNode BOOL_LIT() { return getToken(BasicParser.BOOL_LIT, 0); }
		public BoolLitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterBoolLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitBoolLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBoolLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StrLitContext extends ExprContext {
		public TerminalNode STR_LIT() { return getToken(BasicParser.STR_LIT, 0); }
		public StrLitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterStrLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitStrLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStrLit(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenContext extends ExprContext {
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public ParenContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParen(this);
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
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new UnOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(215);
				unaryOper();
				setState(216);
				expr(11);
				}
				break;
			case 2:
				{
				_localctx = new IntLitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(218);
				match(INT_LIT);
				}
				break;
			case 3:
				{
				_localctx = new BoolLitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(219);
				match(BOOL_LIT);
				}
				break;
			case 4:
				{
				_localctx = new CharLitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(220);
				match(CHAR_LIT);
				}
				break;
			case 5:
				{
				_localctx = new StrLitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(221);
				match(STR_LIT);
				}
				break;
			case 6:
				{
				_localctx = new PairLitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(222);
				pair_Lit();
				}
				break;
			case 7:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(223);
				ident();
				}
				break;
			case 8:
				{
				_localctx = new Array_ElemContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(224);
				arrayElem();
				}
				break;
			case 9:
				{
				_localctx = new UnOpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(225);
				unaryOper();
				setState(226);
				expr(2);
				}
				break;
			case 10:
				{
				_localctx = new ParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(228);
				match(OPEN_PARENTHESES);
				setState(229);
				expr(0);
				setState(230);
				match(CLOSE_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(252);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(250);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new MultDivOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(234);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(235);
						multDiv();
						setState(236);
						expr(15);
						}
						break;
					case 2:
						{
						_localctx = new AddSubOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(238);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(239);
						addSub();
						setState(240);
						expr(14);
						}
						break;
					case 3:
						{
						_localctx = new EqOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(242);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(243);
						eq_Op();
						setState(244);
						expr(13);
						}
						break;
					case 4:
						{
						_localctx = new BoolOperContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(247);
						boolOp();
						setState(248);
						expr(11);
						}
						break;
					}
					} 
				}
				setState(254);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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

	public static class IdentContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public IdentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterIdent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitIdent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIdent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentContext ident() throws RecognitionException {
		IdentContext _localctx = new IdentContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ident);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
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

	public static class UnaryOperContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(BasicParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode LEN() { return getToken(BasicParser.LEN, 0); }
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
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
		enterRule(_localctx, 40, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << PLUS) | (1L << MINUS))) != 0)) ) {
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
		enterRule(_localctx, 42, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(IDENT);
			setState(264); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(260);
					match(OPEN_SQR_BRACKET);
					setState(261);
					expr(0);
					setState(262);
					match(CLOSE_SQR_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(266); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		enterRule(_localctx, 44, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(OPEN_SQR_BRACKET);
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & ((1L << (BOOL_LIT - 30)) | (1L << (NOT - 30)) | (1L << (LEN - 30)) | (1L << (ORD - 30)) | (1L << (CHR - 30)) | (1L << (PLUS - 30)) | (1L << (MINUS - 30)) | (1L << (OPEN_PARENTHESES - 30)) | (1L << (NULL - 30)) | (1L << (IDENT - 30)) | (1L << (INT_LIT - 30)) | (1L << (CHAR_LIT - 30)) | (1L << (STR_LIT - 30)))) != 0)) {
				{
				setState(269);
				expr(0);
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(270);
					match(COMMA);
					setState(271);
					expr(0);
					}
					}
					setState(276);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(279);
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

	public static class Pair_LitContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(BasicParser.NULL, 0); }
		public Pair_LitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair_Lit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).enterPair_Lit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicParserListener ) ((BasicParserListener)listener).exitPair_Lit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPair_Lit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pair_LitContext pair_Lit() throws RecognitionException {
		Pair_LitContext _localctx = new Pair_LitContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pair_Lit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 9:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 18:
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
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3B\u011e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\7\2\65\n\2\f\2\16\28\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3"+
		"B\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4L\n\4\f\4\16\4O\13\4\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6}\n\6\3\6\3\6\3\6\7\6\u0082\n\6\f\6\16"+
		"\6\u0085\13\6\3\7\3\7\3\7\5\7\u008a\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009a\n\b\3\b\5\b\u009d\n\b\3\t\3\t\3\t"+
		"\7\t\u00a2\n\t\f\t\16\t\u00a5\13\t\3\n\3\n\3\n\3\n\5\n\u00ab\n\n\3\13"+
		"\3\13\3\13\5\13\u00b0\n\13\3\13\3\13\3\13\7\13\u00b5\n\13\f\13\16\13\u00b8"+
		"\13\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00c4\n\r\3\16\3\16"+
		"\3\16\5\16\u00c9\n\16\3\17\3\17\3\17\3\17\5\17\u00cf\n\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00eb\n\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\7\24\u00fd\n\24\f\24\16\24\u0100\13\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\6\27\u010b\n\27\r\27\16\27\u010c\3\30\3\30\3\30\3"+
		"\30\7\30\u0113\n\30\f\30\16\30\u0116\13\30\5\30\u0118\n\30\3\30\3\30\3"+
		"\31\3\31\3\31\2\5\n\24&\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$"+
		"&(*,.\60\2\b\3\2\34\37\3\2*+\3\2\'(\3\2,\61\3\2\62\63\4\2#&*+\2\u0135"+
		"\2\62\3\2\2\2\4=\3\2\2\2\6H\3\2\2\2\bP\3\2\2\2\n|\3\2\2\2\f\u0089\3\2"+
		"\2\2\16\u009c\3\2\2\2\20\u009e\3\2\2\2\22\u00aa\3\2\2\2\24\u00af\3\2\2"+
		"\2\26\u00b9\3\2\2\2\30\u00c3\3\2\2\2\32\u00c8\3\2\2\2\34\u00ce\3\2\2\2"+
		"\36\u00d0\3\2\2\2 \u00d2\3\2\2\2\"\u00d4\3\2\2\2$\u00d6\3\2\2\2&\u00ea"+
		"\3\2\2\2(\u0101\3\2\2\2*\u0103\3\2\2\2,\u0105\3\2\2\2.\u010e\3\2\2\2\60"+
		"\u011b\3\2\2\2\62\66\7\7\2\2\63\65\5\4\3\2\64\63\3\2\2\2\658\3\2\2\2\66"+
		"\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\5\n\6\2:;\7\b\2\2"+
		";<\7\2\2\3<\3\3\2\2\2=>\5\24\13\2>?\7>\2\2?A\7\64\2\2@B\5\6\4\2A@\3\2"+
		"\2\2AB\3\2\2\2BC\3\2\2\2CD\7\65\2\2DE\7\f\2\2EF\5\n\6\2FG\7\b\2\2G\5\3"+
		"\2\2\2HM\5\b\5\2IJ\7:\2\2JL\5\b\5\2KI\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3"+
		"\2\2\2N\7\3\2\2\2OM\3\2\2\2PQ\5\24\13\2QR\7>\2\2R\t\3\2\2\2ST\b\6\1\2"+
		"T}\7\6\2\2UV\5\24\13\2VW\7>\2\2WX\79\2\2XY\5\16\b\2Y}\3\2\2\2Z[\5\f\7"+
		"\2[\\\79\2\2\\]\5\16\b\2]}\3\2\2\2^_\7\16\2\2_}\5\f\7\2`a\7\17\2\2a}\5"+
		"&\24\2bc\7\20\2\2c}\5&\24\2de\7\21\2\2e}\5&\24\2fg\7\r\2\2g}\5&\24\2h"+
		"i\7\22\2\2i}\5&\24\2jk\7\24\2\2kl\5&\24\2lm\7\26\2\2mn\5\n\6\2no\7\25"+
		"\2\2op\5\n\6\2pq\7\27\2\2q}\3\2\2\2rs\7\t\2\2st\5&\24\2tu\7\n\2\2uv\5"+
		"\n\6\2vw\7\13\2\2w}\3\2\2\2xy\7\7\2\2yz\5\n\6\2z{\7\b\2\2{}\3\2\2\2|S"+
		"\3\2\2\2|U\3\2\2\2|Z\3\2\2\2|^\3\2\2\2|`\3\2\2\2|b\3\2\2\2|d\3\2\2\2|"+
		"f\3\2\2\2|h\3\2\2\2|j\3\2\2\2|r\3\2\2\2|x\3\2\2\2}\u0083\3\2\2\2~\177"+
		"\f\3\2\2\177\u0080\78\2\2\u0080\u0082\5\n\6\4\u0081~\3\2\2\2\u0082\u0085"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\13\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0086\u008a\7>\2\2\u0087\u008a\5,\27\2\u0088\u008a\5\22"+
		"\n\2\u0089\u0086\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a"+
		"\r\3\2\2\2\u008b\u009d\5&\24\2\u008c\u009d\5.\30\2\u008d\u008e\7\30\2"+
		"\2\u008e\u008f\7\64\2\2\u008f\u0090\5&\24\2\u0090\u0091\7:\2\2\u0091\u0092"+
		"\5&\24\2\u0092\u0093\7\65\2\2\u0093\u009d\3\2\2\2\u0094\u009d\5\22\n\2"+
		"\u0095\u0096\7\23\2\2\u0096\u0097\7>\2\2\u0097\u0099\7\64\2\2\u0098\u009a"+
		"\5\20\t\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2"+
		"\u009b\u009d\7\65\2\2\u009c\u008b\3\2\2\2\u009c\u008c\3\2\2\2\u009c\u008d"+
		"\3\2\2\2\u009c\u0094\3\2\2\2\u009c\u0095\3\2\2\2\u009d\17\3\2\2\2\u009e"+
		"\u00a3\5&\24\2\u009f\u00a0\7:\2\2\u00a0\u00a2\5&\24\2\u00a1\u009f\3\2"+
		"\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\21\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7\31\2\2\u00a7\u00ab\5&\24"+
		"\2\u00a8\u00a9\7\32\2\2\u00a9\u00ab\5&\24\2\u00aa\u00a6\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00ab\23\3\2\2\2\u00ac\u00ad\b\13\1\2\u00ad\u00b0\5\26"+
		"\f\2\u00ae\u00b0\5\30\r\2\u00af\u00ac\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0"+
		"\u00b6\3\2\2\2\u00b1\u00b2\f\4\2\2\u00b2\u00b3\7\66\2\2\u00b3\u00b5\7"+
		"\67\2\2\u00b4\u00b1\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\25\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\t\2\2"+
		"\2\u00ba\27\3\2\2\2\u00bb\u00bc\7\33\2\2\u00bc\u00bd\7\64\2\2\u00bd\u00be"+
		"\5\32\16\2\u00be\u00bf\7:\2\2\u00bf\u00c0\5\32\16\2\u00c0\u00c1\7\65\2"+
		"\2\u00c1\u00c4\3\2\2\2\u00c2\u00c4\5\60\31\2\u00c3\u00bb\3\2\2\2\u00c3"+
		"\u00c2\3\2\2\2\u00c4\31\3\2\2\2\u00c5\u00c9\5\26\f\2\u00c6\u00c9\5\24"+
		"\13\2\u00c7\u00c9\7\33\2\2\u00c8\u00c5\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8"+
		"\u00c7\3\2\2\2\u00c9\33\3\2\2\2\u00ca\u00cf\5\36\20\2\u00cb\u00cf\5 \21"+
		"\2\u00cc\u00cf\5\"\22\2\u00cd\u00cf\5$\23\2\u00ce\u00ca\3\2\2\2\u00ce"+
		"\u00cb\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf\35\3\2\2"+
		"\2\u00d0\u00d1\t\3\2\2\u00d1\37\3\2\2\2\u00d2\u00d3\t\4\2\2\u00d3!\3\2"+
		"\2\2\u00d4\u00d5\t\5\2\2\u00d5#\3\2\2\2\u00d6\u00d7\t\6\2\2\u00d7%\3\2"+
		"\2\2\u00d8\u00d9\b\24\1\2\u00d9\u00da\5*\26\2\u00da\u00db\5&\24\r\u00db"+
		"\u00eb\3\2\2\2\u00dc\u00eb\7?\2\2\u00dd\u00eb\7 \2\2\u00de\u00eb\7A\2"+
		"\2\u00df\u00eb\7B\2\2\u00e0\u00eb\5\60\31\2\u00e1\u00eb\5(\25\2\u00e2"+
		"\u00eb\5,\27\2\u00e3\u00e4\5*\26\2\u00e4\u00e5\5&\24\4\u00e5\u00eb\3\2"+
		"\2\2\u00e6\u00e7\7\64\2\2\u00e7\u00e8\5&\24\2\u00e8\u00e9\7\65\2\2\u00e9"+
		"\u00eb\3\2\2\2\u00ea\u00d8\3\2\2\2\u00ea\u00dc\3\2\2\2\u00ea\u00dd\3\2"+
		"\2\2\u00ea\u00de\3\2\2\2\u00ea\u00df\3\2\2\2\u00ea\u00e0\3\2\2\2\u00ea"+
		"\u00e1\3\2\2\2\u00ea\u00e2\3\2\2\2\u00ea\u00e3\3\2\2\2\u00ea\u00e6\3\2"+
		"\2\2\u00eb\u00fe\3\2\2\2\u00ec\u00ed\f\20\2\2\u00ed\u00ee\5 \21\2\u00ee"+
		"\u00ef\5&\24\21\u00ef\u00fd\3\2\2\2\u00f0\u00f1\f\17\2\2\u00f1\u00f2\5"+
		"\36\20\2\u00f2\u00f3\5&\24\20\u00f3\u00fd\3\2\2\2\u00f4\u00f5\f\16\2\2"+
		"\u00f5\u00f6\5\"\22\2\u00f6\u00f7\5&\24\17\u00f7\u00fd\3\2\2\2\u00f8\u00f9"+
		"\f\f\2\2\u00f9\u00fa\5$\23\2\u00fa\u00fb\5&\24\r\u00fb\u00fd\3\2\2\2\u00fc"+
		"\u00ec\3\2\2\2\u00fc\u00f0\3\2\2\2\u00fc\u00f4\3\2\2\2\u00fc\u00f8\3\2"+
		"\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\'\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\7>\2\2\u0102)\3\2\2\2\u0103"+
		"\u0104\t\7\2\2\u0104+\3\2\2\2\u0105\u010a\7>\2\2\u0106\u0107\7\66\2\2"+
		"\u0107\u0108\5&\24\2\u0108\u0109\7\67\2\2\u0109\u010b\3\2\2\2\u010a\u0106"+
		"\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"-\3\2\2\2\u010e\u0117\7\66\2\2\u010f\u0114\5&\24\2\u0110\u0111\7:\2\2"+
		"\u0111\u0113\5&\24\2\u0112\u0110\3\2\2\2\u0113\u0116\3\2\2\2\u0114\u0112"+
		"\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0117"+
		"\u010f\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\7\67"+
		"\2\2\u011a/\3\2\2\2\u011b\u011c\7=\2\2\u011c\61\3\2\2\2\27\66AM|\u0083"+
		"\u0089\u0099\u009c\u00a3\u00aa\u00af\u00b6\u00c3\u00c8\u00ce\u00ea\u00fc"+
		"\u00fe\u010c\u0114\u0117";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}