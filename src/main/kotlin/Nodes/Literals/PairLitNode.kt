package Nodes.Literals

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PairLitNode(override val ctx : BasicParser.PairLitContext): ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        val reg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, 0, null))
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.PairWacc
    }

}
