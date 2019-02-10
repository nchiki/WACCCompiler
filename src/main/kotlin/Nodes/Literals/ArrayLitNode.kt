package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ArrayLitNode(val exprList : MutableList<ExprNode>, val ctx : BasicParser.ArrayLiterContext) : Node {

    fun getType() : LitTypes {
        return exprList[0].type as LitTypes
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val type = exprList[0].type
        for (expr in exprList) {
            if(type != expr.type) {
                errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
            }
            expr.semanticCheck(errors, table)
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
