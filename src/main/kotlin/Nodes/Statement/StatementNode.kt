package Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.ValueTable
import kotlin.system.exitProcess

class StatementNode(var stat: Node, override val ctx: BasicParser.StatementContext?) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = stat.weight

    override fun optimise(valueTable: ValueTable): Node {
        valueTable.markAllAsDynamic()
        val childValueTable = ValueTable(valueTable)
        stat = stat.optimise(childValueTable)
        return this
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        stat.generateCode(codeGenerator)
        symbolTable!!.recoverSp(codeGenerator)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
            exitProcess(100)
        }
        var childTable = SymbolTable(table)
        this.symbolTable = childTable
        stat.semanticCheck(errors, childTable)
    }
}
