package main.kotlin.Nodes

import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.NullReferDef
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode

class LHS_Node(val Nodetype: Any?, val id: String, val line: Int, val pos : Int,
               override val ctx: BasicParser.AssignLHSContext) : ExprNode {

    override val size: Int
        get() = 4
    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var symbolTable: SymbolTable? = null

    /* Puts the address of the variable into a register */
    override fun generateCode(codeGenerator: CodeGenerator) {
        val regRHS = codeGenerator.getLastUsedReg()

        if(Nodetype is ArrayElemNode){
            /* Resolve the address of the array element and put it into a register */
            Nodetype.resolveToAddress(codeGenerator)
            val addressReg = codeGenerator.getLastUsedReg()

            /* Store RHS value into the address of the array element */
            if(getBaseType().equals(LitTypes.CharWacc) || getBaseType().equals(LitTypes.BoolWacc)) {
                codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$addressReg]"))
            } else {
                codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, "[$addressReg]"))
            }

            codeGenerator.freeReg(addressReg)
            codeGenerator.freeReg(regRHS)
            return
        }

        if(Nodetype is PairElemNode) {

            /* Resolve the address of the pair */
            val offset = symbolTable?.getValueOffset((Nodetype.expr as IdentNode).id, codeGenerator)!!
            var inMemory = "[sp]"
            if (offset != 0) {
                inMemory = "[sp, #${offset}]"
            }
            val addressReg = codeGenerator.getFreeRegister()
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(addressReg, inMemory, null))

            /* Store the first element of the pair */
            if (Nodetype.elem == 0) {
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(addressReg, addressReg, null))
                if (getBaseType().equals(LitTypes.CharWacc) || getBaseType().equals(LitTypes.BoolWacc)) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$addressReg]"))
                } else {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, "[$addressReg]"))
                }

                codeGenerator.freeReg(addressReg)
                codeGenerator.freeReg(regRHS)
                return
            } else {
                val node = symbolTable?.lookupSymbol((Nodetype.expr as IdentNode).id) as PairNode
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(addressReg, "[$addressReg, #4]", null))
                /* Store the second element of the pair */
                if (getBaseType().equals(LitTypes.CharWacc) || getBaseType().equals(LitTypes.BoolWacc)) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$addressReg]"))
                } else {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, "[$addressReg]"))
                }

                codeGenerator.freeReg(addressReg)
                codeGenerator.freeReg(regRHS)
                return
            }
        }

        /* LHS can only be an identifier now */

        /* Calculate the position in the stack */
        val identOffset = symbolTable?.getValueOffset(id, codeGenerator)
        var inMemory = "[sp]"
        if(identOffset != 0){
            inMemory = "[sp, #$identOffset]"
        }

        /* Store to memory based on correct type */
        val type = symbolTable!!.lookupSymbol(id)!!.getBaseType()
        if (type.equals(LitTypes.CharWacc) || type.equals(LitTypes.BoolWacc)) {
            codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, inMemory))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, inMemory))
        }

        codeGenerator.freeReg(regRHS)
    }

    override fun getBaseType(): LitTypes {
        if (Nodetype is ArrayElemNode) {
            return Nodetype.getBaseType()
        } else if (Nodetype is PairElemNode) {
            return Nodetype.getBaseType()
        } else {
            return LitTypes.IdentWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        val value = table.lookupSymbol(id)

        if( value == null || value is FunctionNode) {
            errors.addError(UndefinedVariable(ctx, id))
        }

        if(Nodetype is IdentNode){
            Nodetype.semanticCheck(errors, table)
        }

        if (Nodetype is ArrayElemNode) {
            Nodetype.semanticCheck(errors, table)
        }
        if (Nodetype is PairElemNode) {
            Nodetype.semanticCheck(errors, table)
        }
    }

}
