import org.jetbrains.annotations.NotNull
import main.kotlin.Nodes.*

class WaccVisitor : BasicParserBaseVisitor<Node>() {
    val globalTable = ScopeTable(null)

    override fun visitProg(ctx: BasicParser.ProgContext?): Node? {
        //create symbol tables and initialize stuff etc.
        /*val globalTable = ScopeTable(null)
        val symbol_table = SymbolTable()
        symbol_table.addTable(globalTable)*/
        return ProgNode()
    }

    override fun visitFunc(ctx: BasicParser.FuncContext?): Node {
        return super.visitFunc(ctx)
    }

    //IdentNode needs to be constructed with Identifier (constructor of IdentNode not done yet)
    override fun visitId(ctx: BasicParser.IdContext): Node? {
        val id = ctx.IDENT().text
        /* uncomment when Symbol Table is done */
        return globalTable.lookupSymbol(id)

    }

    override fun visitFunc(ctx: BasicParser.FuncContext?): Node {
        TODO()
    }


    //IntNode needs to be constructed with val of int
    override fun visitIntLit(@NotNull ctx: BasicParser.IntLitContext): Node? {
        val int_val = Integer.valueOf(ctx.INT_LIT().text)
        return IntLitNode()
    }

    override fun visitAssign(@NotNull ctx: BasicParser.AssignContext): Node? {
        val id = visit(ctx.assignLHS())
        val value = visit(ctx.assignRHS())
        /* uncomment when symbol table is implemented */
        //memory.put(id, value);
        return AssignNode()
    }

    override fun visitArrayType(@NotNull ctx: BasicParser.ArrayTypeContext): Node? {
        val type = visit(ctx.type())
        return ArrayTypeNode()
    }

    override fun visitPair_type(@NotNull ctx: BasicParser.Pair_typeContext): Node? {
        val fst = visit(ctx.pairElemType(0))
        val snd = visit(ctx.pairElemType(1))
        return PairNode(fst, snd)
    }

    override fun visitArrayElem(ctx: BasicParser.ArrayElemContext?): Node? {
        val idType = visit(ctx?.IDENT())
        return null
    }

    override fun visitArgList(ctx: BasicParser.ArgListContext?): Node {
        return super.visitArgList(ctx)
    }

    override fun visitArrayLiter(ctx: BasicParser.ArrayLiterContext?): Node {
        return super.visitArrayLiter(ctx)
    }

    override fun visitBinaryOper(ctx: BasicParser.BinaryOperContext?): Node {
        return super.visitBinaryOper(ctx)
    }

    override fun visitStatList(ctx: BasicParser.StatListContext?): Node {
        return super.visitStatList(ctx)
    }

    override fun visitUnaryOper(ctx: BasicParser.UnaryOperContext?): Node {
        return super.visitUnaryOper(ctx)
    }

    override fun visitDecl(ctx: BasicParser.DeclContext?): Node {
        return super.visitDecl(ctx)
    }

    override fun visitIfCond(ctx: BasicParser.IfCondContext?): Node {
        return super.visitIfCond(ctx)
    }


    override fun visitPrint(ctx: BasicParser.PrintContext?): Node {
        return super.visitPrint(ctx)
    }

    override fun visitBoolLit(ctx: BasicParser.BoolLitContext?): Node {
        return super.visitBoolLit(ctx)
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext?): Node {
        return super.visitParamList(ctx)
    }

    override fun visitStrLit(ctx: BasicParser.StrLitContext?): Node {
        return super.visitStrLit(ctx)
    }

    override fun visitWhile(ctx: BasicParser.WhileContext?): Node {
        return super.visitWhile(ctx)
    }

    override fun visitExit(ctx: BasicParser.ExitContext?): Node {
        return super.visitExit(ctx)
    }

    override fun visitParam(ctx: BasicParser.ParamContext?): Node {
        return super.visitParam(ctx)
    }

    override fun visitCharLit(ctx: BasicParser.CharLitContext?): Node {
        return super.visitCharLit(ctx)
    }

    override fun visitStatement(ctx: BasicParser.StatementContext?): Node {
        return super.visitStatement(ctx)
    }

    override fun visitReturn(ctx: BasicParser.ReturnContext?): Node {
        return super.visitReturn(ctx)
    }

    override fun visitRead(ctx: BasicParser.ReadContext?): Node {
        return super.visitRead(ctx)
    }

    override fun visitPairLit(ctx: BasicParser.PairLitContext?): Node {
        return super.visitPairLit(ctx)
    }

    override fun visitSkip(ctx: BasicParser.SkipContext?): Node {
        return super.visitSkip(ctx)
    }

    override fun visitPairType(ctx: BasicParser.PairTypeContext?): Node {
        return super.visitPairType(ctx)
    }

    override fun visitPrintln(ctx: BasicParser.PrintlnContext?): Node {
        return super.visitPrintln(ctx)
    }

    override fun visitFree(ctx: BasicParser.FreeContext?): Node {
        return super.visitFree(ctx)
    }

    override fun visitPairElem(ctx: BasicParser.PairElemContext?): Node {
        return super.visitPairElem(ctx)
    }

    override fun visitBaseType(ctx: BasicParser.BaseTypeContext?): Node {
        return super.visitBaseType(ctx)
    }

    override fun visitPairElemType(ctx: BasicParser.PairElemTypeContext?): Node {
        return super.visitPairElemType(ctx)
    }



}
