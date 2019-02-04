package wacc_25


import antlr.BasicParser
import antlr.BasicParserBaseVisitor
import antlr.BasicParserVisitor
import org.antlr.v4.runtime.misc.NotNull
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import wacc_25.Nodes.BaseNode

class WaccVisitor : BasicParserBaseVisitor<Node>() {

    override fun visitPairType(@NotNull ctx: BasicParser.PairTypeContext) : PairNode{
        val fst = visit(ctx.pairELemType(0))
        val snd = visit(ctx.pairELemType(1))
        return PairNode(fst, snd)
    }

    override fun visitProg(ctx: BasicParser.ProgContext): Node{
        return visitChildren(ctx)
    }

    override fun visitArgList(ctx: BasicParser.ArgListContext): Node{
        return visitChildren(ctx)
    }

    override fun visitFunc(ctx: BasicParser.FuncContext): Node{
        return visitChildren(ctx)
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext): Node{
        return visitChildren(ctx)
    }

    override fun visitParam(ctx: BasicParser.ParamContext): Node{
        return visitChildren(ctx)
    }

    override fun visitStat(ctx: BasicParser.StatContext): Node{
        return visitChildren(ctx)
    }

    override fun visitAssignLHS(ctx: BasicParser.AssignLHSContext): Node{
        return visitChildren(ctx)
    }

    override fun visitAssignRHS(ctx: BasicParser.AssignRHSContext): Node{
        return visitChildren(ctx)
    }

    override fun visitArrayElem(ctx: BasicParser.ArrayElemContext): Node{
        return visitChildren(ctx)
    }

    override fun visitPairElem(ctx: BasicParser.PairElemContext): Node{
        return visitChildren(ctx)
    }

    override fun visitType(ctx: BasicParser.TypeContext): Node{
        return visitChildren(ctx)
    }

    override fun visitBaseType(ctx: BasicParser.BaseTypeContext): Node{
        return BaseNode()
    }

    override fun visitPairELemType(ctx: BasicParser.PairELemTypeContext): Node{
        return visitChildren(ctx)
    }

    override fun visitExpr(ctx: BasicParser.ExprContext): Node{
        return visitChildren(ctx)
    }

    override fun visitBinaryOper(ctx: BasicParser.BinaryOperContext): Node{
        return visitChildren(ctx)
    }

    override fun visitUnaryOper(ctx: BasicParser.UnaryOperContext): Node{
        return visitChildren(ctx)
    }

    override fun visitArrayLiter(ctx: BasicParser.ArrayLiterContext): Node{
        return visitChildren(ctx)
    }
}