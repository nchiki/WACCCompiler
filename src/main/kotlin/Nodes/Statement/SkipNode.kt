package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.system.exitProcess

class SkipNode(override val ctx: BasicParser.SkipContext): Node{



    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        //nothing to do here
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }
    }
}


