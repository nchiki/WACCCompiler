package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.StringLitNode
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClass

class PrintStatNode(val expr : ExprNode) : Node {
    override fun getType() : BaseNode {
        return BaseNode("string")
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (!expr.getType().equals("string")) {
            errors.addError(IncompatibleTypes())
        }
        expr.semanticCheck(errors, table)
    }
}