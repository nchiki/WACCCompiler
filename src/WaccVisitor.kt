package wacc_25


import antlr.BasicParser
import antlr.BasicParserVisitor
import org.antlr.v4.runtime.misc.NotNull
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

class WaccVisitor : BasicParserVisitor<Node> {
    override fun visitPairElemType(ctx: BasicParser.PairElemTypeContext?): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitProg(ctx: BasicParser.ProgContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitArgList(ctx: BasicParser.ArgListContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitFunc(ctx: BasicParser.FuncContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitParam(ctx: BasicParser.ParamContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitStat(@NotNull ctx: BasicParser.StatContext) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitAssignLHS(ctx: BasicParser.AssignLHSContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitAssignRHS(ctx: BasicParser.AssignRHSContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitArrayElem(ctx: BasicParser.ArrayElemContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitPairElem(ctx: BasicParser.PairElemContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitType(ctx: BasicParser.TypeContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitBaseType(ctx: BasicParser.BaseTypeContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitPairType(@NotNull ctx: BasicParser.PairTypeContext) : PairNode{
        val fst =  visit(ctx.pairElemType(0))
        val snd = visit(ctx.pairElemType(1))
        return PairNode(fst, snd)
    }

    override fun visitArrayLiter(ctx: BasicParser.ArrayLiterContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(p0: ParseTree?): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitExpr(ctx: BasicParser.ExprContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitBinaryOper(ctx: BasicParser.BinaryOperContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitErrorNode(p0: ErrorNode?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitTerminal(p0: TerminalNode?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitUnaryOper(ctx: BasicParser.UnaryOperContext?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitChildren(p0: RuleNode?) : Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}