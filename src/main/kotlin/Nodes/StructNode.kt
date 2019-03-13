package main.kotlin.Nodes

import Nodes.DeclNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class StructNode(val id : String, var exprs: List<Node>, override val ctx: ParserRuleContext?) : ExprNode{
    override fun getBaseType(): LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var symbolTable: SymbolTable? = null


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val childTable = SymbolTable(table)
        table.add(this, id)
        this.symbolTable = childTable
        symbolTable!!.add(this, id)
        for (expr in exprs) {
            expr.semanticCheck(errors, childTable)
        }
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        val curLabel = codeGenerator.curLabel

        /* The register with the address of the array */
        val baseReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, SubInstr(Register.sp, "#$size"))

        // Call the malloc function to allocate the necessary memory
        if (exprs.count() != 0) {
            var size = 0
            for (expr in exprs) {
                expr as DeclNode
                size += expr.type.size
            }
            codeGenerator.addInstruction(curLabel, LoadInstr(Register.r0,
                    size + (size), null))
        } else {
            codeGenerator.addInstruction(curLabel, LoadInstr(Register.r0, size))
        }


        codeGenerator.addInstruction(curLabel, BLInstr("malloc"))
        codeGenerator.addInstruction(curLabel, MovInstr(baseReg, Register.r0))

        if (exprs.size == 0) {
            /* Store 0 as size of struct and be done */
            val tempReg = codeGenerator.getFreeRegister()
            codeGenerator.addInstruction(curLabel, LoadInstr(tempReg, exprs.size))
            codeGenerator.addInstruction(curLabel, StoreInstr(tempReg, "[$baseReg]"))
            codeGenerator.freeReg(tempReg)
            symbolTable!!.recoverSp(codeGenerator)
            return
        }

        /* Add each element to the struct*/
        var curSize = 4
        for (expr in exprs) {
            expr as DeclNode
            expr.generateCode(codeGenerator)
            val valReg = codeGenerator.getLastUsedReg()
            if (expr.type.getBaseType() == LitTypes.BoolWacc || expr.type.getBaseType() == LitTypes.CharWacc) {
                codeGenerator.addInstruction(curLabel, StrBInstr(valReg, "[$baseReg, #${curSize + size}]"))
                curSize += size
            } else {
                codeGenerator.addInstruction(curLabel, StoreInstr(valReg, "[$baseReg, #${curSize + size}]"))
                curSize += size
            }
            codeGenerator.freeReg(valReg)
        }

        /* Store the size of the struct */
        val tempReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(curLabel, LoadInstr(tempReg, exprs.size))
        codeGenerator.addInstruction(curLabel, StoreInstr(tempReg, "[$baseReg]"))
        codeGenerator.freeReg(tempReg)

        symbolTable!!.recoverSp(codeGenerator)
    }
}