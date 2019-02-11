package main.kotlin.Nodes.Statement

import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.PairElemNode
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClass

class FreeStatNode(val expr : ExprNode, override val ctx: BasicParser.FreeContext) : Node {
    fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr !is PairLitNode) {
            errors.addError(IncompatibleTypes(0, 0))
        }
    }
}