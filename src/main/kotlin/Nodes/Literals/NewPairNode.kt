package main.kotlin.Nodes.Literals

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode

class NewPairNode(override val ctx: BasicParser.AssignR_PairContext, val exprNode1: ExprNode, val exprNode2: ExprNode) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.curLabel
        val reg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(Register.r0, 8, null))

        codeGenerator.addInstruction(label, BLInstr("malloc"))
        codeGenerator.addInstruction(label, MovInstr(reg, Register.r0))
        exprNode1.generateCode(codeGenerator)
        codeGenerator.addInstruction(label, LoadInstr(Register.r0, exprNode1.size, null))
        codeGenerator.addInstruction(label, BLInstr("malloc"))
        if (exprNode1.getBaseType() == LitTypes.CharWacc || exprNode1.getBaseType() == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), Register.r0))
        } else {
            codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), Register.r0))
        }
        codeGenerator.addInstruction(label, StoreInstr(Register.r0, reg))
        codeGenerator.freeReg(codeGenerator.getLastUsedReg())
        exprNode2.generateCode(codeGenerator)
        codeGenerator.addInstruction(label, LoadInstr(Register.r0, exprNode2.size, null))
        codeGenerator.addInstruction(label, BLInstr("malloc"))
        val lastReg = codeGenerator.getLastUsedReg()
        if (exprNode2.getBaseType() == LitTypes.CharWacc || exprNode2.getBaseType() == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(label, StrBInstr(lastReg, Register.r0))
        } else {
            codeGenerator.addInstruction(label, StoreInstr(lastReg, Register.r0))
        }
        codeGenerator.addInstruction(label, StoreInstr(Register.r0, "[$reg, #4]"))
        codeGenerator.freeReg(lastReg)

        //codeGenerator.addInstruction(label, StoreInstr(reg, "[sp, #4]"))
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.PairWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        exprNode1.semanticCheck(errors, table)
        exprNode2.semanticCheck(errors, table)
    }

}
