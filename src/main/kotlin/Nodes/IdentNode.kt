package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.LoadBInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.LoadSBInstr
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode
import kotlin.math.exp

class IdentNode(val id : String, override val ctx: ParserRuleContext) : ExprNode {
    override val size: Int
        get() = 4//table.lookupSymbol(id).size

    override val weight: Int
        get() =  1

    override var symbolTable: SymbolTable? = null

    override fun generateCode(codeGenerator: CodeGenerator) {
        //println(symbolTable == null)
        val offset = symbolTable?.getValueAddress(id)!!
        val reg = codeGenerator.getParamReg()

        val expr = symbolTable!!.lookupSymbol(id)

        if(expr is ExprNode && expr.getBaseType() == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(reg, "[sp, #$offset]"))
        }else if (expr is ExprNode && expr.getBaseType() == LitTypes.CharWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadSBInstr(reg, "[sp, #$offset]"))
        }else{
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, "[sp, #$offset]", null))
        }


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
