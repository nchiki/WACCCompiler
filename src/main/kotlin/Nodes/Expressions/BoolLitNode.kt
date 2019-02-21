package main.kotlin.Nodes.Literals

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.FalseDef
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.Utils.TrueDef
import src.main.kotlin.Nodes.ExprNode



class BoolLitNode(val bool_val : String, override val ctx: BasicParser.BoolLitContext) : ExprNode {
    override val size: Int
        get() = 1

    override val weight: Int
        get() = 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        //add instructions to main
        val reg = codeGenerator.getParamReg()
        //add instructions to label
        if(bool_val == "false") {
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg, "#0",
                    null))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg, "#1",
                    null))
        }
    }
    override fun getBaseType() : LitTypes {
        return LitTypes.BoolWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //base types do not need to be checked
    }

}
