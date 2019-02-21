package main.kotlin.Nodes.Literals

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode



class BoolLitNode(val bool_val : String, override val ctx: BasicParser.BoolLitContext) : ExprNode {

    override val size = 1

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        if(bool_val == "false") {
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(codeGenerator.regsNotInUse.get(0), "#0",
                    null))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(codeGenerator.regsNotInUse.get(0), "#1",
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
