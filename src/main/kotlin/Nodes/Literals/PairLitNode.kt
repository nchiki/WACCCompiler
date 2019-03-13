package Nodes.Literals

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode

class PairLitNode(override val ctx: BasicParser.PairLitContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(codeGenerator.getFreeRegister(), 0,
                null))
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.PairWacc
    }

}
