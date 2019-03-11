import Nodes.*
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairElemTypeNode
import Nodes.PairType.PairNode
import org.jetbrains.annotations.NotNull
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expression.ParenNode
import main.kotlin.Nodes.Expressions.BinaryOpNode
import main.kotlin.Nodes.Expressions.BoolOpNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Statement.AssignNode
import main.kotlin.Nodes.Statement.*
import main.kotlin.Utils.getType
import src.main.kotlin.IfCondNode
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Statement.StatListNode
import main.kotlin.Utils.LitTypes
import java.lang.Exception
import main.kotlin.Nodes.Statement.DecrementNode
import kotlin.system.exitProcess


class WaccVisitor : BasicParserBaseVisitor<Node>() {

    override fun visitProg(@NotNull ctx: BasicParser.ProgContext): Node {
        val funcCtx = ctx.func()
        val funcList: MutableList<FunctionNode> = mutableListOf()
        for (func in funcCtx) {
            funcList.add(visitFunc(func))
        }
        val stat = visit(ctx.stat())
        return ProgNode(funcList, stat, ctx)
    }

    //IdentNode needs to be constructed with Identifier
    override fun visitId(ctx: BasicParser.IdContext): Node? {
        val id = ctx.ident().text
        return IdentNode(id, ctx)
    }


    override fun visitFunc(@NotNull ctx: BasicParser.FuncContext): FunctionNode {
        val returnType = getType(ctx.type().text)
        val id = ctx.IDENT().text
        val stat = visit(ctx.stat())
        if (ctx.paramList() == null) {
            var params = arrayListOf<ParamNode>()
            val paramList = ParamListNode(params, null)
            return FunctionNode(id, returnType, paramList, stat, ctx)
        } else {
            val paramList = visit(ctx.paramList()) as ParamListNode
            return FunctionNode(id, returnType, paramList, stat, ctx)
        }
    }

    override fun visitIntLit(@NotNull ctx: BasicParser.IntLitContext): Node {
        val int_val : Int
        try {
            int_val = ctx.INT_LIT().text.toInt()
        } catch (e : Exception) {
            if(ctx.getParent() is BasicParser.UnOpContext){
                val parent = ctx.getParent() as BasicParser.UnOpContext
                if(parent.unaryOper().text == "-" && ctx.INT_LIT().text.toLong().equals(2147483648)){
                    return IntLitNode(ctx.INT_LIT().text.toLong(), ctx)
                }
            }
            exitProcess(100)
        }
        return IntLitNode(int_val.toLong(), ctx)
    }

    override fun visitStructElem(@NotNull ctx: BasicParser.StructElemContext): Node {
        val listMembers = arrayListOf<Node>()
        for (member in ctx.type()) {
            listMembers.add(visit(member))
        }
        val id = ctx.IDENT().text
        return StructNode(id, listMembers, ctx)
    }

    override fun visitBoolLit(@NotNull ctx: BasicParser.BoolLitContext): Node {
        val bool_val = ctx.BOOL_LIT().text
        return BoolLitNode(bool_val, ctx)
    }

    override fun visitCharLit(@NotNull ctx: BasicParser.CharLitContext): Node {
        val char_val = ctx.CHAR_LIT().text
        return CharLitNode(char_val, ctx)
    }

    override fun visitStrLit(@NotNull ctx: BasicParser.StrLitContext): Node {
        val str_val = ctx.STR_LIT().text
        return StringLitNode(str_val, ctx)
    }

    override fun visitAssign(@NotNull ctx: BasicParser.AssignContext): Node {
        val id = visit(ctx.assignLHS()) as LHSNode
        val value = visit(ctx.assignRHS()) as RHSNode
        return AssignNode(id, value, ctx)
    }

    override fun visitAssignL_Array(ctx: BasicParser.AssignL_ArrayContext): Node {
        val arrayElem = visit(ctx.arrayElem()) as ArrayElemNode

        return LHSNode(arrayElem, arrayElem.identifier.id, ctx.start!!.line, ctx.start.charPositionInLine, ctx)
    }

    override fun visitAssignL_Iden(ctx: BasicParser.AssignL_IdenContext): Node {
        val id = ctx.IDENT()
        val text : String
        if(id != null) {
            text = id.text
        } else {
            text = ""
        }
        return LHSNode(id, text, ctx.start!!.line, ctx.start.charPositionInLine, ctx)
    }

