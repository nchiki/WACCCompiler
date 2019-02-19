package main.kotlin.Nodes.Statement

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class PrintLnStatNode(val expr : ExprNode, override val ctx: BasicParser.PrintlnContext) : Node{

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn){
            exitProcess(100)
        }

        expr.semanticCheck(errors, table)
    }
}
