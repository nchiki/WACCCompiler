package main.kotlin.Nodes.Expressions


import main.kotlin.Instructions.AddInstr
import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.InvalidOperandTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BinaryOperContext, override val ctx: ParserRuleContext) : ExprNode {
    override val weight: Int
        get() = left.weight + right.weight + 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        val comparison = codeGenerator.compareWeights(left.weight, right.weight)
        if(comparison > 0) {
            left.generateCode(codeGenerator)
            right.generateCode(codeGenerator)
            val reg1 = codeGenerator.regsNotInUse.get(0)
            val reg2 = codeGenerator.regsNotInUse.get(1)
            codeGenerator.addInstruction(codeGenerator.curLabel, getInstruction(reg1, reg2))

        } else {
            right.generateCode(codeGenerator)
            left.generateCode(codeGenerator)
            val reg2 = codeGenerator.regsNotInUse.get(0)
            val reg1 = codeGenerator.regsNotInUse.get(1)
            codeGenerator.addInstruction(codeGenerator.curLabel, getInstruction(reg1, reg2))

        }
        codeGenerator.regsNotInUse.removeAt(0)
        //MAYBE WE JUST NEED TO REMOVE THE ONE THAT HOLDS THE RESULT
        //codeGenerator.regsNotInUse.removeAt(1)
    }

    fun getInstruction(reg1: Register, reg2:Register) : Instruction{
        if (operator.MULT() != null){
            return MultInstr(reg1, reg2)
            // CHECK FOR OVERFLOW
        }else if (operator.DIV() != null) {
            return BLInstr("__aeabi_idiv")
        } else if(operator.MOD() != null) {
            return BLInstr("__aeabi_idivmod")
        }else if(operator.MINUS() != null) {
            return SubInstr(reg1, reg2)
        }else if(operator.PLUS() != null) {
            return AddInstr(reg1, reg1, reg2)
        } else {
            return CmpInstr(reg1, reg2)
        }

    }

    //differs between a Boolean expression or calculation of two operands
    override fun getBaseType(): LitTypes {
        if(operator.MULT() != null
                || operator.DIV() != null
                || operator.MOD() != null
                || operator.MINUS() != null
                || operator.PLUS() != null)
        {
            return LitTypes.IntWacc
        } else {
            return LitTypes.BoolWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //check the semantics of both operands
        left.semanticCheck(errors, table)
        right.semanticCheck(errors, table)

        var realLeft = left

        /* Get left value from symbol table */
        if (left is IdentNode) {
            val leftValue = table.lookupSymbol(left.id)
            if (leftValue is PairNode) {
                errors.addError(InvalidOperandTypes(ctx))
            }

            if (leftValue == null) {
                errors.addError(UndefinedVariable(ctx, left.id))
                return
            }

            realLeft = leftValue
        }

        var realRight = right
        if (right is IdentNode) {
            val rightValue = table.lookupSymbol(right.id)

            if(rightValue == null) {
                errors.addError(UndefinedVariable(ctx, right.id))
                return
            }

            realRight = rightValue
        }

        if(realLeft is ArrayTypeNode || realLeft is ArrayLitNode){
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realLeft, table))
            return
        }

        if(realRight is ArrayTypeNode || realRight is ArrayLitNode){
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realRight, table))
            return
        }

        if(realLeft is BoolLitNode && getBaseType() != LitTypes.BoolWacc) {
            errors.addError(InvalidOperandTypes(ctx))
        }

        /* Can only be Integer operator now  */
        if(realLeft.getBaseType() != realRight.getBaseType()){
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realLeft, table))
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realRight, table))
        }
    }

}
