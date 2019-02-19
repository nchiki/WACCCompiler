package src.main.kotlin

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess


class IfCondNode(// condition (should evaluate to boolean val
        val expr: ExprNode?, // expr = true -> statement
        val ifTrueStat: Node?, // expr = false -> statement

        val elseStat: Node?, override val ctx: BasicParser.IfCondContext) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn){
            exitProcess(100)
        }

        //table.boolExprCheck(expr!!, errors, table, ctx)
        if(expr?.getBaseType() != LitTypes.BoolWacc) {
            errors.addError(IncompatibleTypes(ctx.expr(), "BOOL", expr!!, table))
        }

        val ifChildTable = SymbolTable(table)
        val elseChildTable = SymbolTable(table)

        ifChildTable.currentFunction = table.currentFunction
        elseChildTable.currentFunction = table.currentFunction

        //checks both statements
        ifTrueStat?.semanticCheck(errors, ifChildTable)

        elseStat?.semanticCheck(errors, elseChildTable)

        if(ifChildTable.currentExecutionPathHasReturn && elseChildTable.currentExecutionPathHasReturn){
            table.currentExecutionPathHasReturn = true
        }

    }

}
