package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.reflect.KClass


class BoolLitNode(val bool_val : String) : ExprNode {

    override fun getType() : BaseNode {
        return BaseNode("bool")
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
