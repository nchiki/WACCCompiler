lexer grammar WACCLexer;

DBL_QUOTES: '"' ;
QUOTE: ''' ;
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


//unary operators
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;
EXCL: '!' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;

UNDERSCORE: '_' ;

OPEN_SQR_BRACKET: '[' ;
CLOSE_SQR_BRACKET: ']' ;

BACKSLASH: '\"' ;

HASH: '#' ;

NULL: 'null' ;


//base types
INT: 'int' ;
BOOL: 'bool' ;
CHAR: 'char' ;
STRING: 'string' ;

//letters
fragment LETTER: 'a'..'Z' ;

//bool literals
TRUE: 'true' ;
FALSE: 'false' ;

//numbers
fragment DIGIT: '0'..'9' ; 

INTEGER: DIGIT+ ;

WHITESPACE : ' ' -> skip ;