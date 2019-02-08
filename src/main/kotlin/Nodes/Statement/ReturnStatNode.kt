package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClass

class ReturnStatNode (val expr : ExprNode) : Node {
    override fun getType() : KClass<ReturnStatNode> {
        return ReturnStatNode::class
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //need to save return type of function in return node and then check
        // type of expr with return type of function
    }
}