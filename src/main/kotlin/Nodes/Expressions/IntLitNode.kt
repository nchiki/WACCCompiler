package src.main.kotlin.Nodes.Literals

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode


class IntLitNode(val int_val: Long, override val ctx: BasicParser.IntLitContext?) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        //add instructions to main
        val reg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, this))
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.IntWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }
}


