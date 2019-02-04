parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}
prog: BEGIN func* stat END EOF ;

argList: expr ( COMMA expr)* ;

func: type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;

paramList: param (COMMA param)* ;

param: type IDENT ;

stat: SKIP_FUNC
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

type:  baseType | pairType |type OPEN_SQR_BRACKET CLOSE_SQR_BRACKET ;

baseType: INT | BOOL | CHAR | STRING;

pairType: PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;

pairElemType: baseType | type | PAIR ;

expr:
INT_LIT
| BOOL_LIT
| CHAR_LIT
| STR_LIT
| PAIR_LIT
| IDENT
| arrayElem
| unaryOper expr
| expr binaryOper expr
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

binaryOper:  MULT | DIV |  MOD | PLUS | MINUS | GREAT | GREAT_EQ | LESS | LESS_EQ | EQ | NOTEQ| AND | OR ;

unaryOper: NOT | MINUS | LEN | ORD | CHR ;

arrayLiter: OPEN_SQR_BRACKET (expr (COMMA expr)*)? CLOSE_SQR_BRACKET ;


// EOF indicates that the program must consume to the end of the input.

