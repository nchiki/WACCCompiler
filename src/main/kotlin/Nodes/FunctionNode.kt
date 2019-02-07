package Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable

class FunctionNode (val id : String, val type : String, val params : ParamListNode, val table : SymbolTable?) : Node {

    fun getID() : String {
        return id
    }

    fun getParent() : SymbolTable? {
        return table?.parent
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}