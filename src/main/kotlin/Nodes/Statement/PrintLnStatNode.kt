package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.CharLitNode
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.StringLitNode
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClass
import kotlin.system.exitProcess

class PrintLnStatNode(val expr : ExprNode) : Node {
    override fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {

    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr !is StringLitNode && expr !is CharLitNode) {
            errors.addError(IncompatibleTypes())
        }
    }
}