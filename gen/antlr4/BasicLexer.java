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
		EQUAL=55, COMMA=56, BACKSLASH=57, HASH=58, NULL=59, IDENT=60, OCTAL_LIT=61, 
		BINARY_LIT=62, INT_LIT=63, ESC_CHAR=64, CHAR_LIT=65, STR_LIT=66;
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
			"CHARACTER", "IDENT", "IDENT_TAIL", "OCTAL_LIT", "BINARY", "BINARY_LIT", 
			"INT_LIT", "ESC_CHAR", "CHAR_LIT", "STR_LIT"
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
			"BACKSLASH", "HASH", "NULL", "IDENT", "OCTAL_LIT", "BINARY_LIT", "INT_LIT", 
			"ESC_CHAR", "CHAR_LIT", "STR_LIT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2D\u01d0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\3\2\3\2\7\2\u009a\n\2\f\2\16\2\u009d\13\2\3\2\3\2\3\2"+
		"\3\2\3\3\6\3\u00a4\n\3\r\3\16\3\u00a5\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\5\37\u0131\n\37\3 "+
		"\3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3"+
		"%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/"+
		"\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64"+
		"\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>"+
		"\3>\3>\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\5B\u018f\nB\3C\3C\7C\u0193\nC\fC"+
		"\16C\u0196\13C\3D\3D\5D\u019a\nD\3E\3E\6E\u019e\nE\rE\16E\u019f\3F\3F"+
		"\3F\3G\3G\6G\u01a7\nG\rG\16G\u01a8\3H\6H\u01ac\nH\rH\16H\u01ad\3I\3I\3"+
		"I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\5I\u01c2\nI\3J\3J\3J\3"+
		"J\3K\3K\7K\u01ca\nK\fK\16K\u01cd\13K\3K\3K\2\2L\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q\2s\2u:w;y<{=}\2\177"+
		"\2\u0081\2\u0083\2\u0085>\u0087\2\u0089?\u008b\2\u008d@\u008fA\u0091B"+
		"\u0093C\u0095D\3\2\6\4\2\f\f\17\17\5\2\13\f\17\17\"\"\5\2C\\aac|\5\2$"+
		"$))^^\2\u01d9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2"+
		"\2\2m\3\2\2\2\2o\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2"+
		"\u0085\3\2\2\2\2\u0089\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091"+
		"\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\3\u0097\3\2\2\2\5\u00a3\3\2\2"+
		"\2\7\u00a9\3\2\2\2\t\u00ab\3\2\2\2\13\u00b0\3\2\2\2\r\u00b6\3\2\2\2\17"+
		"\u00ba\3\2\2\2\21\u00c0\3\2\2\2\23\u00c3\3\2\2\2\25\u00c8\3\2\2\2\27\u00cb"+
		"\3\2\2\2\31\u00d1\3\2\2\2\33\u00d6\3\2\2\2\35\u00db\3\2\2\2\37\u00e2\3"+
		"\2\2\2!\u00e7\3\2\2\2#\u00ef\3\2\2\2%\u00f4\3\2\2\2\'\u00f7\3\2\2\2)\u00fc"+
		"\3\2\2\2+\u0101\3\2\2\2-\u0104\3\2\2\2/\u010c\3\2\2\2\61\u0110\3\2\2\2"+
		"\63\u0114\3\2\2\2\65\u0119\3\2\2\2\67\u011d\3\2\2\29\u0122\3\2\2\2;\u0127"+
		"\3\2\2\2=\u0130\3\2\2\2?\u0132\3\2\2\2A\u0137\3\2\2\2C\u013d\3\2\2\2E"+
		"\u013f\3\2\2\2G\u0143\3\2\2\2I\u0147\3\2\2\2K\u014b\3\2\2\2M\u014d\3\2"+
		"\2\2O\u014f\3\2\2\2Q\u0151\3\2\2\2S\u0153\3\2\2\2U\u0155\3\2\2\2W\u0157"+
		"\3\2\2\2Y\u015a\3\2\2\2[\u015c\3\2\2\2]\u015f\3\2\2\2_\u0162\3\2\2\2a"+
		"\u0165\3\2\2\2c\u0168\3\2\2\2e\u016b\3\2\2\2g\u016d\3\2\2\2i\u016f\3\2"+
		"\2\2k\u0171\3\2\2\2m\u0173\3\2\2\2o\u0175\3\2\2\2q\u0177\3\2\2\2s\u0179"+
		"\3\2\2\2u\u017b\3\2\2\2w\u017d\3\2\2\2y\u017f\3\2\2\2{\u0181\3\2\2\2}"+
		"\u0186\3\2\2\2\177\u0188\3\2\2\2\u0081\u018a\3\2\2\2\u0083\u018e\3\2\2"+
		"\2\u0085\u0190\3\2\2\2\u0087\u0199\3\2\2\2\u0089\u019b\3\2\2\2\u008b\u01a1"+
		"\3\2\2\2\u008d\u01a4\3\2\2\2\u008f\u01ab\3\2\2\2\u0091\u01c1\3\2\2\2\u0093"+
		"\u01c3\3\2\2\2\u0095\u01c7\3\2\2\2\u0097\u009b\5y=\2\u0098\u009a\n\2\2"+
		"\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c"+
		"\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\5\7\4\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a1\b\2\2\2\u00a1\4\3\2\2\2\u00a2\u00a4\t\3\2\2"+
		"\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\3\2\2\u00a8\6\3\2\2\2\u00a9"+
		"\u00aa\t\2\2\2\u00aa\b\3\2\2\2\u00ab\u00ac\7u\2\2\u00ac\u00ad\7m\2\2\u00ad"+
		"\u00ae\7k\2\2\u00ae\u00af\7r\2\2\u00af\n\3\2\2\2\u00b0\u00b1\7d\2\2\u00b1"+
		"\u00b2\7g\2\2\u00b2\u00b3\7i\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7p\2\2"+
		"\u00b5\f\3\2\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9\7f"+
		"\2\2\u00b9\16\3\2\2\2\u00ba\u00bb\7y\2\2\u00bb\u00bc\7j\2\2\u00bc\u00bd"+
		"\7k\2\2\u00bd\u00be\7n\2\2\u00be\u00bf\7g\2\2\u00bf\20\3\2\2\2\u00c0\u00c1"+
		"\7f\2\2\u00c1\u00c2\7q\2\2\u00c2\22\3\2\2\2\u00c3\u00c4\7f\2\2\u00c4\u00c5"+
		"\7q\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7g\2\2\u00c7\24\3\2\2\2\u00c8\u00c9"+
		"\7k\2\2\u00c9\u00ca\7u\2\2\u00ca\26\3\2\2\2\u00cb\u00cc\7r\2\2\u00cc\u00cd"+
		"\7t\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7p\2\2\u00cf\u00d0\7v\2\2\u00d0"+
		"\30\3\2\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7c\2\2\u00d4"+
		"\u00d5\7f\2\2\u00d5\32\3\2\2\2\u00d6\u00d7\7h\2\2\u00d7\u00d8\7t\2\2\u00d8"+
		"\u00d9\7g\2\2\u00d9\u00da\7g\2\2\u00da\34\3\2\2\2\u00db\u00dc\7t\2\2\u00dc"+
		"\u00dd\7g\2\2\u00dd\u00de\7v\2\2\u00de\u00df\7w\2\2\u00df\u00e0\7t\2\2"+
		"\u00e0\u00e1\7p\2\2\u00e1\36\3\2\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7"+
		"z\2\2\u00e4\u00e5\7k\2\2\u00e5\u00e6\7v\2\2\u00e6 \3\2\2\2\u00e7\u00e8"+
		"\7r\2\2\u00e8\u00e9\7t\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7p\2\2\u00eb"+
		"\u00ec\7v\2\2\u00ec\u00ed\7n\2\2\u00ed\u00ee\7p\2\2\u00ee\"\3\2\2\2\u00ef"+
		"\u00f0\7e\2\2\u00f0\u00f1\7c\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7n\2\2"+
		"\u00f3$\3\2\2\2\u00f4\u00f5\7k\2\2\u00f5\u00f6\7h\2\2\u00f6&\3\2\2\2\u00f7"+
		"\u00f8\7g\2\2\u00f8\u00f9\7n\2\2\u00f9\u00fa\7u\2\2\u00fa\u00fb\7g\2\2"+
		"\u00fb(\3\2\2\2\u00fc\u00fd\7v\2\2\u00fd\u00fe\7j\2\2\u00fe\u00ff\7g\2"+
		"\2\u00ff\u0100\7p\2\2\u0100*\3\2\2\2\u0101\u0102\7h\2\2\u0102\u0103\7"+
		"k\2\2\u0103,\3\2\2\2\u0104\u0105\7p\2\2\u0105\u0106\7g\2\2\u0106\u0107"+
		"\7y\2\2\u0107\u0108\7r\2\2\u0108\u0109\7c\2\2\u0109\u010a\7k\2\2\u010a"+
		"\u010b\7t\2\2\u010b.\3\2\2\2\u010c\u010d\7h\2\2\u010d\u010e\7u\2\2\u010e"+
		"\u010f\7v\2\2\u010f\60\3\2\2\2\u0110\u0111\7u\2\2\u0111\u0112\7p\2\2\u0112"+
		"\u0113\7f\2\2\u0113\62\3\2\2\2\u0114\u0115\7r\2\2\u0115\u0116\7c\2\2\u0116"+
		"\u0117\7k\2\2\u0117\u0118\7t\2\2\u0118\64\3\2\2\2\u0119\u011a\7k\2\2\u011a"+
		"\u011b\7p\2\2\u011b\u011c\7v\2\2\u011c\66\3\2\2\2\u011d\u011e\7d\2\2\u011e"+
		"\u011f\7q\2\2\u011f\u0120\7q\2\2\u0120\u0121\7n\2\2\u01218\3\2\2\2\u0122"+
		"\u0123\7e\2\2\u0123\u0124\7j\2\2\u0124\u0125\7c\2\2\u0125\u0126\7t\2\2"+
		"\u0126:\3\2\2\2\u0127\u0128\7u\2\2\u0128\u0129\7v\2\2\u0129\u012a\7t\2"+
		"\2\u012a\u012b\7k\2\2\u012b\u012c\7p\2\2\u012c\u012d\7i\2\2\u012d<\3\2"+
		"\2\2\u012e\u0131\5? \2\u012f\u0131\5A!\2\u0130\u012e\3\2\2\2\u0130\u012f"+
		"\3\2\2\2\u0131>\3\2\2\2\u0132\u0133\7v\2\2\u0133\u0134\7t\2\2\u0134\u0135"+
		"\7w\2\2\u0135\u0136\7g\2\2\u0136@\3\2\2\2\u0137\u0138\7h\2\2\u0138\u0139"+
		"\7c\2\2\u0139\u013a\7n\2\2\u013a\u013b\7u\2\2\u013b\u013c\7g\2\2\u013c"+
		"B\3\2\2\2\u013d\u013e\7#\2\2\u013eD\3\2\2\2\u013f\u0140\7n\2\2\u0140\u0141"+
		"\7g\2\2\u0141\u0142\7p\2\2\u0142F\3\2\2\2\u0143\u0144\7q\2\2\u0144\u0145"+
		"\7t\2\2\u0145\u0146\7f\2\2\u0146H\3\2\2\2\u0147\u0148\7e\2\2\u0148\u0149"+
		"\7j\2\2\u0149\u014a\7t\2\2\u014aJ\3\2\2\2\u014b\u014c\7,\2\2\u014cL\3"+
		"\2\2\2\u014d\u014e\7\61\2\2\u014eN\3\2\2\2\u014f\u0150\7\'\2\2\u0150P"+
		"\3\2\2\2\u0151\u0152\7-\2\2\u0152R\3\2\2\2\u0153\u0154\7/\2\2\u0154T\3"+
		"\2\2\2\u0155\u0156\7>\2\2\u0156V\3\2\2\2\u0157\u0158\7>\2\2\u0158\u0159"+
		"\7?\2\2\u0159X\3\2\2\2\u015a\u015b\7@\2\2\u015bZ\3\2\2\2\u015c\u015d\7"+
		"@\2\2\u015d\u015e\7?\2\2\u015e\\\3\2\2\2\u015f\u0160\7?\2\2\u0160\u0161"+
		"\7?\2\2\u0161^\3\2\2\2\u0162\u0163\7#\2\2\u0163\u0164\7?\2\2\u0164`\3"+
		"\2\2\2\u0165\u0166\7(\2\2\u0166\u0167\7(\2\2\u0167b\3\2\2\2\u0168\u0169"+
		"\7~\2\2\u0169\u016a\7~\2\2\u016ad\3\2\2\2\u016b\u016c\7*\2\2\u016cf\3"+
		"\2\2\2\u016d\u016e\7+\2\2\u016eh\3\2\2\2\u016f\u0170\7]\2\2\u0170j\3\2"+
		"\2\2\u0171\u0172\7_\2\2\u0172l\3\2\2\2\u0173\u0174\7=\2\2\u0174n\3\2\2"+
		"\2\u0175\u0176\7?\2\2\u0176p\3\2\2\2\u0177\u0178\7$\2\2\u0178r\3\2\2\2"+
		"\u0179\u017a\7)\2\2\u017at\3\2\2\2\u017b\u017c\7.\2\2\u017cv\3\2\2\2\u017d"+
		"\u017e\7^\2\2\u017ex\3\2\2\2\u017f\u0180\7%\2\2\u0180z\3\2\2\2\u0181\u0182"+
		"\7p\2\2\u0182\u0183\7w\2\2\u0183\u0184\7n\2\2\u0184\u0185\7n\2\2\u0185"+
		"|\3\2\2\2\u0186\u0187\4\62;\2\u0187~\3\2\2\2\u0188\u0189\t\4\2\2\u0189"+
		"\u0080\3\2\2\2\u018a\u018b\7a\2\2\u018b\u0082\3\2\2\2\u018c\u018f\n\5"+
		"\2\2\u018d\u018f\5\u0091I\2\u018e\u018c\3\2\2\2\u018e\u018d\3\2\2\2\u018f"+
		"\u0084\3\2\2\2\u0190\u0194\5\177@\2\u0191\u0193\5\u0087D\2\u0192\u0191"+
		"\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195"+
		"\u0086\3\2\2\2\u0196\u0194\3\2\2\2\u0197\u019a\5\177@\2\u0198\u019a\5"+
		"}?\2\u0199\u0197\3\2\2\2\u0199\u0198\3\2\2\2\u019a\u0088\3\2\2\2\u019b"+
		"\u019d\7\62\2\2\u019c\u019e\4\629\2\u019d\u019c\3\2\2\2\u019e\u019f\3"+
		"\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u008a\3\2\2\2\u01a1"+
		"\u01a2\7\62\2\2\u01a2\u01a3\7d\2\2\u01a3\u008c\3\2\2\2\u01a4\u01a6\5\u008b"+
		"F\2\u01a5\u01a7\4\62\63\2\u01a6\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8"+
		"\u01a6\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u008e\3\2\2\2\u01aa\u01ac\5}"+
		"?\2\u01ab\u01aa\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad"+
		"\u01ae\3\2\2\2\u01ae\u0090\3\2\2\2\u01af\u01b0\7^\2\2\u01b0\u01c2\7\62"+
		"\2\2\u01b1\u01b2\7^\2\2\u01b2\u01c2\7d\2\2\u01b3\u01b4\7^\2\2\u01b4\u01c2"+
		"\7v\2\2\u01b5\u01b6\7^\2\2\u01b6\u01c2\7p\2\2\u01b7\u01b8\7^\2\2\u01b8"+
		"\u01c2\7h\2\2\u01b9\u01ba\7^\2\2\u01ba\u01c2\7t\2\2\u01bb\u01bc\7^\2\2"+
		"\u01bc\u01c2\5q9\2\u01bd\u01be\7^\2\2\u01be\u01c2\5s:\2\u01bf\u01c0\7"+
		"^\2\2\u01c0\u01c2\5w<\2\u01c1\u01af\3\2\2\2\u01c1\u01b1\3\2\2\2\u01c1"+
		"\u01b3\3\2\2\2\u01c1\u01b5\3\2\2\2\u01c1\u01b7\3\2\2\2\u01c1\u01b9\3\2"+
		"\2\2\u01c1\u01bb\3\2\2\2\u01c1\u01bd\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2"+
		"\u0092\3\2\2\2\u01c3\u01c4\5s:\2\u01c4\u01c5\5\u0083B\2\u01c5\u01c6\5"+
		"s:\2\u01c6\u0094\3\2\2\2\u01c7\u01cb\5q9\2\u01c8\u01ca\5\u0083B\2\u01c9"+
		"\u01c8\3\2\2\2\u01ca\u01cd\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cb\u01cc\3\2"+
		"\2\2\u01cc\u01ce\3\2\2\2\u01cd\u01cb\3\2\2\2\u01ce\u01cf\5q9\2\u01cf\u0096"+
		"\3\2\2\2\16\2\u009b\u00a5\u0130\u018e\u0194\u0199\u019f\u01a8\u01ad\u01c1"+
		"\u01cb\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}