package main.kotlin.Nodes.Statement

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.system.exitProcess

class SkipNode(override val ctx: BasicParser.SkipContext): Node{



    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(table.currentExecutionPathHasReturn){
            exitProcess(100)
        }
    }
}


