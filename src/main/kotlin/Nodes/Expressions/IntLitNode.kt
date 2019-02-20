package src.main.kotlin.Nodes.Literals

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class IntLitNode(val int_val : Long, override val ctx: BasicParser.IntLitContext) : ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val reg = codeGenerator.regsNotInUse.get(0)
        codeGenerator.removeUsedReg()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, this))
    }
    override fun getBaseType(): LitTypes {
        return LitTypes.IntWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for the Literals
    }
}
