package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.InvalidOperandTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Instructions.SubInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class UnaryOpNode(val operand: ExprNode, val operator: BasicParser.UnaryOperContext, type : Any,
                  override val ctx: BasicParser.UnOpContext) : ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.curLabel
        // Generates the code in the expression that will occupy the first register of regsNotInUse
        operand.generateCode(codeGenerator)
        when (operator.text) {
            //negation not implemented yet
            "!" -> codeGenerator.addInstruction(label, MovInstr("", "", null))
            "ord" -> codeGenerator.addInstruction(label, BLInstr("putchar"))
            "len" -> return // loop iterating through the chars and incrementing a counter?
            "chr" -> codeGenerator.addInstruction(label,BLInstr("putchar"))
            // A negative number is the same as 0 - positive number. For that, we need to access the register that
            // has just been allocated in lastUsedReg.
            "-" -> {codeGenerator.addInstruction(label, MovInstr(codeGenerator.regsNotInUse.get(0),codeGenerator.getLastUsedReg(),null))
            codeGenerator.addInstruction(label, MovInstr(codeGenerator.getLastUsedReg(), "#0",null))
            codeGenerator.addInstruction(label, SubInstr(codeGenerator.getLastUsedReg(), codeGenerator.regsNotInUse.get(0)))}
            else -> return //for the add instruction we dont need to do anything since its a positive number
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
        var op = operand

        //get type of operand from Symboltable
        if(operand is IdentNode) {
            val opValue = table.lookupSymbol(operand.id)
            if (opValue == null) {
                errors.addError(UndefinedVariable(ctx, operand.id))
                return
            }
            op = opValue
        }

        //check whether operand and operator are compatible
        if (operator.text == "!" && op.getBaseType() != LitTypes.BoolWacc
                || operator.text == "minus" && op.getBaseType() != LitTypes.IntWacc
                || operator.text == "len" && op !is ArrayTypeNode
                || operator.text == "ord" && op.getBaseType() != LitTypes.CharWacc
                || operator.text == "chr" && op.getBaseType() != LitTypes.IntWacc)
        {

            errors.addError(InvalidOperandTypes(ctx))
        }
    }
}
