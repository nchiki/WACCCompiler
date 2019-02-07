lexer grammar BasicLexer;

COMMENT: HASH ~([\r\n])* EOL -> skip;
//WHITESPACES
WS : [ \n\r\t]+ -> skip ; //channel(HIDDEN) ;
// EOL needs to be checked
EOL: [\r\n] ;

//WHITESPACE : ' ' -> skip ;

//commands
BEGIN: 'begin' ;
END: 'end' ;
IS: 'is' ;
SKIP_FUNC: 'skip' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINT: 'print' ;
PRINTLN: 'println' ;
//if statement
IF: 'if' ;
ELSE: 'else' ;
THEN: 'then' ;
FI: 'fi';
WHILE: 'while' ;
DO: 'do' ;
DONE: 'done' ;
CALL: 'call' ;



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


SEMICOLON: ';' ;
EQUAL: '=' ;

fragment DBL_QUOTES: '"' ;
fragment QUOTE: '\'';
COMMA: ',' ;


BACKSLASH: '\\' ;
HASH: '#' ;
fragment NULL: 'null' ;

//letters
fragment LETTER: '_'|'a'..'z'|'A'..'Z' ;

fragment UNDERSCORE: '_' ;

fragment CHARACTER: ~('\''| '"'| '\\') | BACKSLASH ESC_CHAR ;

PAIR_LIT: NULL ;


fragment IDENT_TAIL: LETTER|DIGIT ;
fragment INTSIGN: (PLUS | MINUS) ;

// literals
INT_LIT: (INTSIGN)? DIGIT+ ;

//numbers
fragment DIGIT: '0'..'9' ;

ESC_CHAR: '0' | 'b' | 't' | 'n' | 'f' | 'r' |  DBL_QUOTES |  QUOTE | BACKSLASH ;

CHAR_LIT: QUOTE CHARACTER QUOTE ;

STR_LIT: DBL_QUOTES (CHARACTER)* DBL_QUOTES ;
IDENT: LETTER (IDENT_TAIL)* ;









