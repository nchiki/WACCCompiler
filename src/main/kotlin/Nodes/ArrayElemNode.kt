package src.main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class ArrayElemNode(val baseType : Node, var exprs : List<ExprNode>, val ctx: BasicParser.ArrayElemContext) : ExprNode {

    override fun getType() : LitTypes {
        return exprs[0].getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        for (expr in exprs) {
            if (expr.getType() != baseType) {
                errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine, baseType.toString(), expr, table))
            }
            expr.semanticCheck(errors, table)
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}