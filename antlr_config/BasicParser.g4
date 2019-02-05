parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}
prog: BEGIN func* stat END EOF ;

func: type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;

paramList: param (COMMA param)* ;

param: type IDENT ;

stat:
    SKIP_FUNC                       #Skip
| type IDENT EQUAL assignRHS        #Decl
| assignLHS EQUAL assignRHS         #Assign
| READ assignLHS                    #Read
| FREE expr                         #Free
| RETURN expr                       #Return
| EXIT expr                         #Exit
| PRINT expr                        #Print
| PRINTLN expr                      #Println
| IF expr THEN stat ELSE stat FI    #IfCond
| WHILE expr DO stat DONE           #While
| BEGIN stat END                    #Statement
| stat SEMICOLON stat               #StatList
;

assignLHS:
    IDENT       #Id
| arrayElem     #Array
| pairElem      #Pairelem
;

assignRHS:
    expr                                                        #Exp
| arrayLiter                                                    #ArrayL
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES    #Pair
| pairElem                                                      #Pair_Elem
| CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES        #Call
;


argList: expr ( COMMA expr)* ;

pairElem: FST expr | SND expr ;

arrayElem: IDENT (OPEN_SQR_BRACKET expr CLOSE_SQR_BRACKET)+ ;

type:
    base_type                                       #BaseType
    | type OPEN_SQR_BRACKET CLOSE_SQR_BRACKET       #ArrayType
    | pair_type                                     #PairType
;

base_type: INT | BOOL | CHAR | STRING;

pair_type: PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;

pairElemType: base_type | type | PAIR ;

expr:
    INT_LIT                                 #IntLit
| BOOL_LIT                                  #BoolLit
| CHAR_LIT                                  #CharLit
| STR_LIT                                   #StrLit
| PAIR_LIT                                  #PairLit
| IDENT                                     #Id
| arrayElem                                 #Array_Elem
| unaryOper expr                            #UnOp
| expr binaryOper expr                      #BinOper
| OPEN_PARENTHESES expr CLOSE_PARENTHESES   #Paren
;

unaryOper: NOT | MINUS | LEN | ORD | CHR ;

binaryOper:  MULT | DIV |  MOD | PLUS | MINUS | GREAT | GREAT_EQ | LESS | LESS_EQ | EQ | NOTEQ| AND | OR ;

arrayLiter: OPEN_SQR_BRACKET (expr (COMMA expr)*)? CLOSE_SQR_BRACKET ;


// EOF indicates that the program must consume to the end of the input.