    override fun visitAssignL_Pairelem(ctx: BasicParser.AssignL_PairelemContext): Node {
        val pairELem = visit(ctx.pairElem()) as PairElemNode
        val exprId = pairELem.expr as IdentNode
        val id = exprId.id
        return LHSNode(pairELem,id, ctx.start!!.line, ctx.start.charPositionInLine, ctx)
    }

    override fun visitAssignR_Exp(ctx: BasicParser.AssignR_ExpContext?): Node {
        return RHSNode(RHS_type.expr, "", null, ctx?.start!!.line, ctx.start!!.charPositionInLine,
                visit(ctx.expr()) as ExprNode?, null, null, null, ctx)
    }

    override fun visitParen(ctx: BasicParser.ParenContext): Node {
        return ParenNode(visit(ctx.expr()) as ExprNode, ctx)
    }
    override fun visitAssignR_Call(ctx: BasicParser.AssignR_CallContext?): Node {
        val funId = ctx?.IDENT()!!.text
        var argList = arrayListOf<ExprNode>()
        if (ctx.argList() != null) {
            val args = ctx.argList().expr()
            for (arg in args) {
                argList.add(visit(arg) as ExprNode)
            }
        }
        return RHSNode(RHS_type.call, funId, ArgListNode(argList, null), ctx.start!!.line,
                ctx.start!!.charPositionInLine,
                null, null, null, null, ctx)
    }

    override fun visitAssignR_Pair_Elem(ctx: BasicParser.AssignR_Pair_ElemContext): Node {
        return RHSNode(RHS_type.pair_elem, "", null, ctx.start.line, ctx.start.charPositionInLine,
                null, null, visit(ctx.pairElem()) as PairElemNode, null, ctx)
    }

    override fun visitAssignR_ArrayL(ctx: BasicParser.AssignR_ArrayLContext): Node {
        return RHSNode(RHS_type.array_lit, "", null, ctx.start.line, ctx.start.charPositionInLine,
                null, null, null, visit(ctx.arrayLiter()) as ArrayLitNode, ctx)
    }

    override fun visitAssignR_Pair(ctx: BasicParser.AssignR_PairContext): Node {
        val expr1 = visit(ctx.expr(0)) as ExprNode
        val expr2 = visit(ctx.expr(1)) as ExprNode
        val newPair = NewPairNode(ctx, expr1, expr2)
        return RHSNode(RHS_type.newpair, "", null, ctx.start!!.line, ctx.start!!.charPositionInLine,
                null, newPair, null, null, ctx)
    }

    override fun visitArrayType(ctx: BasicParser.ArrayTypeContext): Node? {
        val type = visit(ctx.type()) as ExprNode
        return ArrayTypeNode(ctx, type)
    }

    override fun visitPair_type(ctx: BasicParser.Pair_typeContext): Node? {
        val fst = visit(ctx.pairElemType(0)) as PairElemTypeNode
        val snd = visit(ctx.pairElemType(1)) as PairElemTypeNode

        return PairNode(fst, snd, ctx)
    }

    override fun visitPairElemType(ctx: BasicParser.PairElemTypeContext): Node {
        if(ctx.PAIR() != null) {
            return PairElemTypeNode(null, "pair", ctx)
        } else {
            if(ctx.base_type() != null) {
                return PairElemTypeNode(visit(ctx.getChild(0)) as ExprNode, "", ctx)
            } else {
                return PairElemTypeNode(visit(ctx.getChild(0)) as ExprNode, "", ctx)
            }
        }
    }

    override fun visitArrayElem(@NotNull ctx: BasicParser.ArrayElemContext): Node? {
        val id = ctx.IDENT().text
        var exprs = arrayListOf<ExprNode>()
        var i = 0
        while(ctx.expr(i) != null) {
            exprs.add(visit(ctx.expr(i)) as ExprNode)
            i++
        }
        return ArrayElemNode(IdentNode(id, ctx), exprs, ctx)
    }

    override fun visitWhile(@NotNull ctx: BasicParser.WhileContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        val stat = visit(ctx.stat())
        return WhileNode(expr, stat, ctx)
    }

