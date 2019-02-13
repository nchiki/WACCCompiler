package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ArrayLitNode(val exprList : MutableList<ExprNode>, override val ctx : BasicParser.ArrayLiterContext) : Node {

    override fun getType() : LitTypes {
        return LitTypes.ArrayLit
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(exprList.size == 0){
            return
        }
        val type = exprList[0].getType()
        for (expr in exprList) {
            if(type != expr.getType()) {
                errors.addError(IncompatibleTypes(ctx, type.toString(), expr, table))
            }
            expr.semanticCheck(errors, table)
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
