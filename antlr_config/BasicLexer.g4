lexer grammar BasicLexer;

//operators
PLUS: '+' ;
MINUS: '-' ;
MULT: '*' ;
DIVIDE: '/' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;

//numbers
fragment DIGIT: '0'..'9' ; 

INTEGER: DIGIT+ ;

WHITESPACE : ' ' -> skip ;