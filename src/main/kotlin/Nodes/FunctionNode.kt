package Nodes

import main.kotlin.ErrorLogger
import SymbolTable
import main.kotlin.Nodes.Node

class FunctionNode (val id : String, val type : String, val params : ParamListNode, val table : SymbolTable) : Node {

    fun getID() : String {
        return id
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}