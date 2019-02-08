package Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.ExprNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class ArrayElemNode(val type : Node, var exprs : List<ExprNode>) : Node {
    override fun getType() : KClass<ArrayElemNode>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}