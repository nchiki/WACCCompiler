package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.LoadBInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Nodes.Literals.BoolLitNode
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
        //println(symbolTable == null)
        val address = symbolTable?.getValueAddress(id)!!
        val reg = codeGenerator.getParamReg()

        val offset = codeGenerator.sp - address

        val expr = symbolTable!!.lookupSymbol(id)

        if(expr is CharLitNode || expr is BoolLitNode) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(reg, "[sp, #-$offset]"))
        }else{
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, "[sp, #-$offset]", null))
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
