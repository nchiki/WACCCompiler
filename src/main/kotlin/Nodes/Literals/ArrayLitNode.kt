package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ArrayLitNode(val exprList : MutableList<ExprNode>, override val ctx : BasicParser.ArrayLiterContext) : ExprNode {

    override fun getBaseType() : LitTypes {
        if (exprList.size > 0) {
            return exprList[0].getBaseType()
        }
        return LitTypes.ArrayLit
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(exprList.size == 0){
            return
        }
        val type = exprList[0].getBaseType()
        for (expr in exprList) {
            if(type != expr.getBaseType()) {
                errors.addError(IncompatibleTypes(ctx, type.toString(), expr, table))
            }
            expr.semanticCheck(errors, table)
        }
    }
}
