package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.system.exitProcess

class SkipNode(override val ctx: BasicParser.SkipContext?): Node{

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 0

    override fun generateCode(codeGenerator: CodeGenerator) {}

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }
    }
}


