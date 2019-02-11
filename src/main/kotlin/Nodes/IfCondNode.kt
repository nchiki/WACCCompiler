package src.main.kotlin

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode


class IfCondNode(// condition (should evaluate to boolean val
        private val expr: ExprNode?, // expr = true -> statement
        private val ifTrueStat: Node?, // expr = false -> statement
        private val elseStat: Node?, override val ctx: BasicParser.ExprContext) : Node {

    fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // check whether the expr evaluates to boolean value

        table.boolExprCheck(expr!!, errors)

        //checks both statements
        ifTrueStat?.semanticCheck(errors, table)
        elseStat?.semanticCheck(errors, table)

    }


}
