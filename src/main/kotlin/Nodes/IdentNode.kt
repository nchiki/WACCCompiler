package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.LoadInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class IdentNode(val id : String, override val ctx: ParserRuleContext) : ExprNode {
    override val size: Int
        get() = 4//table.lookupSymbol(id).size

    override val weight: Int
        get() =  1

    override fun generateCode(codeGenerator: CodeGenerator) {
        val offset = codeGenerator.returnOffset(id)!!
        codeGenerator.addInstruction(codeGenerator.curLabel,
                LoadInstr(codeGenerator.regsNotInUse.get(0), "[sp, #${-offset}]"))
        codeGenerator.removeUsedReg()
    }

    override fun getBaseType() : LitTypes {
        return LitTypes.IdentWacc
    }

    fun getValueType(table: SymbolTable) : ExprNode? {
        return table.lookupSymbol(id)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(table.lookupSymbol(id) == null){
            errors.addError(UndefinedVariable(ctx, id))
        }
    }
}
