package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Instructions.StoreInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.Utils.getTypeSize
import src.main.kotlin.Nodes.ExprNode

class ArrayLitNode(val exprList : MutableList<ExprNode>, override val ctx : BasicParser.ArrayLiterContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        val curLabel = codeGenerator.curLabel

        /* The register with the address of the array */
        val baseReg = codeGenerator.getFreeRegister()

        // Call the malloc function to allocate the necessary memory
        codeGenerator.addInstruction(curLabel, LoadInstr(Register.r0, 4 + (exprList.size * 4), null))
        codeGenerator.addInstruction(curLabel, BLInstr("malloc"))
        codeGenerator.addInstruction(curLabel, MovInstr(baseReg, Register.r0))

        /* Add each element to the array*/
        val size = getTypeSize(exprList[0].getBaseType())
        for (i in 0 until exprList.size) {
            exprList[i].generateCode(codeGenerator)
            val valReg = codeGenerator.getLastUsedReg()
            codeGenerator.addInstruction(curLabel, StoreInstr(valReg, "[$baseReg, #${4 + size * i}]"))
            codeGenerator.freeReg(valReg)
        }

        /* Store the size of the array */
        val tempReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(curLabel, LoadInstr(tempReg, exprList.size, null))
        codeGenerator.addInstruction(curLabel, StoreInstr(tempReg, "[$baseReg]"))
        codeGenerator.freeReg(tempReg)
    }

    override fun getBaseType() : LitTypes {
            if (exprList.size > 0) {
            return exprList[0].getBaseType()
        }
        return LitTypes.ArrayLit
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if(exprList.size == 0){
            return
        }
        val type = exprList[0].getBaseType()
        for (expr in exprList) {
            if(type != expr.getBaseType()) {
                errors.addError(IncompatibleTypes(ctx, type.toString(), expr, table))
            }
            expr.semanticCheck(errors, table)
        }
    }
}
