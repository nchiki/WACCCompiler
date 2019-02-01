// Generated from /Users/blancatebar/Documents/SecondYear/WACC/wacc_25/antlr_config/BasicParser.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BasicParser}.
 */
public interface BasicParserListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link BasicParser#strLiter}.
	 * @param ctx the parse tree
	 */
	void enterStrLiter(BasicParser.StrLiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#strLiter}.
	 * @param ctx the parse tree
	 */
	void exitStrLiter(BasicParser.StrLiterContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#charLiter}.
	 * @param ctx the parse tree
	 */
	void enterCharLiter(BasicParser.CharLiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#charLiter}.
	 * @param ctx the parse tree
	 */
	void exitCharLiter(BasicParser.CharLiterContext ctx);
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
	 * Enter a parse tree produced by {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(BasicParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(BasicParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignLHS(BasicParser.AssignLHSContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignLHS(BasicParser.AssignLHSContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void enterAssignRHS(BasicParser.AssignRHSContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 */
	void exitAssignRHS(BasicParser.AssignRHSContext ctx);
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
	 * Enter a parse tree produced by {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(BasicParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(BasicParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#baseType}.
	 * @param ctx the parse tree
	 */
	void enterBaseType(BasicParser.BaseTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#baseType}.
	 * @param ctx the parse tree
	 */
	void exitBaseType(BasicParser.BaseTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pairELemType}.
	 * @param ctx the parse tree
	 */
	void enterPairELemType(BasicParser.PairELemTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pairELemType}.
	 * @param ctx the parse tree
	 */
	void exitPairELemType(BasicParser.PairELemTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pairType}.
	 * @param ctx the parse tree
	 */
	void enterPairType(BasicParser.PairTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pairType}.
	 * @param ctx the parse tree
	 */
	void exitPairType(BasicParser.PairTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(BasicParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(BasicParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(BasicParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(BasicParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#intSign}.
	 * @param ctx the parse tree
	 */
	void enterIntSign(BasicParser.IntSignContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#intSign}.
	 * @param ctx the parse tree
	 */
	void exitIntSign(BasicParser.IntSignContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#intLiter}.
	 * @param ctx the parse tree
	 */
	void enterIntLiter(BasicParser.IntLiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#intLiter}.
	 * @param ctx the parse tree
	 */
	void exitIntLiter(BasicParser.IntLiterContext ctx);
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
	 * Enter a parse tree produced by {@link BasicParser#character}.
	 * @param ctx the parse tree
	 */
	void enterCharacter(BasicParser.CharacterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#character}.
	 * @param ctx the parse tree
	 */
	void exitCharacter(BasicParser.CharacterContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#escChar}.
	 * @param ctx the parse tree
	 */
	void enterEscChar(BasicParser.EscCharContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#escChar}.
	 * @param ctx the parse tree
	 */
	void exitEscChar(BasicParser.EscCharContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#pairLiter}.
	 * @param ctx the parse tree
	 */
	void enterPairLiter(BasicParser.PairLiterContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#pairLiter}.
	 * @param ctx the parse tree
	 */
	void exitPairLiter(BasicParser.PairLiterContext ctx);
	/**
	 * Enter a parse tree produced by {@link BasicParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(BasicParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BasicParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(BasicParser.CommentContext ctx);
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
}