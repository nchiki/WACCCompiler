package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class FunctionNode (val id: String, val fun_type: LitTypes, val params: ParamListNode?, val stat: Node,
                    override val ctx: BasicParser.FuncContext) : ExprNode {
    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBaseType() : LitTypes {
        return fun_type
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        table.currentFunction = this
        table.currentExecutionPathHasReturn = false

        if (params != null) {
            params.semanticCheck(errors, table)
        }

        stat.semanticCheck(errors, table)

        /* Check if the function doesn't return */
        if(!table.currentExecutionPathHasReturn){
            exitProcess(100)
        }

        table.currentFunction = null
    }

}
