package main.kotlin.Nodes.Statement


import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ArrayElemNode

class IncrementNode(val id : String, override val ctx: BasicParser.IncrementContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 1

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        val value = table.lookupSymbol(id)

        if (value == null || value is FunctionNode || value is ArrayElemNode
                || value is PairElemNode || value is PairNode || value is ArrayLitNode) {
            errors.addError(UndefinedVariable(ctx, id))
        }
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override fun generateCode(codeGenerator: CodeGenerator) {

         /* Calculate the position in the stack */
        val identOffset = symbolTable?.getValueOffset(id, codeGenerator)
        var inMemory = "[sp]"
        if (identOffset != 0) {
            inMemory = "[sp, #$identOffset]"
        }
        val reg1 = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg1, inMemory, null))
        val reg2 = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg2, 1))
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(reg1, reg1, reg2, "S"))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_throw_overflow_error", Condition.VS))

        /* Store to memory based on correct type */
        val type = symbolTable!!.lookupSymbol(id)!!.getBaseType()
        if (type.equals(LitTypes.CharWacc) || type.equals(LitTypes.BoolWacc)) {
            codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(reg1, inMemory))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(reg1, inMemory))
        }
        if (reg2 != Register.r0) {
            codeGenerator.freeReg(reg2)
        }
    }
}