package Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import kotlin.system.exitProcess

class StatementNode(val stat : Node, override val ctx: BasicParser.StatementContext): Node {

    override val weight: Int
        get() = stat.weight

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.getNewLabel()

        codeGenerator.addLabel(label)
        codeGenerator.curLabel = label
        stat.generateCode(codeGenerator)

        codeGenerator.recoverSp()
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        var childTable = SymbolTable(table)
        stat.semanticCheck(errors, childTable)
    }
}
