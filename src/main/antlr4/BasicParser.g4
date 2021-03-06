parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}
prog: BEGIN func* stat END EOF ;

define: DEFINEMACRO IDENT INT_LIT ;

func: type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;

paramList: param (COMMA param)* ;

param: type IDENT ;

stat:
    SKIP_FUNC                       #Skip
| BREAK                             #Break
| CONTINUE                          #Continue
| IDENT PLUS PLUS                   #Increment
| IDENT MINUS MINUS                 #Decrement
| assignLHS EQUAL assignRHS         #Assign
| type IDENT EQUAL assignRHS        #Decl
| READ assignLHS                    #Read
| FREE expr                         #Free
| RETURN expr                       #Return
| EXIT expr                         #Exit
| PRINT expr                        #Print
| PRINTLN expr                      #Println
| IF expr THEN stat ELSE stat FI    #IfCond
| FOR forCond DO stat DONE          #ForLoop
| WHILE expr DO stat DONE           #While
| DO stat WHILE expr DONE           #DoWhile
| BEGIN stat END                    #Statement
| stat SEMICOLON stat               #StatList
| structElem                        #StructStat
;

forCond: OPEN_PARENTHESES stat SEMICOLON expr SEMICOLON stat CLOSE_PARENTHESES;

assignLHS:
   IDENT        #AssignL_Iden
| structLiter   #AssignL_StructLiter
| arrayElem     #AssignL_Array
| pairElem      #AssignL_Pairelem
;

assignRHS:
    expr                                                        #AssignR_Exp
| arrayLiter                                                    #AssignR_ArrayL
| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES    #AssignR_Pair
| pairElem                                                      #AssignR_Pair_Elem
| CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES        #AssignR_Call
;


argList: expr ( COMMA expr)* ;

pairElem: FST expr | SND expr ;


type:
    base_type                                       #BaseType
    | type OPEN_SQR_BRACKET CLOSE_SQR_BRACKET       #ArrayType
    | pair_type                                     #PairType
;

base_type: INT | BOOL | CHAR | STRING | FUNC ;

pair_type: PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES
| pair_Lit;

pairElemType: base_type | type | PAIR ;


addSub: PLUS | MINUS;
multDiv: MULT | DIV | MOD;
eq_Op: GREAT | GREAT_EQ | LESS | LESS_EQ | EQ | NOTEQ;
boolOp: AND | OR ;


expr:
unaryOper expr                              #UnOp
| structLiter                               #StructLit
| expr multDiv expr                         #MultDivOp
| expr addSub expr                          #AddSubOp
| expr eq_Op expr                           #EqOp
| unaryOper expr                            #UnOp
| expr boolOp expr                          #BoolOper
| INT_LIT                                   #IntLit
| BOOL_LIT                                  #BoolLit
| BINARY_LIT                                #BinaryLit
| OCTAL_LIT                                 #OctalLit
| HEXADEC_LIT                               #HexadecLit
| CHAR_LIT                                  #CharLit
| STR_LIT                                   #StrLit
| pair_Lit                                  #PairLit
| ident                                     #Id
| arrayElem                                 #Array_Elem
| OPEN_PARENTHESES expr CLOSE_PARENTHESES   #Paren
;

ident : IDENT ;

unaryOper: NOT | MINUS | PLUS | LEN | ORD | CHR ;

structLiter: IDENT FULL_STOP IDENT ;

structMember:
    type IDENT EQUAL assignRHS SEMICOLON ;

structElem: STRUCT IDENT OPEN_CRLY_BRACKET structMember+
            CLOSE_CRLY_BRACKET ;

arrayElem: IDENT (OPEN_SQR_BRACKET expr CLOSE_SQR_BRACKET)+ ;

arrayLiter: OPEN_SQR_BRACKET (expr (COMMA expr)*)? CLOSE_SQR_BRACKET ;

pair_Lit: NULL;

// EOF indicates that the program must consume to the end of the input.
