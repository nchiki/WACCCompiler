lexer grammar BasicLexer;

WHITESPACE : ' ' -> skip ;
COMMENT: HASH ~([\r\n])* EOL -> skip;
// EOL needs to be checked
EOL: [\r\n] ;

//commands
SKIP_FUNC: 'skip' ;
BEGIN: 'begin' ;
END: 'end' ;
WHILE: 'while' ;
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

//base types
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;

BOOL_LIT: TRUE | FALSE ;

//bool literals
TRUE: 'true' ;
FALSE: 'false' ;


//unary operators
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;
NOT: '!' ;

//binary operators
PLUS: '+' ;
MINUS: '-' ;
MULT: '*' ;
DIV: '/' ;
MOD: '%' ;
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


SEMICOLON: ';' ;
EQUAL: '=' ;

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

UNDERSCORE: '_' ;


fragment ESC_CHAR: ZERO | B | T | N | F | R | DBL_QUOTES | QUOTE | BACKSLASH ;

//escaped chars
ZERO: '0' ;
B: 'b' ;
T: 't' ;
N: 'n' ;
F: 'f' ;
R: 'r' ;

fragment CHARACTER: ~('\''| '"'| '\\') | BACKSLASH ESC_CHAR ;
IDENT_TAIL: LETTER|DIGIT ;
IDENT: LETTER (IDENT_TAIL)* ;

// literals
INT_LIT: INT_SIGN? INTEGER ;
STR_LIT: DBL_QUOTES (CHAR_LIT)* DBL_QUOTES ;
CHAR_LIT: QUOTE CHARACTER QUOTE ;
PAIR_LIT: NULL ;


INTEGER: DIGIT+ ;
INT_SIGN: PLUS | MINUS ;



