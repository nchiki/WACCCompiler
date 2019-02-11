package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PairElemNode(val expr : ExprNode, val ctx: BasicParser.PairElemContext) : Node{

    override fun getType() : LitTypes {
        return expr.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
