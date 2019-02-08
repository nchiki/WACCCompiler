package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.ExprNode
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class IntLitNode(val int_val : Int) : ExprNode {
    override fun getType(): KClass<IntLitNode> {
        return IntLitNode::class
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}