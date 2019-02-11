package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode

class ArgListNode(val exprs : List<ExprNode>, override val ctx: BasicParser.ArgListContext) : Node {

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        for (expr in exprs) {
            expr.semanticCheck(errors, table)
        }
    }

    fun getType(): BaseNode {
        //not needed for this type
        TODO()
    }
}