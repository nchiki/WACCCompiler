package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class CharLitNode(val char : String, override val ctx: BasicParser.CharLitContext) : ExprNode {
    override val size: Int
        get() = 1

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val reg = codeGenerator.regsNotInUse.get(0)
        codeGenerator.removeUsedReg()
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg, this))
    }
    override fun getBaseType(): LitTypes {
        return LitTypes.CharWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for Literal
    }

}
