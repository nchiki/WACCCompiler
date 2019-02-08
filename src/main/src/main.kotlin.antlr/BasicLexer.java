// Generated from ./BasicLexer.g4 by ANTLR 4.7
package main.kotlin.antlr;
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WS=2, EOL=3, SKIP_FUNC=4, BEGIN=5, END=6, WHILE=7, DO=8, DONE=9, 
		IS=10, PRINT=11, READ=12, FREE=13, RETURN=14, EXIT=15, PRINTLN=16, CALL=17, 
		IF=18, ELSE=19, THEN=20, FI=21, NEWPAIR=22, FST=23, SND=24, PAIR=25, INT=26, 
		BOOL=27, CHAR=28, STRING=29, BOOL_LIT=30, TRUE=31, FALSE=32, LEN=33, ORD=34, 
		CHR=35, NOT=36, PLUS=37, MINUS=38, MULT=39, DIV=40, MOD=41, LESS=42, LESS_EQ=43, 
		GREAT=44, GREAT_EQ=45, EQ=46, NOTEQ=47, AND=48, OR=49, OPEN_PARENTHESES=50, 
		CLOSE_PARENTHESES=51, OPEN_SQR_BRACKET=52, CLOSE_SQR_BRACKET=53, SEMICOLON=54, 
		EQUAL=55, COMMA=56, BACKSLASH=57, HASH=58, NULL=59, UNDERSCORE=60, ZERO=61, 
		B=62, T=63, N=64, F=65, R=66, IDENT=67, IDENT_TAIL=68, INT_LIT=69, STR_LIT=70, 
		CHAR_LIT=71, PAIR_LIT=72, INTEGER=73, INT_SIGN=74;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMENT", "WS", "EOL", "SKIP_FUNC", "BEGIN", "END", "WHILE", "DO", "DONE", 
		"IS", "PRINT", "READ", "FREE", "RETURN", "EXIT", "PRINTLN", "CALL", "IF", 
		"ELSE", "THEN", "FI", "NEWPAIR", "FST", "SND", "PAIR", "INT", "BOOL", 
		"CHAR", "STRING", "BOOL_LIT", "TRUE", "FALSE", "LEN", "ORD", "CHR", "NOT", 
		"PLUS", "MINUS", "MULT", "DIV", "MOD", "LESS", "LESS_EQ", "GREAT", "GREAT_EQ", 
		"EQ", "NOTEQ", "AND", "OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQR_BRACKET", 
		"CLOSE_SQR_BRACKET", "SEMICOLON", "EQUAL", "DBL_QUOTES", "QUOTE", "COMMA", 
		"BACKSLASH", "HASH", "NULL", "DIGIT", "LETTER", "UNDERSCORE", "ESC_CHAR", 
		"ZERO", "B", "T", "N", "F", "R", "CHARACTER", "IDENT", "IDENT_TAIL", "INT_LIT", 
		"STR_LIT", "CHAR_LIT", "PAIR_LIT", "INTEGER", "INT_SIGN"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'skip'", "'begin'", "'end'", "'while'", "'do'", 
		"'done'", "'is'", "'print'", "'read'", "'free'", "'return'", "'exit'", 
		"'println'", "'call'", "'if'", "'else'", "'then'", "'fi'", "'newpair'", 
		"'fst'", "'snd'", "'pair'", "'int'", "'bool'", "'char'", "'string'", null, 
		"'true'", "'false'", "'len'", "'ord'", "'chr'", "'!'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'&&'", "'||'", 
		"'('", "')'", "'['", "']'", "';'", "'='", "','", "'\\'", "'#'", "'null'", 
		"'_'", "'0'", "'b'", "'t'", "'n'", "'f'", "'r'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "WS", "EOL", "SKIP_FUNC", "BEGIN", "END", "WHILE", "DO", 
		"DONE", "IS", "PRINT", "READ", "FREE", "RETURN", "EXIT", "PRINTLN", "CALL", 
		"IF", "ELSE", "THEN", "FI", "NEWPAIR", "FST", "SND", "PAIR", "INT", "BOOL", 
		"CHAR", "STRING", "BOOL_LIT", "TRUE", "FALSE", "LEN", "ORD", "CHR", "NOT", 
		"PLUS", "MINUS", "MULT", "DIV", "MOD", "LESS", "LESS_EQ", "GREAT", "GREAT_EQ", 
		"EQ", "NOTEQ", "AND", "OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQR_BRACKET", 
		"CLOSE_SQR_BRACKET", "SEMICOLON", "EQUAL", "COMMA", "BACKSLASH", "HASH", 
		"NULL", "UNDERSCORE", "ZERO", "B", "T", "N", "F", "R", "IDENT", "IDENT_TAIL", 
		"INT_LIT", "STR_LIT", "CHAR_LIT", "PAIR_LIT", "INTEGER", "INT_SIGN"
	};
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2L\u01dd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\3\2\3\2\7\2\u00a6"+
		"\n\2\f\2\16\2\u00a9\13\2\3\2\3\2\3\2\3\2\3\3\6\3\u00b0\n\3\r\3\16\3\u00b1"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\37\3\37\5\37\u013d\n\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\""+
		"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3"+
		"*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61"+
		"\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\3"+
		"8\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3>\3>\3>\3?\3?\3@\3@\3A\3A\3B\3"+
		"B\3B\3B\3B\3B\3B\3B\3B\5B\u01a2\nB\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3"+
		"H\3I\3I\3I\3I\5I\u01b4\nI\3J\3J\7J\u01b8\nJ\fJ\16J\u01bb\13J\3K\3K\5K"+
		"\u01bf\nK\3L\5L\u01c2\nL\3L\3L\3M\3M\7M\u01c8\nM\fM\16M\u01cb\13M\3M\3"+
		"M\3N\3N\3N\3N\3O\3O\3P\6P\u01d6\nP\rP\16P\u01d7\3Q\3Q\5Q\u01dc\nQ\2\2"+
		"R\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o"+
		"9q\2s\2u:w;y<{=}\2\177\2\u0081>\u0083\2\u0085?\u0087@\u0089A\u008bB\u008d"+
		"C\u008fD\u0091\2\u0093E\u0095F\u0097G\u0099H\u009bI\u009dJ\u009fK\u00a1"+
		"L\3\2\6\4\2\f\f\17\17\5\2\13\f\17\17\"\"\5\2C\\aac|\5\2$$))^^\2\u01e8"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3"+
		"\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2"+
		"\2\2o\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2\u0081\3\2"+
		"\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2"+
		"\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097"+
		"\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2"+
		"\2\2\u00a1\3\2\2\2\3\u00a3\3\2\2\2\5\u00af\3\2\2\2\7\u00b5\3\2\2\2\t\u00b7"+
		"\3\2\2\2\13\u00bc\3\2\2\2\r\u00c2\3\2\2\2\17\u00c6\3\2\2\2\21\u00cc\3"+
		"\2\2\2\23\u00cf\3\2\2\2\25\u00d4\3\2\2\2\27\u00d7\3\2\2\2\31\u00dd\3\2"+
		"\2\2\33\u00e2\3\2\2\2\35\u00e7\3\2\2\2\37\u00ee\3\2\2\2!\u00f3\3\2\2\2"+
		"#\u00fb\3\2\2\2%\u0100\3\2\2\2\'\u0103\3\2\2\2)\u0108\3\2\2\2+\u010d\3"+
		"\2\2\2-\u0110\3\2\2\2/\u0118\3\2\2\2\61\u011c\3\2\2\2\63\u0120\3\2\2\2"+
		"\65\u0125\3\2\2\2\67\u0129\3\2\2\29\u012e\3\2\2\2;\u0133\3\2\2\2=\u013c"+
		"\3\2\2\2?\u013e\3\2\2\2A\u0143\3\2\2\2C\u0149\3\2\2\2E\u014d\3\2\2\2G"+
		"\u0151\3\2\2\2I\u0155\3\2\2\2K\u0157\3\2\2\2M\u0159\3\2\2\2O\u015b\3\2"+
		"\2\2Q\u015d\3\2\2\2S\u015f\3\2\2\2U\u0161\3\2\2\2W\u0163\3\2\2\2Y\u0166"+
		"\3\2\2\2[\u0168\3\2\2\2]\u016b\3\2\2\2_\u016e\3\2\2\2a\u0171\3\2\2\2c"+
		"\u0174\3\2\2\2e\u0177\3\2\2\2g\u0179\3\2\2\2i\u017b\3\2\2\2k\u017d\3\2"+
		"\2\2m\u017f\3\2\2\2o\u0181\3\2\2\2q\u0183\3\2\2\2s\u0185\3\2\2\2u\u0187"+
		"\3\2\2\2w\u0189\3\2\2\2y\u018b\3\2\2\2{\u018d\3\2\2\2}\u0192\3\2\2\2\177"+
		"\u0194\3\2\2\2\u0081\u0196\3\2\2\2\u0083\u01a1\3\2\2\2\u0085\u01a3\3\2"+
		"\2\2\u0087\u01a5\3\2\2\2\u0089\u01a7\3\2\2\2\u008b\u01a9\3\2\2\2\u008d"+
		"\u01ab\3\2\2\2\u008f\u01ad\3\2\2\2\u0091\u01b3\3\2\2\2\u0093\u01b5\3\2"+
		"\2\2\u0095\u01be\3\2\2\2\u0097\u01c1\3\2\2\2\u0099\u01c5\3\2\2\2\u009b"+
		"\u01ce\3\2\2\2\u009d\u01d2\3\2\2\2\u009f\u01d5\3\2\2\2\u00a1\u01db\3\2"+
		"\2\2\u00a3\u00a7\5y=\2\u00a4\u00a6\n\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9"+
		"\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00a7\3\2\2\2\u00aa\u00ab\5\7\4\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\2"+
		"\2\2\u00ad\4\3\2\2\2\u00ae\u00b0\t\3\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b1"+
		"\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\u00b4\b\3\2\2\u00b4\6\3\2\2\2\u00b5\u00b6\t\2\2\2\u00b6\b\3\2\2\2\u00b7"+
		"\u00b8\7u\2\2\u00b8\u00b9\7m\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7r\2\2"+
		"\u00bb\n\3\2\2\2\u00bc\u00bd\7d\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7i"+
		"\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1\7p\2\2\u00c1\f\3\2\2\2\u00c2\u00c3"+
		"\7g\2\2\u00c3\u00c4\7p\2\2\u00c4\u00c5\7f\2\2\u00c5\16\3\2\2\2\u00c6\u00c7"+
		"\7y\2\2\u00c7\u00c8\7j\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7n\2\2\u00ca"+
		"\u00cb\7g\2\2\u00cb\20\3\2\2\2\u00cc\u00cd\7f\2\2\u00cd\u00ce\7q\2\2\u00ce"+
		"\22\3\2\2\2\u00cf\u00d0\7f\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7p\2\2\u00d2"+
		"\u00d3\7g\2\2\u00d3\24\3\2\2\2\u00d4\u00d5\7k\2\2\u00d5\u00d6\7u\2\2\u00d6"+
		"\26\3\2\2\2\u00d7\u00d8\7r\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da\7k\2\2\u00da"+
		"\u00db\7p\2\2\u00db\u00dc\7v\2\2\u00dc\30\3\2\2\2\u00dd\u00de\7t\2\2\u00de"+
		"\u00df\7g\2\2\u00df\u00e0\7c\2\2\u00e0\u00e1\7f\2\2\u00e1\32\3\2\2\2\u00e2"+
		"\u00e3\7h\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7g\2\2"+
		"\u00e6\34\3\2\2\2\u00e7\u00e8\7t\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea\7"+
		"v\2\2\u00ea\u00eb\7w\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7p\2\2\u00ed\36"+
		"\3\2\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7z\2\2\u00f0\u00f1\7k\2\2\u00f1"+
		"\u00f2\7v\2\2\u00f2 \3\2\2\2\u00f3\u00f4\7r\2\2\u00f4\u00f5\7t\2\2\u00f5"+
		"\u00f6\7k\2\2\u00f6\u00f7\7p\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7n\2\2"+
		"\u00f9\u00fa\7p\2\2\u00fa\"\3\2\2\2\u00fb\u00fc\7e\2\2\u00fc\u00fd\7c"+
		"\2\2\u00fd\u00fe\7n\2\2\u00fe\u00ff\7n\2\2\u00ff$\3\2\2\2\u0100\u0101"+
		"\7k\2\2\u0101\u0102\7h\2\2\u0102&\3\2\2\2\u0103\u0104\7g\2\2\u0104\u0105"+
		"\7n\2\2\u0105\u0106\7u\2\2\u0106\u0107\7g\2\2\u0107(\3\2\2\2\u0108\u0109"+
		"\7v\2\2\u0109\u010a\7j\2\2\u010a\u010b\7g\2\2\u010b\u010c\7p\2\2\u010c"+
		"*\3\2\2\2\u010d\u010e\7h\2\2\u010e\u010f\7k\2\2\u010f,\3\2\2\2\u0110\u0111"+
		"\7p\2\2\u0111\u0112\7g\2\2\u0112\u0113\7y\2\2\u0113\u0114\7r\2\2\u0114"+
		"\u0115\7c\2\2\u0115\u0116\7k\2\2\u0116\u0117\7t\2\2\u0117.\3\2\2\2\u0118"+
		"\u0119\7h\2\2\u0119\u011a\7u\2\2\u011a\u011b\7v\2\2\u011b\60\3\2\2\2\u011c"+
		"\u011d\7u\2\2\u011d\u011e\7p\2\2\u011e\u011f\7f\2\2\u011f\62\3\2\2\2\u0120"+
		"\u0121\7r\2\2\u0121\u0122\7c\2\2\u0122\u0123\7k\2\2\u0123\u0124\7t\2\2"+
		"\u0124\64\3\2\2\2\u0125\u0126\7k\2\2\u0126\u0127\7p\2\2\u0127\u0128\7"+
		"v\2\2\u0128\66\3\2\2\2\u0129\u012a\7d\2\2\u012a\u012b\7q\2\2\u012b\u012c"+
		"\7q\2\2\u012c\u012d\7n\2\2\u012d8\3\2\2\2\u012e\u012f\7e\2\2\u012f\u0130"+
		"\7j\2\2\u0130\u0131\7c\2\2\u0131\u0132\7t\2\2\u0132:\3\2\2\2\u0133\u0134"+
		"\7u\2\2\u0134\u0135\7v\2\2\u0135\u0136\7t\2\2\u0136\u0137\7k\2\2\u0137"+
		"\u0138\7p\2\2\u0138\u0139\7i\2\2\u0139<\3\2\2\2\u013a\u013d\5? \2\u013b"+
		"\u013d\5A!\2\u013c\u013a\3\2\2\2\u013c\u013b\3\2\2\2\u013d>\3\2\2\2\u013e"+
		"\u013f\7v\2\2\u013f\u0140\7t\2\2\u0140\u0141\7w\2\2\u0141\u0142\7g\2\2"+
		"\u0142@\3\2\2\2\u0143\u0144\7h\2\2\u0144\u0145\7c\2\2\u0145\u0146\7n\2"+
		"\2\u0146\u0147\7u\2\2\u0147\u0148\7g\2\2\u0148B\3\2\2\2\u0149\u014a\7"+
		"n\2\2\u014a\u014b\7g\2\2\u014b\u014c\7p\2\2\u014cD\3\2\2\2\u014d\u014e"+
		"\7q\2\2\u014e\u014f\7t\2\2\u014f\u0150\7f\2\2\u0150F\3\2\2\2\u0151\u0152"+
		"\7e\2\2\u0152\u0153\7j\2\2\u0153\u0154\7t\2\2\u0154H\3\2\2\2\u0155\u0156"+
		"\7#\2\2\u0156J\3\2\2\2\u0157\u0158\7-\2\2\u0158L\3\2\2\2\u0159\u015a\7"+
		"/\2\2\u015aN\3\2\2\2\u015b\u015c\7,\2\2\u015cP\3\2\2\2\u015d\u015e\7\61"+
		"\2\2\u015eR\3\2\2\2\u015f\u0160\7\'\2\2\u0160T\3\2\2\2\u0161\u0162\7>"+
		"\2\2\u0162V\3\2\2\2\u0163\u0164\7>\2\2\u0164\u0165\7?\2\2\u0165X\3\2\2"+
		"\2\u0166\u0167\7@\2\2\u0167Z\3\2\2\2\u0168\u0169\7@\2\2\u0169\u016a\7"+
		"?\2\2\u016a\\\3\2\2\2\u016b\u016c\7?\2\2\u016c\u016d\7?\2\2\u016d^\3\2"+
		"\2\2\u016e\u016f\7#\2\2\u016f\u0170\7?\2\2\u0170`\3\2\2\2\u0171\u0172"+
		"\7(\2\2\u0172\u0173\7(\2\2\u0173b\3\2\2\2\u0174\u0175\7~\2\2\u0175\u0176"+
		"\7~\2\2\u0176d\3\2\2\2\u0177\u0178\7*\2\2\u0178f\3\2\2\2\u0179\u017a\7"+
		"+\2\2\u017ah\3\2\2\2\u017b\u017c\7]\2\2\u017cj\3\2\2\2\u017d\u017e\7_"+
		"\2\2\u017el\3\2\2\2\u017f\u0180\7=\2\2\u0180n\3\2\2\2\u0181\u0182\7?\2"+
		"\2\u0182p\3\2\2\2\u0183\u0184\7$\2\2\u0184r\3\2\2\2\u0185\u0186\7)\2\2"+
		"\u0186t\3\2\2\2\u0187\u0188\7.\2\2\u0188v\3\2\2\2\u0189\u018a\7^\2\2\u018a"+
		"x\3\2\2\2\u018b\u018c\7%\2\2\u018cz\3\2\2\2\u018d\u018e\7p\2\2\u018e\u018f"+
		"\7w\2\2\u018f\u0190\7n\2\2\u0190\u0191\7n\2\2\u0191|\3\2\2\2\u0192\u0193"+
		"\4\62;\2\u0193~\3\2\2\2\u0194\u0195\t\4\2\2\u0195\u0080\3\2\2\2\u0196"+
		"\u0197\7a\2\2\u0197\u0082\3\2\2\2\u0198\u01a2\5\u0085C\2\u0199\u01a2\5"+
		"\u0087D\2\u019a\u01a2\5\u0089E\2\u019b\u01a2\5\u008bF\2\u019c\u01a2\5"+
		"\u008dG\2\u019d\u01a2\5\u008fH\2\u019e\u01a2\5q9\2\u019f\u01a2\5s:\2\u01a0"+
		"\u01a2\5w<\2\u01a1\u0198\3\2\2\2\u01a1\u0199\3\2\2\2\u01a1\u019a\3\2\2"+
		"\2\u01a1\u019b\3\2\2\2\u01a1\u019c\3\2\2\2\u01a1\u019d\3\2\2\2\u01a1\u019e"+
		"\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a0\3\2\2\2\u01a2\u0084\3\2\2\2\u01a3"+
		"\u01a4\7\62\2\2\u01a4\u0086\3\2\2\2\u01a5\u01a6\7d\2\2\u01a6\u0088\3\2"+
		"\2\2\u01a7\u01a8\7v\2\2\u01a8\u008a\3\2\2\2\u01a9\u01aa\7p\2\2\u01aa\u008c"+
		"\3\2\2\2\u01ab\u01ac\7h\2\2\u01ac\u008e\3\2\2\2\u01ad\u01ae\7t\2\2\u01ae"+
		"\u0090\3\2\2\2\u01af\u01b4\n\5\2\2\u01b0\u01b1\5w<\2\u01b1\u01b2\5\u0083"+
		"B\2\u01b2\u01b4\3\2\2\2\u01b3\u01af\3\2\2\2\u01b3\u01b0\3\2\2\2\u01b4"+
		"\u0092\3\2\2\2\u01b5\u01b9\5\177@\2\u01b6\u01b8\5\u0095K\2\u01b7\u01b6"+
		"\3\2\2\2\u01b8\u01bb\3\2\2\2\u01b9\u01b7\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba"+
		"\u0094\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc\u01bf\5\177@\2\u01bd\u01bf\5"+
		"}?\2\u01be\u01bc\3\2\2\2\u01be\u01bd\3\2\2\2\u01bf\u0096\3\2\2\2\u01c0"+
		"\u01c2\5\u00a1Q\2\u01c1\u01c0\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3"+
		"\3\2\2\2\u01c3\u01c4\5\u009fP\2\u01c4\u0098\3\2\2\2\u01c5\u01c9\5q9\2"+
		"\u01c6\u01c8\5\u009bN\2\u01c7\u01c6\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9"+
		"\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01cc\3\2\2\2\u01cb\u01c9\3\2"+
		"\2\2\u01cc\u01cd\5q9\2\u01cd\u009a\3\2\2\2\u01ce\u01cf\5s:\2\u01cf\u01d0"+
		"\5\u0091I\2\u01d0\u01d1\5s:\2\u01d1\u009c\3\2\2\2\u01d2\u01d3\5{>\2\u01d3"+
		"\u009e\3\2\2\2\u01d4\u01d6\5}?\2\u01d5\u01d4\3\2\2\2\u01d6\u01d7\3\2\2"+
		"\2\u01d7\u01d5\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u00a0\3\2\2\2\u01d9\u01dc"+
		"\5K&\2\u01da\u01dc\5M\'\2\u01db\u01d9\3\2\2\2\u01db\u01da\3\2\2\2\u01dc"+
		"\u00a2\3\2\2\2\16\2\u00a7\u00b1\u013c\u01a1\u01b3\u01b9\u01be\u01c1\u01c9"+
		"\u01d7\u01db\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}