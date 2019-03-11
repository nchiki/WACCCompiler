package main.kotlin.Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.Utils.getTypeSize
import src.main.kotlin.Nodes.ExprNode

class ArrayLitNode(val exprList: MutableList<ExprNode>, override val ctx: BasicParser.ArrayLiterContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not needed")

    override fun generateCode(codeGenerator: CodeGenerator) {
        val curLabel = codeGenerator.curLabel

        /* The register with the address of the array */
        val baseReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, SubInstr(Register.sp, "#$size"))

        // Call the malloc function to allocate the necessary memory
        if (exprList.count() != 0) {
            codeGenerator.addInstruction(curLabel, LoadInstr(Register.r0,
                    size + (exprList[0].size * exprList.count()), null))
        } else {
            codeGenerator.addInstruction(curLabel, LoadInstr(Register.r0, size))
        }
        codeGenerator.addInstruction(curLabel, BLInstr("malloc"))
        codeGenerator.addInstruction(curLabel, MovInstr(baseReg, Register.r0))

        if (exprList.size == 0) {
            /* Store 0 as size of array and be done */
            val tempReg = codeGenerator.getFreeRegister()
            codeGenerator.addInstruction(curLabel, LoadInstr(tempReg, exprList.size))
            codeGenerator.addInstruction(curLabel, StoreInstr(tempReg, "[$baseReg]"))
            codeGenerator.freeReg(tempReg)
            return
        }

        /* Add each element to the array*/
        val size = getTypeSize(exprList[0].getBaseType())
        for (i in 0 until exprList.size) {
            exprList[i].generateCode(codeGenerator)
            val valReg = codeGenerator.getLastUsedReg()
            if (getBaseType() == LitTypes.BoolWacc || getBaseType() == LitTypes.CharWacc) {
                codeGenerator.addInstruction(curLabel, StrBInstr(valReg, "[$baseReg, #${4 + size * i}]"))
            } else {
                codeGenerator.addInstruction(curLabel, StoreInstr(valReg, "[$baseReg, #${4 + size * i}]"))
            }
            codeGenerator.freeReg(valReg)
        }

        /* Store the size of the array */
        val tempReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(curLabel, LoadInstr(tempReg, exprList.size))
        codeGenerator.addInstruction(curLabel, StoreInstr(tempReg, "[$baseReg]"))
        codeGenerator.freeReg(tempReg)
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBaseType(): LitTypes {
        if (exprList.size > 0) {
            return exprList[0].getBaseType()
        }
        return LitTypes.ArrayLit
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (exprList.size == 0) {
            return
        }
        val type = exprList[0].getBaseType()
        for (expr in exprList) {
            if (type != expr.getBaseType()) {
                errors.addError(IncompatibleTypes(ctx, type.toString(), expr, table))
            }
            expr.semanticCheck(errors, table)
        }
    }
}
