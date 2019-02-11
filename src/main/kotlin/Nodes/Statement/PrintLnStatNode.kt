package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PrintLnStatNode(val expr : ExprNode, val ctx: BasicParser.PrintlnContext) : Node {
    override fun getType() : LitTypes {
        return expr.getType()
    }

    override fun syntaxCheck() {

    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() != LitTypes.StringWacc && expr.getType() != LitTypes.CharWacc &&
                expr.getType() != LitTypes.IdentWacc && expr.getType() != LitTypes.IntWacc && expr.getType() != LitTypes.BoolWacc) {
            errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
        }
    }
}