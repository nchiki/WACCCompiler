package Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.ValueTable

class ParamListNode(// list of parameterNodes
        val listParamNodes: MutableList<ParamNode>, override val ctx: BasicParser.ParamListContext?) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 0

    override fun generateCode(codeGenerator: CodeGenerator) {
        for (param in listParamNodes) {
            param.generateCode(codeGenerator)
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        // iterates through the list of parameters, checking semantics of each of them
        for (param in listParamNodes) {
            param.semanticCheck(errors, table)
        }
    }


    override fun optimise(valueTable: ValueTable): Node {
        return this
    }
}