    override fun visitArrayLiter(ctx: BasicParser.ArrayLiterContext?): Node {
        val exprs = ctx!!.expr()
        var exprList = ArrayList<ExprNode>()
        for (expr in exprs) {
            exprList.add(visit(expr) as ExprNode)
        }
        return ArrayLitNode(exprList, ctx)
    }


    override fun visitAddSubOp(@NotNull ctx: BasicParser.AddSubOpContext): Node {
        val left = visit(ctx.expr(0)) as ExprNode
        val right = visit(ctx.expr(1)) as ExprNode

        val operator = ctx.addSub()

        if(left.getBaseType().equals(LitTypes.StringWacc) || right.getBaseType().equals(LitTypes.StringWacc)){
            println("Invalid Syntax using ${operator} on line ${ctx.start.line} at " +
                    "position ${ctx.start.charPositionInLine}")
            exitProcess(100)
        }

        return BinaryOpNode(left, right, operator, null, null, ctx)
    }

    override fun visitMultDivOp(@NotNull ctx: BasicParser.MultDivOpContext): Node {
        val left = visit(ctx.expr(0)) as ExprNode
        val right = visit(ctx.expr(1)) as ExprNode

        val operator = ctx.multDiv()

        if(left.getBaseType().equals(LitTypes.StringWacc) || right.getBaseType().equals(LitTypes.StringWacc)){
            println("Invalid Syntax using ${operator} on line ${ctx.start.line} at " +
                    "position ${ctx.start.charPositionInLine}")
            exitProcess(100)
        }

        return BinaryOpNode(left, right, null, operator, null, ctx)
    }

    override fun visitEqOp(@NotNull ctx: BasicParser.EqOpContext): Node {
        val left = visit(ctx.expr(0)) as ExprNode
        val right = visit(ctx.expr(1)) as ExprNode

        val operator = ctx.eq_Op()

        if(left.getBaseType().equals(LitTypes.StringWacc) || right.getBaseType().equals(LitTypes.StringWacc)){
            println("Invalid Syntax using ${operator} on line ${ctx.start.line} at " +
                    "position ${ctx.start.charPositionInLine}")
            exitProcess(100)
        }

        return BinaryOpNode(left, right, null, null, operator, ctx)
    }

    override fun visitBoolOper(ctx: BasicParser.BoolOperContext?): Node {
        val left = visit(ctx?.expr(0)) as ExprNode
        val right = visit(ctx?.expr(1)) as ExprNode
        val operator = ctx?.boolOp()
        return BoolOpNode(left, right, operator!!, ctx)
    }

    override fun visitUnOp(ctx: BasicParser.UnOpContext): Node {
        val ex = ctx.expr()
        if (ex is BasicParser.IntLitContext) {
            if (ctx.text[0] == '-') {
                return IntLitNode(-ex.INT_LIT().text.toLong(), ex)
            }
        }
        val operand = visit(ctx.expr()) as ExprNode
        val operator = ctx.unaryOper()
        return UnaryOpNode(operand, operator!!, operand.getBaseType(), ctx)
    }

    override fun visitStatList(ctx: BasicParser.StatListContext): Node {
        val stats = ctx.stat()

        // declaration empty list of parameters
        val listStatNodes = mutableListOf<Node>()

        // iterates through the parameters adding them to the list
        if(stats != null) {
            for (stat in stats) {
                listStatNodes.add(visit(stat))
            }
        }
        return StatListNode(listStatNodes, ctx)
    }

    override fun visitStatement(@NotNull ctx: BasicParser.StatementContext): Node {
        val stat = visit(ctx.stat())
        return StatementNode(stat, ctx)
    }

    override fun visitArgList(ctx: BasicParser.ArgListContext) : Node {
        val exprs = ctx.expr()
        val exprList = ArrayList<ExprNode>()
        for (expr in exprs) {
            exprList.add(visit(expr) as ExprNode)
        }
        return ArgListNode(exprList, ctx)

    }

    override fun visitDecl(ctx: BasicParser.DeclContext): Node {

        // type node that will worry about checking if its valid type
        val type = visit(ctx.type()) as ExprNode

        // idNode that will check if there is a variable already declared with the same id
        val id = ctx.IDENT()?.text

        // assignRHS node that will worry about semantic check of the RHS
        val RHS = visit(ctx.assignRHS()) as RHSNode

        return DeclNode(id!!, type, RHS, ctx)
    }

