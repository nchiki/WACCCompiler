package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.ExprNode
import main.kotlin.Nodes.Literals.IntLitNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class ExitStatNode(val expr : ExprNode) : Node {
    override fun getType(): KClass<ExitStatNode> {
        return ExitStatNode::class
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() != IntLitNode::class) {
            errors.addError(IncompatibleTypes())
        }
        expr.semanticCheck(errors, table)
    }
}