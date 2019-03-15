package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.LoadSBInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class IdentNode(val id : String, override val ctx: ParserRuleContext?) : ExprNode {

    override val size: Int
        get() {
            if (symbolTable == null) {
                return 0
            }
            val sizeNod = symbolTable!!.lookupSymbol(id)
            if(sizeNod != null) {
                return sizeNod.size
            } else {
                return 4
            }
        }

    override val weight: Int
        get() =  1

    override var symbolTable: SymbolTable? = null

    override fun generateCode(codeGenerator: CodeGenerator) {
        if(symbolTable!!.getFunction(id) != null) {
            symbolTable?.declareVariable(id, symbolTable!!.sp, 4)
            //symbolTable!!.sp += 4// add offset to stack pointer
        }
        val offset = symbolTable?.getValueOffset(id, codeGenerator)!!

        var inMemory = "[sp]"
        if(offset != 0) {
            if (offset < 0) {
                inMemory = "[sp, #${-offset}]"
            } else {
                inMemory = "[sp, #${offset}]"
            }
        }
        val reg = codeGenerator.getFreeRegister()

        //get respective node from symboltable
        val expr = symbolTable!!.lookupSymbol(id)

        //check for every possible type of identNode
        if(expr is ArrayTypeNode) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, inMemory, null))
        } else if(expr is ExprNode && expr.getBaseType() == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadSBInstr(reg, inMemory))
        }else if (expr is ExprNode && expr.getBaseType() == LitTypes.CharWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadSBInstr(reg, inMemory))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, inMemory, null))
        }

    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
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
            if(table.getFunction(id) == null) {
                errors.addError(UndefinedVariable(ctx!!, id))
            }
        }
    }
}
