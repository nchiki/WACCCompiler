package main.kotlin.Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.InvalidOperandTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode

class UnaryOpNode(var operand: ExprNode, val operator: BasicParser.UnaryOperContext, type: Any,
                  override val ctx: BasicParser.UnOpContext) : ExprNode {

    override val size: Int
        get() {
            return when (operator.text) {
                //negation not implemented yet
                "!" -> 4
                "ord" -> 4
                "len" -> 4
                "chr" -> 1
                "-" -> 4
                else -> 4
            }
        }
    override var symbolTable: SymbolTable? = null

    override fun optimise(valueTable: ValueTable): Node {
        operand = operand.optimise(valueTable) as ExprNode

        /* Can only optimise !true or !false */
        if (operand !is BoolLitNode || operator.NOT() == null) {
            return this
        }

        return BoolLitNode((!(operand as BoolLitNode).bool_val.toBoolean()).toString(), null)
    }

    override val weight: Int
        get() = 1 + operand.weight

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.curLabel

        // Generates the code in the expression that will occupy the first register of regsNotInUse
        operand.generateCode(codeGenerator)

        when (operator.text) {
            "!" -> codeGenerator.addInstruction(label, EORInstr(codeGenerator.getLastUsedReg(), 1))
            "ord" -> return
            "len" -> codeGenerator.addInstruction(label, LoadInstr(codeGenerator.getLastUsedReg(),
                    "[${codeGenerator.getLastUsedReg()}]", Condition.AL))
            "chr" -> return

            // A negative number is the same as 0 - positive number --> need to access the register that
            // has just been allocated in lastUsedReg.
            "-" -> {
                val reg = codeGenerator.getLastUsedReg()
                val otherReg = codeGenerator.getFreeRegister()
                codeGenerator.addInstruction(label, MovInstr(otherReg, reg, null))
                codeGenerator.addInstruction(label, MovInstr(reg, "#0", null))
                codeGenerator.addInstruction(label, SubInstr(reg, otherReg, "S"))
                codeGenerator.addInstruction(label, BLInstr("p_throw_overflow_error", Condition.VS))
                if (otherReg != Register.r0) {
                    codeGenerator.freeReg(otherReg)
                }
            }
            //for the add instruction we dont need to do anything since its a positive number
            else -> return
        }

    }

    //return the type of the operand
    override fun getBaseType(): LitTypes {
        when (operator.text) {
            "!" -> return LitTypes.BoolWacc
            "chr" -> return LitTypes.CharWacc
            else -> return LitTypes.IntWacc
        }
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        var op = operand

        //get type of operand from Symboltable
        if (operand is IdentNode) {
            val opValue = table.lookupSymbol((operand as IdentNode).id)
            if (opValue == null) {
                errors.addError(UndefinedVariable(ctx, (operand as IdentNode).id))
                return
            }
            op = opValue
        }

        //check whether operand and operator are compatible
        if (operator.text == "!" && op.getBaseType() != LitTypes.BoolWacc
                || operator.text == "minus" && op.getBaseType() != LitTypes.IntWacc
                || operator.text == "len" && op !is ArrayTypeNode
                || operator.text == "ord" && op.getBaseType() != LitTypes.CharWacc
                || operator.text == "chr" && op.getBaseType() != LitTypes.IntWacc) {

            errors.addError(InvalidOperandTypes(ctx))
        }

        operand.semanticCheck(errors, table)
    }
}
