package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode


class ReturnStatNode (val expr : ExprNode, val ctx: BasicParser.ReturnContext) : Node {
    fun getType() : BaseNode {
    TODO()
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //need to save return type of function in return node and then check
        // type of expr with return type of function
        //compare with type
    }
}