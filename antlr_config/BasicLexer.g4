lexer grammar BasicLexer;

DBL_QUOTES: '"' ;
QUOTE: '\'';
COMMA: ',' ;

//binary operators
PLUS: '+' ;
MINUS: '-' ;
MULT: '*' ;
DIVIDE: '/' ;
MOD: '%' ;
LESS: '<' ;
LESS_EQ: '<=' ;
GREAT: '>' ;
GREAT_EQ: '>=' ;
EQ: '==' ;
NOTEQ: '!=' ;
AND: '&&' ;
OR: '||' ;

//escaped chars
ZERO: '0' ;
B: 'b' ;
T: 't' ;

//commands
SKIP_FUNC: 'skip' ; 
BEGIN: 'begin' ;
END: 'end' ;
WHILE: 'while' ;
DO: 'do' ;
PRINT: 'print' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINTLN: 'println' ;


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


//unary operators
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;
EXCL: '!' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_SQR_BRACKET: '[' ;
CLOSE_SQR_BRACKET: ']' ;

UNDERSCORE: '_' ;
SEMICOLON: ';' ;


BACKSLASH: '\\' ;
HASH: '#' ;
NULL: 'null' ;

//base types
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;

//letters
fragment LETTER: '_'|'a'..'z'|'A'..'Z' ;

//bool literals
TRUE: 'true' ;
FALSE: 'false' ;

//numbers
fragment DIGIT: '0'..'9' ; 

INTEGER: DIGIT+ ;

IDENT_TAIL: LETTER|DIGIT ;
IDENT: LETTER IDENT_TAIL* ;

WHITESPACE : ' ' -> skip ;