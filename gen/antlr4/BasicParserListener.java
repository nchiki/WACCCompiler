// Generated from /home/nahida/Documents/Year2/WACC/wacc_25/src/main/antlr4/BasicParser.g4 by ANTLR 4.7.2
package antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BasicParser}.
 */
public interface BasicParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BasicParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(BasicParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(BasicParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(BasicParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(BasicParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(BasicParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(BasicParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(BasicParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(BasicParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturn(BasicParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturn(BasicParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Statement}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatement(BasicParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Statement}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatement(BasicParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Decl}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDecl(BasicParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Decl}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDecl(BasicParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code While}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile(BasicParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code While}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile(BasicParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Free}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFree(BasicParser.FreeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Free}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFree(BasicParser.FreeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Read}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterRead(BasicParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Read}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitRead(BasicParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfCond}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfCond(BasicParser.IfCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfCond}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfCond(BasicParser.IfCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Print}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrint(BasicParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrint(BasicParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatList}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatList(BasicParser.StatListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatList}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatList(BasicParser.StatListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Skip}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterSkip(BasicParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Skip}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitSkip(BasicParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Println}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintln(BasicParser.PrintlnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Println}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintln(BasicParser.PrintlnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(BasicParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(BasicParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Exit}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExit(BasicParser.ExitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Exit}
	 * labeled alternative in {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExit(BasicParser.ExitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignL_Iden}
	 * labeled alternative in {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignL_Iden(BasicParser.AssignL_IdenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignL_Iden}
	 * labeled alternative in {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignL_Iden(BasicParser.AssignL_IdenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignL_Array}
	 * labeled alternative in {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignL_Array(BasicParser.AssignL_ArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignL_Array}
	 * labeled alternative in {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignL_Array(BasicParser.AssignL_ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignL_Pairelem}
	 * labeled alternative in {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignL_Pairelem(BasicParser.AssignL_PairelemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignL_Pairelem}
	 * labeled alternative in {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignL_Pairelem(BasicParser.AssignL_PairelemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignR_Exp}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_Exp(BasicParser.AssignR_ExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignR_Exp}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_Exp(BasicParser.AssignR_ExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignR_ArrayL}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_ArrayL(BasicParser.AssignR_ArrayLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignR_ArrayL}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_ArrayL(BasicParser.AssignR_ArrayLContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignR_Pair}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_Pair(BasicParser.AssignR_PairContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignR_Pair}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_Pair(BasicParser.AssignR_PairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignR_Pair_Elem}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_Pair_Elem(BasicParser.AssignR_Pair_ElemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignR_Pair_Elem}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_Pair_Elem(BasicParser.AssignR_Pair_ElemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignR_Call}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignR_Call(BasicParser.AssignR_CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignR_Call}
	 * labeled alternative in {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignR_Call(BasicParser.AssignR_CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(BasicParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(BasicParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pairElem}.
	 * @param ctx the parse tree
	 */
	void enterPairElem(BasicParser.PairElemContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pairElem}.
	 * @param ctx the parse tree
	 */
	void exitPairElem(BasicParser.PairElemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayType}
	 * labeled alternative in {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(BasicParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayType}
	 * labeled alternative in {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(BasicParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PairType}
	 * labeled alternative in {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void enterPairType(BasicParser.PairTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PairType}
	 * labeled alternative in {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void exitPairType(BasicParser.PairTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BaseType}
	 * labeled alternative in {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(BasicParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BaseType}
	 * labeled alternative in {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(BasicParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void enterBase_type(BasicParser.Base_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#base_type}.
	 * @param ctx the parse tree
	 */
	void exitBase_type(BasicParser.Base_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pair_type}.
	 * @param ctx the parse tree
	 */
	void enterPair_type(BasicParser.Pair_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pair_type}.
	 * @param ctx the parse tree
	 */
	void exitPair_type(BasicParser.Pair_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pairElemType}.
	 * @param ctx the parse tree
	 */
	void enterPairElemType(BasicParser.PairElemTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pairElemType}.
	 * @param ctx the parse tree
	 */
	void exitPairElemType(BasicParser.PairElemTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#binaryOper}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOper(BasicParser.BinaryOperContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#binaryOper}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOper(BasicParser.BinaryOperContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#addSub}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(BasicParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#addSub}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(BasicParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#multDiv}.
	 * @param ctx the parse tree
	 */
	void enterMultDiv(BasicParser.MultDivContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#multDiv}.
	 * @param ctx the parse tree
	 */
	void exitMultDiv(BasicParser.MultDivContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#eq_Op}.
	 * @param ctx the parse tree
	 */
	void enterEq_Op(BasicParser.Eq_OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#eq_Op}.
	 * @param ctx the parse tree
	 */
	void exitEq_Op(BasicParser.Eq_OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(BasicParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(BasicParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Array_Elem}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArray_Elem(BasicParser.Array_ElemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Array_Elem}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArray_Elem(BasicParser.Array_ElemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultDivOp(BasicParser.MultDivOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultDivOp(BasicParser.MultDivOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CharLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharLit(BasicParser.CharLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CharLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharLit(BasicParser.CharLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PairLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPairLit(BasicParser.PairLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PairLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPairLit(BasicParser.PairLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubOp(BasicParser.AddSubOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubOp(BasicParser.AddSubOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqOp(BasicParser.EqOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqOp(BasicParser.EqOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnOp(BasicParser.UnOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnOp}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnOp(BasicParser.UnOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolOper(BasicParser.BoolOperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolOper}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolOper(BasicParser.BoolOperContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Id}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(BasicParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Id}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(BasicParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntLit(BasicParser.IntLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntLit(BasicParser.IntLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolLit(BasicParser.BoolLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolLit(BasicParser.BoolLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StrLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStrLit(BasicParser.StrLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StrLit}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStrLit(BasicParser.StrLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen(BasicParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Paren}
	 * labeled alternative in {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen(BasicParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(BasicParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(BasicParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOper(BasicParser.UnaryOperContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOper(BasicParser.UnaryOperContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#arrayElem}.
	 * @param ctx the parse tree
	 */
	void enterArrayElem(BasicParser.ArrayElemContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#arrayElem}.
	 * @param ctx the parse tree
	 */
	void exitArrayElem(BasicParser.ArrayElemContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#arrayLiter}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiter(BasicParser.ArrayLiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#arrayLiter}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiter(BasicParser.ArrayLiterContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pair_Lit}.
	 * @param ctx the parse tree
	 */
	void enterPair_Lit(BasicParser.Pair_LitContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pair_Lit}.
	 * @param ctx the parse tree
	 */
	void exitPair_Lit(BasicParser.Pair_LitContext ctx);
}