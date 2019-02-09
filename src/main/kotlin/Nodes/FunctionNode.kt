package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class FunctionNode (val id : String, val type : String, val params : ParamListNode, val stat : Node) : Node {
    override fun getType() : KClass<FunctionNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}