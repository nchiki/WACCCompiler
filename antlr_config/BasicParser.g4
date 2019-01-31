parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

binaryOper: PLUS | MINUS ;

unaryOper: LEN | ORD | CHR | EXCL | NEG ;

strLiter: DBL_QUOTES (CHAR)* DBL_QUOTES ;

charLiter: QUOTE CHAR QUOTE ;

argList: expr ( COMMA expr)* ;

paramList: param (COMMA param)* ;

param: type IDENT ;

func: type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;

stat: skip
| type IDENT EQUAL assignRHS
| assignLHS EQUAL assignRHS
| READ assignLHS
| FREE expr
| RETURN expr
| EXIT expr
| PRINT expr
| PRINTLN expr
| IF expr THEN stat ELSE stat FI
| WHILE expr DO stat DONE
| BEGIN stat END
| stat SEMICOLON stat
;

assignLHS: IDENT
| arrayElem
| pairElem
;

assignRHS: expr
| arrayLiter
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
| pairElem
| CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES
;

arrayElem: IDENT (OPEN_SQR_BRACKET expr CLOSE_SQR_BRACKET)+ ;

pairElem: FST expr | SND expr ;

type: baseType | arrayType | pairType ;

baseType: INT | BOOL | CHAR | STRING;

arrayType: type OPEN_SQR_BRACKET CLOSE_SQR_BRACKET


expr: expr binaryOper expr
| INTEGER
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
| BOOL
| charLiter
| strLiter
| pairElem
| IDENT
| arrayElem
| unaryOper expr
;

// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;
