// Generated from /home/nahida/Documents/Year2/WACC/wacc_25/src/main/antlr4/BasicLexer.g4 by ANTLR 4.7.2
package antlr4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "WS", "EOL", "SKIP_FUNC", "BEGIN", "END", "WHILE", "DO", "DONE", 
			"IS", "PRINT", "READ", "FREE", "RETURN", "EXIT", "PRINTLN", "CALL", "IF", 
			"ELSE", "THEN", "FI", "NEWPAIR", "FST", "SND", "PAIR", "INT", "BOOL", 
			"CHAR", "STRING", "BOOL_LIT", "TRUE", "FALSE", "NOT", "LEN", "ORD", "CHR", 
			"MULT", "DIV", "MOD", "PLUS", "MINUS", "LESS", "LESS_EQ", "GREAT", "GREAT_EQ", 
			"EQ", "NOTEQ", "AND", "OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQR_BRACKET", "CLOSE_SQR_BRACKET", "SEMICOLON", "EQUAL", "DBL_QUOTES", 
			"QUOTE", "COMMA", "BACKSLASH", "HASH", "NULL", "DIGIT", "LETTER", "UNDERSCORE", 
			"CHARACTER", "IDENT", "IDENT_TAIL", "INT_LIT", "ESC_CHAR", "CHAR_LIT", 
			"STR_LIT"
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


	public BasicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BasicLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2B\u01bb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\3\2"+
		"\3\2\7\2\u0094\n\2\f\2\16\2\u0097\13\2\3\2\3\2\3\2\3\2\3\3\6\3\u009e\n"+
		"\3\r\3\16\3\u009f\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\5\37\u012b\n\37\3 \3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3!\3!\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3\'\3\'\3"+
		"(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60"+
		"\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66"+
		"\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3>\3>\3>\3?\3?\3"+
		"@\3@\3A\3A\3B\3B\5B\u0189\nB\3C\3C\7C\u018d\nC\fC\16C\u0190\13C\3D\3D"+
		"\5D\u0194\nD\3E\6E\u0197\nE\rE\16E\u0198\3F\3F\3F\3F\3F\3F\3F\3F\3F\3"+
		"F\3F\3F\3F\3F\3F\3F\3F\3F\5F\u01ad\nF\3G\3G\3G\3G\3H\3H\7H\u01b5\nH\f"+
		"H\16H\u01b8\13H\3H\3H\2\2I\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a"+
		"\62c\63e\64g\65i\66k\67m8o9q\2s\2u:w;y<{=}\2\177\2\u0081\2\u0083\2\u0085"+
		">\u0087\2\u0089?\u008b@\u008dA\u008fB\3\2\6\4\2\f\f\17\17\5\2\13\f\17"+
		"\17\"\"\5\2C\\aac|\5\2$$))^^\2\u01c3\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2["+
		"\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2"+
		"\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2"+
		"\2y\3\2\2\2\2{\3\2\2\2\2\u0085\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2"+
		"\2\u008d\3\2\2\2\2\u008f\3\2\2\2\3\u0091\3\2\2\2\5\u009d\3\2\2\2\7\u00a3"+
		"\3\2\2\2\t\u00a5\3\2\2\2\13\u00aa\3\2\2\2\r\u00b0\3\2\2\2\17\u00b4\3\2"+
		"\2\2\21\u00ba\3\2\2\2\23\u00bd\3\2\2\2\25\u00c2\3\2\2\2\27\u00c5\3\2\2"+
		"\2\31\u00cb\3\2\2\2\33\u00d0\3\2\2\2\35\u00d5\3\2\2\2\37\u00dc\3\2\2\2"+
		"!\u00e1\3\2\2\2#\u00e9\3\2\2\2%\u00ee\3\2\2\2\'\u00f1\3\2\2\2)\u00f6\3"+
		"\2\2\2+\u00fb\3\2\2\2-\u00fe\3\2\2\2/\u0106\3\2\2\2\61\u010a\3\2\2\2\63"+
		"\u010e\3\2\2\2\65\u0113\3\2\2\2\67\u0117\3\2\2\29\u011c\3\2\2\2;\u0121"+
		"\3\2\2\2=\u012a\3\2\2\2?\u012c\3\2\2\2A\u0131\3\2\2\2C\u0137\3\2\2\2E"+
		"\u0139\3\2\2\2G\u013d\3\2\2\2I\u0141\3\2\2\2K\u0145\3\2\2\2M\u0147\3\2"+
		"\2\2O\u0149\3\2\2\2Q\u014b\3\2\2\2S\u014d\3\2\2\2U\u014f\3\2\2\2W\u0151"+
		"\3\2\2\2Y\u0154\3\2\2\2[\u0156\3\2\2\2]\u0159\3\2\2\2_\u015c\3\2\2\2a"+
		"\u015f\3\2\2\2c\u0162\3\2\2\2e\u0165\3\2\2\2g\u0167\3\2\2\2i\u0169\3\2"+
		"\2\2k\u016b\3\2\2\2m\u016d\3\2\2\2o\u016f\3\2\2\2q\u0171\3\2\2\2s\u0173"+
		"\3\2\2\2u\u0175\3\2\2\2w\u0177\3\2\2\2y\u0179\3\2\2\2{\u017b\3\2\2\2}"+
		"\u0180\3\2\2\2\177\u0182\3\2\2\2\u0081\u0184\3\2\2\2\u0083\u0188\3\2\2"+
		"\2\u0085\u018a\3\2\2\2\u0087\u0193\3\2\2\2\u0089\u0196\3\2\2\2\u008b\u01ac"+
		"\3\2\2\2\u008d\u01ae\3\2\2\2\u008f\u01b2\3\2\2\2\u0091\u0095\5y=\2\u0092"+
		"\u0094\n\2\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098"+
		"\u0099\5\7\4\2\u0099\u009a\3\2\2\2\u009a\u009b\b\2\2\2\u009b\4\3\2\2\2"+
		"\u009c\u009e\t\3\2\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b\3\2\2\u00a2"+
		"\6\3\2\2\2\u00a3\u00a4\t\2\2\2\u00a4\b\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6"+
		"\u00a7\7m\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7r\2\2\u00a9\n\3\2\2\2\u00aa"+
		"\u00ab\7d\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7i\2\2\u00ad\u00ae\7k\2\2"+
		"\u00ae\u00af\7p\2\2\u00af\f\3\2\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7p"+
		"\2\2\u00b2\u00b3\7f\2\2\u00b3\16\3\2\2\2\u00b4\u00b5\7y\2\2\u00b5\u00b6"+
		"\7j\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7g\2\2\u00b9"+
		"\20\3\2\2\2\u00ba\u00bb\7f\2\2\u00bb\u00bc\7q\2\2\u00bc\22\3\2\2\2\u00bd"+
		"\u00be\7f\2\2\u00be\u00bf\7q\2\2\u00bf\u00c0\7p\2\2\u00c0\u00c1\7g\2\2"+
		"\u00c1\24\3\2\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4\7u\2\2\u00c4\26\3\2\2"+
		"\2\u00c5\u00c6\7r\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9"+
		"\7p\2\2\u00c9\u00ca\7v\2\2\u00ca\30\3\2\2\2\u00cb\u00cc\7t\2\2\u00cc\u00cd"+
		"\7g\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7f\2\2\u00cf\32\3\2\2\2\u00d0\u00d1"+
		"\7h\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7g\2\2\u00d4"+
		"\34\3\2\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7v\2\2\u00d8"+
		"\u00d9\7w\2\2\u00d9\u00da\7t\2\2\u00da\u00db\7p\2\2\u00db\36\3\2\2\2\u00dc"+
		"\u00dd\7g\2\2\u00dd\u00de\7z\2\2\u00de\u00df\7k\2\2\u00df\u00e0\7v\2\2"+
		"\u00e0 \3\2\2\2\u00e1\u00e2\7r\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4\7k\2"+
		"\2\u00e4\u00e5\7p\2\2\u00e5\u00e6\7v\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8"+
		"\7p\2\2\u00e8\"\3\2\2\2\u00e9\u00ea\7e\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec"+
		"\7n\2\2\u00ec\u00ed\7n\2\2\u00ed$\3\2\2\2\u00ee\u00ef\7k\2\2\u00ef\u00f0"+
		"\7h\2\2\u00f0&\3\2\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7n\2\2\u00f3\u00f4"+
		"\7u\2\2\u00f4\u00f5\7g\2\2\u00f5(\3\2\2\2\u00f6\u00f7\7v\2\2\u00f7\u00f8"+
		"\7j\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7p\2\2\u00fa*\3\2\2\2\u00fb\u00fc"+
		"\7h\2\2\u00fc\u00fd\7k\2\2\u00fd,\3\2\2\2\u00fe\u00ff\7p\2\2\u00ff\u0100"+
		"\7g\2\2\u0100\u0101\7y\2\2\u0101\u0102\7r\2\2\u0102\u0103\7c\2\2\u0103"+
		"\u0104\7k\2\2\u0104\u0105\7t\2\2\u0105.\3\2\2\2\u0106\u0107\7h\2\2\u0107"+
		"\u0108\7u\2\2\u0108\u0109\7v\2\2\u0109\60\3\2\2\2\u010a\u010b\7u\2\2\u010b"+
		"\u010c\7p\2\2\u010c\u010d\7f\2\2\u010d\62\3\2\2\2\u010e\u010f\7r\2\2\u010f"+
		"\u0110\7c\2\2\u0110\u0111\7k\2\2\u0111\u0112\7t\2\2\u0112\64\3\2\2\2\u0113"+
		"\u0114\7k\2\2\u0114\u0115\7p\2\2\u0115\u0116\7v\2\2\u0116\66\3\2\2\2\u0117"+
		"\u0118\7d\2\2\u0118\u0119\7q\2\2\u0119\u011a\7q\2\2\u011a\u011b\7n\2\2"+
		"\u011b8\3\2\2\2\u011c\u011d\7e\2\2\u011d\u011e\7j\2\2\u011e\u011f\7c\2"+
		"\2\u011f\u0120\7t\2\2\u0120:\3\2\2\2\u0121\u0122\7u\2\2\u0122\u0123\7"+
		"v\2\2\u0123\u0124\7t\2\2\u0124\u0125\7k\2\2\u0125\u0126\7p\2\2\u0126\u0127"+
		"\7i\2\2\u0127<\3\2\2\2\u0128\u012b\5? \2\u0129\u012b\5A!\2\u012a\u0128"+
		"\3\2\2\2\u012a\u0129\3\2\2\2\u012b>\3\2\2\2\u012c\u012d\7v\2\2\u012d\u012e"+
		"\7t\2\2\u012e\u012f\7w\2\2\u012f\u0130\7g\2\2\u0130@\3\2\2\2\u0131\u0132"+
		"\7h\2\2\u0132\u0133\7c\2\2\u0133\u0134\7n\2\2\u0134\u0135\7u\2\2\u0135"+
		"\u0136\7g\2\2\u0136B\3\2\2\2\u0137\u0138\7#\2\2\u0138D\3\2\2\2\u0139\u013a"+
		"\7n\2\2\u013a\u013b\7g\2\2\u013b\u013c\7p\2\2\u013cF\3\2\2\2\u013d\u013e"+
		"\7q\2\2\u013e\u013f\7t\2\2\u013f\u0140\7f\2\2\u0140H\3\2\2\2\u0141\u0142"+
		"\7e\2\2\u0142\u0143\7j\2\2\u0143\u0144\7t\2\2\u0144J\3\2\2\2\u0145\u0146"+
		"\7,\2\2\u0146L\3\2\2\2\u0147\u0148\7\61\2\2\u0148N\3\2\2\2\u0149\u014a"+
		"\7\'\2\2\u014aP\3\2\2\2\u014b\u014c\7-\2\2\u014cR\3\2\2\2\u014d\u014e"+
		"\7/\2\2\u014eT\3\2\2\2\u014f\u0150\7>\2\2\u0150V\3\2\2\2\u0151\u0152\7"+
		">\2\2\u0152\u0153\7?\2\2\u0153X\3\2\2\2\u0154\u0155\7@\2\2\u0155Z\3\2"+
		"\2\2\u0156\u0157\7@\2\2\u0157\u0158\7?\2\2\u0158\\\3\2\2\2\u0159\u015a"+
		"\7?\2\2\u015a\u015b\7?\2\2\u015b^\3\2\2\2\u015c\u015d\7#\2\2\u015d\u015e"+
		"\7?\2\2\u015e`\3\2\2\2\u015f\u0160\7(\2\2\u0160\u0161\7(\2\2\u0161b\3"+
		"\2\2\2\u0162\u0163\7~\2\2\u0163\u0164\7~\2\2\u0164d\3\2\2\2\u0165\u0166"+
		"\7*\2\2\u0166f\3\2\2\2\u0167\u0168\7+\2\2\u0168h\3\2\2\2\u0169\u016a\7"+
		"]\2\2\u016aj\3\2\2\2\u016b\u016c\7_\2\2\u016cl\3\2\2\2\u016d\u016e\7="+
		"\2\2\u016en\3\2\2\2\u016f\u0170\7?\2\2\u0170p\3\2\2\2\u0171\u0172\7$\2"+
		"\2\u0172r\3\2\2\2\u0173\u0174\7)\2\2\u0174t\3\2\2\2\u0175\u0176\7.\2\2"+
		"\u0176v\3\2\2\2\u0177\u0178\7^\2\2\u0178x\3\2\2\2\u0179\u017a\7%\2\2\u017a"+
		"z\3\2\2\2\u017b\u017c\7p\2\2\u017c\u017d\7w\2\2\u017d\u017e\7n\2\2\u017e"+
		"\u017f\7n\2\2\u017f|\3\2\2\2\u0180\u0181\4\62;\2\u0181~\3\2\2\2\u0182"+
		"\u0183\t\4\2\2\u0183\u0080\3\2\2\2\u0184\u0185\7a\2\2\u0185\u0082\3\2"+
		"\2\2\u0186\u0189\n\5\2\2\u0187\u0189\5\u008bF\2\u0188\u0186\3\2\2\2\u0188"+
		"\u0187\3\2\2\2\u0189\u0084\3\2\2\2\u018a\u018e\5\177@\2\u018b\u018d\5"+
		"\u0087D\2\u018c\u018b\3\2\2\2\u018d\u0190\3\2\2\2\u018e\u018c\3\2\2\2"+
		"\u018e\u018f\3\2\2\2\u018f\u0086\3\2\2\2\u0190\u018e\3\2\2\2\u0191\u0194"+
		"\5\177@\2\u0192\u0194\5}?\2\u0193\u0191\3\2\2\2\u0193\u0192\3\2\2\2\u0194"+
		"\u0088\3\2\2\2\u0195\u0197\5}?\2\u0196\u0195\3\2\2\2\u0197\u0198\3\2\2"+
		"\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u008a\3\2\2\2\u019a\u019b"+
		"\7^\2\2\u019b\u01ad\7\62\2\2\u019c\u019d\7^\2\2\u019d\u01ad\7d\2\2\u019e"+
		"\u019f\7^\2\2\u019f\u01ad\7v\2\2\u01a0\u01a1\7^\2\2\u01a1\u01ad\7p\2\2"+
		"\u01a2\u01a3\7^\2\2\u01a3\u01ad\7h\2\2\u01a4\u01a5\7^\2\2\u01a5\u01ad"+
		"\7t\2\2\u01a6\u01a7\7^\2\2\u01a7\u01ad\5q9\2\u01a8\u01a9\7^\2\2\u01a9"+
		"\u01ad\5s:\2\u01aa\u01ab\7^\2\2\u01ab\u01ad\5w<\2\u01ac\u019a\3\2\2\2"+
		"\u01ac\u019c\3\2\2\2\u01ac\u019e\3\2\2\2\u01ac\u01a0\3\2\2\2\u01ac\u01a2"+
		"\3\2\2\2\u01ac\u01a4\3\2\2\2\u01ac\u01a6\3\2\2\2\u01ac\u01a8\3\2\2\2\u01ac"+
		"\u01aa\3\2\2\2\u01ad\u008c\3\2\2\2\u01ae\u01af\5s:\2\u01af\u01b0\5\u0083"+
		"B\2\u01b0\u01b1\5s:\2\u01b1\u008e\3\2\2\2\u01b2\u01b6\5q9\2\u01b3\u01b5"+
		"\5\u0083B\2\u01b4\u01b3\3\2\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2"+
		"\2\u01b6\u01b7\3\2\2\2\u01b7\u01b9\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01ba"+
		"\5q9\2\u01ba\u0090\3\2\2\2\f\2\u0095\u009f\u012a\u0188\u018e\u0193\u0198"+
		"\u01ac\u01b6\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}