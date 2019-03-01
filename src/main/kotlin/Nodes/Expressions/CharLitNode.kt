package main.kotlin.Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class CharLitNode(val char: String, override val ctx: BasicParser.CharLitContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 1

    override val weight: Int
        get() = 1

    //move the value of char into a free Register
    override fun generateCode(codeGenerator: CodeGenerator) {
        val reg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg, this))
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.CharWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

}
