package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import kotlin.system.exitProcess

class StatListNode(val listStatNodes: MutableList<Node>, override val ctx: BasicParser.StatListContext) : Node{

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        for (stat in listStatNodes) {
            stat.generateCode(codeGenerator)
        }
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        for (stat in listStatNodes) {
            stat.semanticCheck(errors, table)
        }
    }
}
