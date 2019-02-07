import Nodes.*
import org.jetbrains.annotations.NotNull
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable

class WaccVisitor : BasicParserBaseVisitor<Node>() {

    val globalTable = SymbolTable(null)

    override fun visitProg(@NotNull ctx: BasicParser.ProgContext): Node {
        val funcCtx = ctx.func()
        val funcList: MutableList<FunctionNode> = mutableListOf()
        for (func in funcCtx) {
            funcList.add(visitFunc(func))
        }
        val stat = visit(ctx.stat()) as StatementNode
        return ProgNode(funcList, stat)
    }

    //IdentNode needs to be constructed with Identifier (constructor of IdentNode not done yet)
    override fun visitId(ctx: BasicParser.IdContext): Node? {
        val id = ctx.IDENT().text
        /* uncomment when Symbol Table is done */
        return globalTable.lookupSymbol(id)

    }

    //add Statements as well as parameter of FuncNode
    override fun visitFunc(@NotNull ctx: BasicParser.FuncContext): FunctionNode {
        val paramList = visitParamList(ctx.paramList())
        val returnType = ctx.type().text
        val id = ctx.IDENT().text
        var newTable : SymbolTable? = null
        if (globalTable.lookupSymbol(id) == null) {
            newTable = SymbolTable(globalTable)
            //globalTable = newTable
        }
        val stat = visit(ctx.stat()) as StatementNode
        //globalTable = newTable!!.parent!!
        return FunctionNode(/*id, returnType, paramList, newTable*/)
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

    override fun visitArrayElem(@NotNull ctx: BasicParser.ArrayElemContext): Node? {
        val idType = visit(ctx.IDENT())
        var exprs = arrayListOf<ExprNode>()
        for (expr in ctx.expr()) {
            exprs.add(expr as ExprNode)
        }
        return ArrayElemNode(idType, exprs)
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

        // type node that will worry about checking if its valid type
        val type = visit(ctx?.type())

        // idNode that will check if there is a variable already declared with the same id
        val id = ctx?.IDENT()?.text

        // assignRHS node that will worry about semantic check of the RHS
        val RHS = visit(ctx?.assignRHS())

        return DeclNode(id!!, type, RHS)
    }

    override fun visitIfCond(ctx: BasicParser.IfCondContext?): Node {
        // the expr will be evaluted depending on its type
        val expr = visit(ctx?.expr())
        // if expr evalutes to true
        val IfTrueStat = visit(ctx?.stat(0))
        // if expr evaluates to false
        val ElseStat = visit(ctx?.stat(1))

        return IfCondNode(expr, IfTrueStat, ElseStat)

    }


    override fun visitPrint(ctx: BasicParser.PrintContext?): Node {
        return super.visitPrint(ctx)
    }

    override fun visitBoolLit(ctx: BasicParser.BoolLitContext?): Node {
        return super.visitBoolLit(ctx)
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext?): Node {
        // gets parameters in context
        val params =ctx?.param()

        // declaration empty list of parameters
        val listParamNodes = mutableListOf<Node>()

        // iterates through the parameters adding them to the list
        for (param in params!!) {
            listParamNodes.add(visit(param))
        }

        return ParamListNode(listParamNodes) // new Node
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

        // typeNode of the parameter
        val type = visit(ctx?.type())

        // name of param var

        val id = ctx?.IDENT()?.text
        return ParamNode(id!!, type)
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
