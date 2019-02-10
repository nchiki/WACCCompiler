import Nodes.*
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairElemTypeNode
import Nodes.PairType.PairNode
import org.jetbrains.annotations.NotNull
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Statement.*
import main.kotlin.Nodes.TypeNodes.TypeNode
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
        return ProgNode(funcList, stat, ctx)
    }

    //IdentNode needs to be constructed with Identifier (constructor of IdentNode not done yet)
    override fun visitId(ctx: BasicParser.IdContext): Node? {
        val id = ctx.IDENT().text
        return IdentNode(id, ctx)
    }

    //add Statements as well as parameter of FuncNode
    override fun visitFunc(@NotNull ctx: BasicParser.FuncContext): FunctionNode {
        val paramList = visitParamList(ctx.paramList())
        val returnType = ctx.type().text
        val id = ctx.IDENT().text
        val stat = visit(ctx.stat()) as ReturnStatNode
        return FunctionNode(id, returnType, paramList, stat, ctx)
    }

    //IntNode needs to be constructed with val of int
    override fun visitIntLit(@NotNull ctx: BasicParser.IntLitContext): Node {
        val int_val = ctx.INT_LIT().text.toInt()
        return IntLitNode(int_val, ctx)
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

    override fun visitAssign(@NotNull ctx: BasicParser.AssignContext): Node? {
        val id = visit(ctx.assignLHS()) as LHS_Node
        val value = visit(ctx.assignRHS()) as RHS_Node
        /* uncomment when symbol table is implemented */
        //memory.put(id, value);
        return AssignNode(id, value, ctx)
    }

    override fun visitAssignL_Array(ctx: BasicParser.AssignL_ArrayContext?): Node {
        return LHS_Node(visit(ctx?.arrayElem()), "", ctx?.start!!.line, ctx?.start.charPositionInLine)
    }

    override fun visitAssignL_Iden(ctx: BasicParser.AssignL_IdenContext?): Node {
        return LHS_Node(visit(ctx?.IDENT()),  ctx?.IDENT()!!.text, ctx?.start!!.line, ctx?.start.charPositionInLine)
    }

    override fun visitAssignL_Pairelem(ctx: BasicParser.AssignL_PairelemContext?): Node {
        return LHS_Node(visit(ctx?.pairElem()), "", ctx?.start!!.line, ctx?.start.charPositionInLine)
    }

    override fun visitAssignR_Exp(ctx: BasicParser.AssignR_ExpContext?): Node {
        return RHS_Node(RHS_type.expr, "", null, ctx?.start!!.line, ctx.start!!.charPositionInLine,
                visit(ctx.expr()) as ExprNode, null, null, null)
    }

    override fun visitAssignR_Call(ctx: BasicParser.AssignR_CallContext?): Node {
        val funId = ctx?.IDENT()!!.text
        val params = visit(ctx.argList()) as ArgListNode
        return RHS_Node(RHS_type.call, ctx.IDENT().text, params, ctx.start!!.line, ctx.start!!.charPositionInLine,
                null, null, null, null)
    }

    override fun visitAssignR_Pair_Elem(ctx: BasicParser.AssignR_Pair_ElemContext?): Node {
        return RHS_Node(RHS_type.pair_elem, "", null, ctx?.start!!.line, ctx.start!!.charPositionInLine,
                null, null, visit(ctx.pairElem()) as PairElemNode, null)
    }

    override fun visitAssignR_ArrayL(ctx: BasicParser.AssignR_ArrayLContext?): Node {
        return RHS_Node(RHS_type.array_lit, "", null, ctx?.start!!.line, ctx.start!!.charPositionInLine,
                null, null, null, visit(ctx.arrayLiter()) as ArrayLitNode)
    }


    override fun visitAssignR_Pair(ctx: BasicParser.AssignR_PairContext?): Node {
        return RHS_Node(RHS_type.newpair, "", null, ctx?.start!!.line, ctx.start!!.charPositionInLine,
                visit(ctx.expr(0)) as ExprNode, visit(ctx.expr(1)) as ExprNode, null, null)
    }

    override fun visitArrayType(@NotNull ctx: BasicParser.ArrayTypeContext): Node? {
        val type = visit(ctx.type())
        return ArrayTypeNode(ctx, type as TypeNode)
    }

    override fun visitPair_type(@NotNull ctx: BasicParser.Pair_typeContext): Node? {
        val fst = visit(ctx.pairElemType(0)) as PairElemTypeNode
        val snd = visit(ctx.pairElemType(1)) as PairElemTypeNode

        return PairNode(fst, snd)
    }

    override fun visitPairElemType(ctx: BasicParser.PairElemTypeContext?): Node {
        if(ctx?.PAIR() != null) {
            return PairElemTypeNode(null, "pair")
        } else {
            return PairElemTypeNode(visit(ctx?.type()) as TypeNode, "")
        }
    }



    override fun visitArrayElem(@NotNull ctx: BasicParser.ArrayElemContext): Node? {
        val idType = visit(ctx.IDENT())
        var exprs = arrayListOf<ExprNode>()
        for (expr in ctx.expr()) {
            exprs.add(expr as ExprNode)
        }
        return ArrayElemNode(idType, exprs, ctx)
    }

    override fun visitArrayLiter(ctx: BasicParser.ArrayLiterContext?): Node {
        val exprs = ctx!!.expr()
        var exprList = ArrayList<ExprNode>()
        for (expr in exprs) {
            exprList.add(visit(expr) as ExprNode)
        }
        return ArrayLitNode(exprList, ctx)
    }

    override fun visitBinOper(ctx: BasicParser.BinOperContext?): Node {
        val left = visit(ctx?.expr(0)) as ExprNode
        val right = visit(ctx?.expr(1)) as ExprNode
        val operator = ctx?.binaryOper()
        return BinaryOpNode(left, right, operator!!, ctx)
    }

    override fun visitUnOp(ctx: BasicParser.UnOpContext?): Node {
        val operand = visit(ctx?.expr()) as ExprNode
        val operator = ctx?.unaryOper()
        return UnaryOpNode(operand, operator!!, operand.getType())
    }

    override fun visitStatList(ctx: BasicParser.StatListContext?): Node {
        return super.visitStatList(ctx)

    }

    override fun visitArgList(@NotNull ctx: BasicParser.ArgListContext) : Node {
        val exprs = ctx.expr()
        var exprList = ArrayList<ExprNode>()
        for (expr in exprs) {
            exprList.add(visit(expr) as ExprNode)
        }
        return ArgListNode(exprList)

    }

    override fun visitDecl(ctx: BasicParser.DeclContext?): Node {

        // type node that will worry about checking if its valid type
        val type = visit(ctx?.type())

        // idNode that will check if there is a variable already declared with the same id
        val id = ctx?.IDENT()?.text

        // assignRHS node that will worry about semantic check of the RHS
        val RHS = visit(ctx?.assignRHS()) as RHS_Node

        return DeclNode(id!!, type as TypeNode, RHS, ctx)
    }

    override fun visitIfCond(ctx: BasicParser.IfCondContext?): Node {
        // the expr will be evaluted depending on its type
        val expr = visit(ctx?.expr())
        // if expr evalutes to true
        val IfTrueStat = visit(ctx?.stat(0))
        // if expr evaluates to false
        val ElseStat = visit(ctx?.stat(1))

        return IfCondNode(expr, IfTrueStat, ElseStat, ctx?.expr()!!)

    }


    override fun visitPrint(@NotNull ctx: BasicParser.PrintContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return PrintStatNode(expr, ctx)
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext?): ParamListNode {
        // gets parameters in context
        val params =ctx?.param()

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

    override fun visitWhile(ctx: BasicParser.WhileContext?): Node {
        return super.visitWhile(ctx)
    }

    override fun visitExit(@NotNull ctx: BasicParser.ExitContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return ExitStatNode(expr, ctx)
    }

    override fun visitParam(ctx: BasicParser.ParamContext?): Node {

        // typeNode of the parameter
        val type = visit(ctx?.type())

        // name of param var

        val id = ctx?.IDENT()?.text
        return ParamNode(id!!, type, ctx)
    }

    override fun visitStatement(ctx: BasicParser.StatementContext?): Node {
        return super.visitStatement(ctx)
    }

    override fun visitReturn(@NotNull ctx: BasicParser.ReturnContext): Node {
        val expr = visit(ctx.expr()) as ExprNode
        return ReturnStatNode(expr, ctx)
    }

    override fun visitRead(ctx: BasicParser.ReadContext?): Node {
        //have to add how to do read since we need to represent AssignLhs
        TODO()
    }

    override fun visitPairLit(ctx: BasicParser.PairLitContext?): Node {
        return PairLitNode(ctx!!)
    }

    override fun visitSkip(ctx: BasicParser.SkipContext?): SkipNode {
        return SkipNode()
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
        return BaseNode(ctx.text)
    }



}
