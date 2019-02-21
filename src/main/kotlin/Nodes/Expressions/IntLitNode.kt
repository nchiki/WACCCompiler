package src.main.kotlin.Nodes.Literals

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.IntAppendDef
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode


class IntLitNode(val int_val : Long, override val ctx: BasicParser.IntLitContext) : ExprNode {

    override val size: Int
        get() = 4

            override val weight: Int
        get() = 1

    override fun generateCode(codeGenerator: CodeGenerator) {

        //add instructions to main
        val reg = codeGenerator.getParamReg()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, this, null))

        //add intvalue to data section
        codeGenerator.dataAppendices.add(IntAppendDef())
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.IntWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for the Literals
    }
}
