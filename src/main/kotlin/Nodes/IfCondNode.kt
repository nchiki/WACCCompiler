package src.main.kotlin

import Errors.NotBoolConditionError
import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class IfCondNode(// condition (should evaluate to boolean val
        private val expr: Node?, // expr = true -> statement
        private val ifTrueStat: Node?, // expr = false -> statement
        private val elseStat: Node?, val ctx: BasicParser.ExprContext) : StatementNode {

    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // check whether the expr evaluates to boolean value

        //table.boolExprCheck(expr!!, errors, table, ctx)
        if(expr?.getType() != LitTypes.BoolWacc) {
            errors.addError(NotBoolConditionError(ctx.start.line, ctx.start.charPositionInLine))
        }
        //checks both statements
        ifTrueStat?.semanticCheck(errors, table)
        elseStat?.semanticCheck(errors, table)

    }


}
