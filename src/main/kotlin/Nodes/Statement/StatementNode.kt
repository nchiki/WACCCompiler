package Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import kotlin.system.exitProcess

class StatementNode(val stat : Node, override val ctx: BasicParser.StatementContext?): Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = stat.weight

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        //val label = codeGenerator.getNewLabel()
        //codeGenerator.addLabel(label, null)
        //codeGenerator.curLabel = label
        //codeGenerator.curScope = label

        stat.generateCode(codeGenerator)

        symbolTable!!.recoverSp(codeGenerator)
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }
        var childTable = SymbolTable(table)
        this.symbolTable = childTable
        stat.semanticCheck(errors, childTable)
    }
}
