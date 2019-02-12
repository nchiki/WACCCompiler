package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ArrayLitNode(val exprList : MutableList<ExprNode>, override val ctx : BasicParser.ArrayLiterContext) : Node {

    override fun getType() : LitTypes {
        return exprList[0].getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val type = exprList[0].getType()
        for (expr in exprList) {
            if(type != expr.getType()) {
                errors.addError(IncompatibleTypes(ctx, type.toString(), expr, table))
            }
            expr.semanticCheck(errors, table)
        }
    }

    fun getPrintableType() : String {
        return getType().toString().toUpperCase().replace("WACC", "") + "[]"
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
