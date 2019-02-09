package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.reflect.KClass

class ExitStatNode(val expr : ExprNode) : Node {
    override fun getType(): KClass<ExitStatNode> {
        return ExitStatNode::class
    }

    override fun syntaxCheck() {
        //not needed
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr !is IntLitNode) {
            errors.addError(IncompatibleTypes())
        }
        expr.semanticCheck(errors, table)
    }
}