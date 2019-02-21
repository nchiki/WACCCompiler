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

    override var symbolTable: SymbolTable? = null

    override fun generateCode(codeGenerator: CodeGenerator) {
        val offset = codeGenerator.returnOffset(id)!!
        val value = codeGenerator.sp - offset
        if(value < 0) {
            codeGenerator.addInstruction(codeGenerator.curLabel,
                    LoadInstr(codeGenerator.getParamReg(), "[sp, #${-offset}]", null))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel,
                    LoadInstr(codeGenerator.getParamReg(), "[sp]", null))
        }
        codeGenerator.removeUsedReg()
    }

    override fun getBaseType() : LitTypes {
        return LitTypes.IdentWacc
    }

    fun getValueType(table: SymbolTable) : ExprNode? {
        return table.lookupSymbol(id)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if(table.lookupSymbol(id) == null){
            errors.addError(UndefinedVariable(ctx, id))
        }
    }
}
