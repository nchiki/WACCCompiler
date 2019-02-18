package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ArgListNode(val exprs : List<ExprNode>, override val ctx: BasicParser.ArgListContext?) : Node {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        for (expr in exprs) {
            expr.semanticCheck(errors, table)
        }
    }

    override fun getType(): LitTypes {
        //not needed for this type
        TODO()
    }
}