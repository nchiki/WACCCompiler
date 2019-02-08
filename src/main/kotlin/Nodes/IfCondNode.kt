package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable


class IfCondNode(expr: Node?, ifTrueStat: Node?, elseStat: Node?) : Node {
    override fun getType() {
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

        table.boolExprCheck(expr!!, errors)

        //checks both statements
        ifTrueStat?.semanticCheck(errors, table)
        elseStat?.semanticCheck(errors, table)

    }


}
