package wacc_25

import antlr.BasicParser
import antlr.BasicParserVisitor
import org.antlr.v4.runtime.misc.NotNull
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

class WaccVisitor : BasicParserVisitor<Void> {
    override fun visitProg(ctx: BasicParser.ProgContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitArgList(ctx: BasicParser.ArgListContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitFunc(ctx: BasicParser.FuncContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitParamList(ctx: BasicParser.ParamListContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitParam(ctx: BasicParser.ParamContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitStat(@NotNull ctx: BasicParser.StatContext): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitAssignLHS(ctx: BasicParser.AssignLHSContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitAssignRHS(ctx: BasicParser.AssignRHSContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitArrayElem(ctx: BasicParser.ArrayElemContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitPairElem(ctx: BasicParser.PairElemContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitType(ctx: BasicParser.TypeContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitBaseType(ctx: BasicParser.BaseTypeContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitPairType(ctx: BasicParser.PairTypeContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitPairELemType(ctx: BasicParser.PairELemTypeContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitArrayLiter(ctx: BasicParser.ArrayLiterContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visit(p0: ParseTree?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitExpr(ctx: BasicParser.ExprContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitBinaryOper(ctx: BasicParser.BinaryOperContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitErrorNode(p0: ErrorNode?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitTerminal(p0: TerminalNode?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitUnaryOper(ctx: BasicParser.UnaryOperContext?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitChildren(p0: RuleNode?): Void {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}