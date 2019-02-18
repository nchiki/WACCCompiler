package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PairElemNode(val expr : ExprNode, override val ctx: BasicParser.PairElemContext, val elem : Int) : ExprNode {

    override fun getBaseType() : LitTypes {
        return expr.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}
