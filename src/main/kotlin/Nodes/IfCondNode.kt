package src.main.kotlin

import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass


class IfCondNode(// condition (should evaluate to boolean val
        private val expr: Node?, // expr = true -> statement
        private val ifTrueStat: Node?, // expr = false -> statement
        private val elseStat: Node?, val ctx: BasicParser.ExprContext) : StatementNode() {

    override fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // check whether the expr evaluates to boolean value

        table.boolExprCheck(expr!!, errors, ctx)

        //checks both statements
        ifTrueStat?.semanticCheck(errors, table)
        elseStat?.semanticCheck(errors, table)

    }


}
