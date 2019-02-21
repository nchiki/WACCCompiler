package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.Utils.StringAppendDef
import main.kotlin.Utils.StringLitDef
import src.main.kotlin.Nodes.ExprNode


class StringLitNode(val str : String, override val ctx: BasicParser.StrLitContext) : ExprNode {
    override val size: Int
        get() = 4

    override val weight: Int
        get() = 1

    override fun generateCode(codeGenerator: CodeGenerator) {

        //entries for data
        val msg = "msg_${codeGenerator.data.size}"
        codeGenerator.data.put(msg, StringLitDef(str))

        //instructions for main
        val reg = codeGenerator.getParamReg()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, msg, null))
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.StringWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for Literals
    }

}
