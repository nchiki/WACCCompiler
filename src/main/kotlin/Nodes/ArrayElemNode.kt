package src.main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClass

class ArrayElemNode(val type : Node, var exprs : List<ExprNode>) : Node {
    override fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}