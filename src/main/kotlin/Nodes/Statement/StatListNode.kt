package main.kotlin.Nodes.Statement

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.ValueTable
import kotlin.system.exitProcess

class StatListNode(val listStatNodes: MutableList<Node>, override val ctx: BasicParser.StatListContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 0

    override fun generateCode(codeGenerator: CodeGenerator) {
        for (stat in listStatNodes) {
            stat.generateCode(codeGenerator)
        }
    }

    override fun optimise(valueTable: ValueTable): Node {
        for (i in (0 until listStatNodes.size)) {
            listStatNodes[i] = listStatNodes[i].optimise(valueTable)
        }
        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
            exitProcess(100)
        }

        for (stat in listStatNodes) {
            stat.semanticCheck(errors, table)
        }
    }
}
