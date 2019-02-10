package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode



class BoolLitNode(val bool_val : String, val ctx: BasicParser.BoolLitContext) : ExprNode {

    val type = LitTypes.BoolWacc

    override fun getType() : BaseNode {
        return BaseNode("bool")
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
