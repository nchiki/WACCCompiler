package main.kotlin.Nodes.Statement

import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{
    override fun getType() : LitTypes {
        return expr.getType()
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() != LitTypes.StringWacc
                && expr.getType() != LitTypes.CharWacc
                && expr.getType() != LitTypes.IdentWacc
                && expr.getType() != LitTypes.IntWacc
                && expr.getType() != LitTypes.BoolWacc
                && expr.getType() != LitTypes.PairWacc) {
            errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine, "{STRING, INT, CHAR}", expr, table))
        }
        expr.semanticCheck(errors, table)
    }
}