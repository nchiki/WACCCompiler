package main.kotlin.Nodes

import Nodes.ParamListNode
import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

class FunctionNode (val id : String, val type : String, val params : ParamListNode, val stat : StatementNode) : Node {
    override fun getType() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}