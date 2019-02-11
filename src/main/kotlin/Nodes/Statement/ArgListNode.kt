package main.kotlin.Nodes.Statement

import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ArgListNode(val exprs : List<ExprNode>) : StatementNode {

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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