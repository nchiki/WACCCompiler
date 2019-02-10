package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class ReturnStatNode (val expr : ExprNode, val ctx: BasicParser.ReturnContext, var type: LitTypes?) : Node {

    fun setFunctionReturn(type : LitTypes) {
        this.type = type
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //need to add actual lines and positions
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (!(type!!.equals(expr.getType()))) {
            errors.addError(IncompatibleTypes(0, 0))
        }
    }
}