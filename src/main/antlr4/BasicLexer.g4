lexer grammar BasicLexer;

COMMENT: HASH ~([\r\n])* EOL -> skip;
//WHITESPACES
WS : [ \n\r\t]+ -> skip ; //channel(HIDDEN) ;
// EOL needs to be checked
EOL: [\r\n] ;

//WHITESPACE : ' ' -> skip ;


//commands
SKIP_FUNC: 'skip' ;
BREAK: 'break' ;
CONTINUE: 'continue' ;
BEGIN: 'begin' ;
END: 'end' ;
WHILE: 'while' ;
FOR: 'for';
DO: 'do' ;
DONE: 'done' ;
IS: 'is' ;
PRINT: 'print' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINTLN: 'println' ;
CALL: 'call' ;
DEFINEMACRO: '.define' ;

//if statement
IF: 'if' ;
ELSE: 'else' ;
THEN: 'then' ;
FI: 'fi';

//types
NEWPAIR: 'newpair' ;
FST: 'fst' ;
SND: 'snd' ;
PAIR: 'pair' ;
FUNC: 'func' ;

//base types
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;

BOOL_LIT: TRUE | FALSE ;

//bool literals
TRUE: 'true' ;
FALSE: 'false' ;

//struct keyword
STRUCT: 'struct' ;

//unary operators
NOT: '!' ;
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;

//binary operators
MULT: '*' ;
DIV: '/' ;
MOD: '%' ;
PLUS: '+' ;
MINUS: '-' ;
LESS: '<' ;
LESS_EQ: '<=' ;
GREAT: '>' ;
GREAT_EQ: '>=' ;
EQ: '==' ;
NOTEQ: '!=' ;
AND: '&&' ;
OR: '||' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_SQR_BRACKET: '[' ;
CLOSE_SQR_BRACKET: ']' ;
OPEN_CRLY_BRACKET: '{' ;
CLOSE_CRLY_BRACKET: '}' ;



SEMICOLON: ';' ;
EQUAL: '=' ;
FULL_STOP: '.' ;

fragment DBL_QUOTES: '"' ;
fragment QUOTE: '\'';
COMMA: ',' ;


BACKSLASH: '\\' ;
HASH: '#' ;
NULL: 'null' ;

//numbers
fragment DIGIT: '0'..'9' ;
//letters
fragment LETTER: '_'|'a'..'z'|'A'..'Z' ;

fragment UNDERSCORE: '_' ;

fragment CHARACTER: ~('\''| '"'| '\\') | ESC_CHAR ;


IDENT: LETTER (IDENT_TAIL)* ;
fragment IDENT_TAIL: LETTER|DIGIT ;

OCTAL_LIT: '0O' ('0'..'7')+;

fragment HEXADEC: '0x';
fragment HEX_LETTER: 'A'..'F';
HEXADEC_LIT: HEXADEC (DIGIT | HEX_LETTER)+;

fragment BINARY: '0b';
BINARY_LIT: BINARY ('0' | '1')+;

// literals
INT_LIT: DIGIT+ ;

ESC_CHAR: '\\0' | '\\b' | '\\t' | '\\n' | '\\f' | '\\r' | '\\' DBL_QUOTES | '\\' QUOTE | '\\' BACKSLASH ;

//STRUCT_LIT: IDENT FULL_STOP IDENT ;

CHAR_LIT: QUOTE CHARACTER QUOTE ;

STR_LIT: DBL_QUOTES (CHARACTER)* DBL_QUOTES ;


