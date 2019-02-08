package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.ExprNode
import main.kotlin.Nodes.Literals.StrLitNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class PrintStatNode(val expr : ExprNode) : Node {
    override fun getType() : KClass<PrintStatNode>{
        return PrintStatNode::class
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() != StrLitNode::class) {
            errors.addError(IncompatibleTypes())
        }
        expr.semanticCheck(errors, table)
    }
}