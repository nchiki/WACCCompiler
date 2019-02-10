package src.main.kotlin

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass


class IfCondNode(expr: Node?, ifTrueStat: Node?, elseStat: Node?) : Node {
    override fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val expr = expr             // condition (should evaluate to boolean val
    private val ifTrueStat = ifTrueStat // expr = true -> statement
    private val elseStat = elseStat     // expr = false -> statement

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // check whether the expr evaluates to boolean value

        table.boolExprCheck(expr!!, errors, table)

        //checks both statements
        ifTrueStat?.semanticCheck(errors, table)
        elseStat?.semanticCheck(errors, table)

    }


}
