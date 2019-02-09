import Nodes.*
import org.jetbrains.annotations.NotNull
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.PairType.Pair_Fst
import main.kotlin.Nodes.PairType.Pair_Snd
import main.kotlin.Nodes.Statement.*
import src.main.kotlin.IfCondNode
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class WaccVisitor : BasicParserBaseVisitor<Node>() {

    override fun visitProg(@NotNull ctx: BasicParser.ProgContext): Node {
        val funcCtx = ctx.func()
        val funcList: MutableList<FunctionNode> = mutableListOf()
        for (func in funcCtx) {
            funcList.add(visitFunc(func))
        }
        val stat = visit(ctx.stat()) //as StatementNode
        return ProgNode(funcList, stat)
    }

    //IdentNode needs to be constructed with Identifier (constructor of IdentNode not done yet)
    override fun visitId(ctx: BasicParser.IdContext): Node? {
        val id = ctx.IDENT().text
        return IdentNode(id)
    }

    //add Statements as well as parameter of FuncNode
    override fun visitFunc(@NotNull ctx: BasicParser.FuncContext): FunctionNode {
        val paramList = visitParamList(ctx.paramList())
        val returnType = ctx.type().text
        val id = ctx.IDENT().text
        val stat = visit(ctx.stat()) as StatementNode
        return FunctionNode(id, returnType, paramList, stat)
    }

    //IntNode needs to be constructed with val of int
    override fun visitIntLit(@NotNull ctx: BasicParser.IntLitContext): Node {
        val int_val = ctx.INT_LIT().text.toInt()
        return IntLitNode(int_val)
    }

    override fun visitBoolLit(@NotNull ctx: BasicParser.BoolLitContext): Node {
//        val bool_val = ctx.BOOL_LIT().symbol.
//        return BoolLitNode(bool_val)
        return BoolLitNode(true)
    }

    override fun visitCharLit(@NotNull ctx: BasicParser.CharLitContext): Node {
        val char_val = ctx.CHAR_LIT().text
        return CharLitNode(char_val)
    }

    override fun visitStrLit(ctx: BasicParser.StrLitContext?): Node {
        val str_val = ctx?.STR_LIT()?.text
        return StringLitNode(str_val!!)
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
        val fst = visit(ctx.pairElemType(1)) as Pair_Fst
        val snd = visit(ctx.pairElemType(1)) as Pair_Snd
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

    override fun visitBinOper(ctx: BasicParser.BinOperContext?): Node {
        val left = visit(ctx?.expr(0))
        val right = visit(ctx?.expr(1))
        val operator = ctx?.binaryOper()
        return BinaryOpNode(left, right, operator!!)
    }

    override fun visitUnOp(ctx: BasicParser.UnOpContext?): Node {
        val operand = visit(ctx?.expr())
        val operator = ctx?.unaryOper()
        return UnaryOpNode(operand, operator!!)
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


    override fun visitPrint(@NotNull ctx: BasicParser.PrintContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return PrintStatNode(expr)
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext?): ParamListNode {
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

    override fun visitWhile(ctx: BasicParser.WhileContext?): Node {
        return super.visitWhile(ctx)
    }

    override fun visitExit(@NotNull ctx: BasicParser.ExitContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return ExitStatNode(expr)
    }

    override fun visitParam(ctx: BasicParser.ParamContext?): Node {

        // typeNode of the parameter
        val type = visit(ctx?.type())

        // name of param var

        val id = ctx?.IDENT()?.text
        return ParamNode(id!!, type)
    }

    override fun visitStatement(ctx: BasicParser.StatementContext?): Node {
        return super.visitStatement(ctx)
    }

    override fun visitReturn(@NotNull ctx: BasicParser.ReturnContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return ReturnStatNode(expr)
    }

    override fun visitRead(ctx: BasicParser.ReadContext?): Node {
        //have to add how to do read since we need to represent AssignLhs
        TODO()
    }

    override fun visitPairLit(ctx: BasicParser.PairLitContext?): Node {
        return super.visitPairLit(ctx)
    }

    override fun visitSkip(ctx: BasicParser.SkipContext?): SkipNode {
        return SkipNode()
    }

    override fun visitPrintln(@NotNull ctx: BasicParser.PrintlnContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return PrintLnStatNode(expr)
    }

    override fun visitFree(@NotNull ctx: BasicParser.FreeContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return FreeStatNode(expr)
    }

    override fun visitBaseType(@NotNull ctx: BasicParser.BaseTypeContext): Node {
        return BaseNode(ctx.text)
    }
}
