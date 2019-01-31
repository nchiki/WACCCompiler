parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

binaryOper: PLUS | MINUS | MOD | MULT | DIV | GREAT | GREAT_EQ | LESS | LESS_EQ | EQ | NOTEQ| AND | OR ;

unaryOper: LEN | ORD | CHR | EXCL | NEG ;

strLiter: DBL_QUOTES (character)* DBL_QUOTES ;

charLiter: QUOTE character QUOTE ;

argList: expr ( COMMA expr)* ;

paramList: param (COMMA param)* ;

param: type IDENT ;

func: type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;

stat: SKIP
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

type:  type OPEN_SQR_BRACKET CLOSE_SQR_BRACKET | baseType | pairType ;

baseType: INT | BOOL | CHAR | STRING;

pairELemType: baseType type PAIR ;

pairType: PAIR OPEN_PARENTHESES pairELemType COMMA pairELemType CLOSE_PARENTHESES ;

expr: expr binaryOper expr
| intLiter
| OPEN_PARENTHESES expr CLOSE_PARENTHESES
| boolean
| charLiter
| strLiter
| pairLiter
| IDENT
| arrayElem
| unaryOper expr
;

boolean: TRUE | FALSE ;

intSign: PLUS | MINUS ;

intLiter: intSign? INTEGER ;

arrayLiter: OPENOPEN_SQR_BRACKET (expr (COMMA expr)*)? CLOSE_SQR_BRACKET ;

character: ~(BACKSLASH|QUOTE|DBL_QUOTES) | BACKSLASH escChar ;

escChar: ZERO | B | T | N | F | R | DBL_QUOTES | QUOTE | BACKSLASH ;

pairLiter: NULL ;

comment: HASH ~(EOL)* EOL;

// EOF indicates that the program must consume to the end of the input.
prog: (expr)*  EOF ;
