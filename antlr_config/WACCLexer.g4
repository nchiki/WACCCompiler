lexer grammar BasicLexer;

//binary operators
PLUS: '+' ;
MINUS: '-' ;
MULT: '*' ;
DIVIDE: '/' ;
MOD: '%' ;
SMALLER_THAN: '<' ;
SMALLER_EQUAL: '<=' ;
GREATER_THAN: '>' ;
GREATER_EQUAL: '>=' ;
EQUAL: '==' ;
NOT_EQUAL: '!=' ;
AND: '&&' ;
OR: '||' ;

//unary operators
LEN: 'len' ;
ORD: 'ord' ;
CHR: 'chr' ;
NOT: '!' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;

UNDERSCORE: '_' ;

//letters
fragment LETTER: 'a'..'Z' ;

//bool literals
TRUE: 'true' ;
FALSE: 'false' ;

//numbers
fragment DIGIT: '0'..'9' ; 

INTEGER: DIGIT+ ;

WHITESPACE : ' ' -> skip ;