    override fun visitIfCond(ctx: BasicParser.IfCondContext): Node {
        // the expr will be evaluted depending on its type
        val expr = visit(ctx.expr()) as ExprNode

        // if expr evalutes to true
        val IfTrueStat = visit(ctx.stat(0))
        // if expr evaluates to false
        val ElseStat = visit(ctx.stat(1))

        return IfCondNode(expr, IfTrueStat, ElseStat, ctx)
    }

    override fun visitPrint(ctx: BasicParser.PrintContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return PrintStatNode(expr, ctx)
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext): ParamListNode {
        // gets parameters in context
        val params = ctx.param()

        // declaration empty list of parameters
        val listParamNodes = mutableListOf<ParamNode>()

        // iterates through the parameters adding them to the list
        if(params != null) {
            for (param in params) {
                listParamNodes.add(visit(param) as ParamNode)
            }
        }

        return ParamListNode(listParamNodes, ctx) // new Node
    }

    override fun visitExit(@NotNull ctx: BasicParser.ExitContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return ExitStatNode(expr, ctx)
    }

    override fun visitParam(ctx: BasicParser.ParamContext?): Node {

        // typeNode of the parameter
        val type = visit(ctx?.type()) as ExprNode

        // name of param var
        val id = ctx?.IDENT()?.text
        return ParamNode(id!!, type, ctx)
    }

    override fun visitReturn(@NotNull ctx: BasicParser.ReturnContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return ReturnStatNode(expr, ctx)
    }

    override fun visitRead(ctx: BasicParser.ReadContext): Node {
        val lhs = visit(ctx.assignLHS()) as LHSNode
        return ReadStatNode(lhs, ctx)
    }

    override fun visitPairLit(ctx: BasicParser.PairLitContext?): Node {
        return PairLitNode(ctx!!)
    }

    override fun visitPairElem(ctx: BasicParser.PairElemContext?): Node {
        val pos :Int
        if(ctx?.SND() != null) {
            pos = 1
        } else {
            pos = 0
        }
        return PairElemNode(visit(ctx?.expr()) as ExprNode, ctx!!, pos)

    }

    override fun visitSkip(ctx: BasicParser.SkipContext): SkipNode {
        return SkipNode(ctx)
    }

    override fun visitPrintln(@NotNull ctx: BasicParser.PrintlnContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return PrintLnStatNode(expr, ctx)
    }

    override fun visitFree(@NotNull ctx: BasicParser.FreeContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return FreeStatNode(expr, ctx)
    }

    override fun visitBaseType(@NotNull ctx: BasicParser.BaseTypeContext): Node {
        return BaseNode(ctx.text, ctx)
    }

    override fun visitBase_type(ctx: BasicParser.Base_typeContext?): Node {
        return BaseNode(ctx!!.text, ctx)
    }

    // FOR LOOP
    override fun visitForLoop(ctx: BasicParser.ForLoopContext): Node {
        val first = visit(ctx.forCond()) as ForCondNode
        val stat = visit(ctx.stat())
        return ForLoopNode(first, stat, ctx)
    }

    override fun visitForCond(ctx: BasicParser.ForCondContext): Node {
        val first = visit(ctx.stat(0))
        val second = visit(ctx.expr()) as ExprNode
        val third = visit(ctx.stat(1))
        return ForCondNode(first, second, third, ctx)
    }

    // DO WHILE
    override fun visitDoWhile(ctx: BasicParser.DoWhileContext): Node {
        val stat = visit(ctx.stat())
        val expr = visit(ctx.expr()) as ExprNode

        return DoWhileNode(stat, expr, ctx)
    }

    // BREAK
    override fun visitBreak(ctx: BasicParser.BreakContext): Node {
        return BreakNode(ctx)
    }

    // CONTINUE
    override fun visitContinue(ctx: BasicParser.ContinueContext): Node {
        return ContinueNode(ctx)
    }

    override fun visitIncrement(ctx: BasicParser.IncrementContext): Node {
        val id = ctx.IDENT().text
        return IncrementNode(id, ctx)
    }

    override fun visitDecrement(ctx: BasicParser.DecrementContext): Node {
        val id = ctx.IDENT().text
        return DecrementNode(id, ctx)
    }

}
