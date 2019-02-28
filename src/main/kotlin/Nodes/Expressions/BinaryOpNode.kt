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
import main.kotlin.Utils.*
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val addSub: BasicParser.AddSubContext?,
                   val mulDiv: BasicParser.MultDivContext?, val eqOp: BasicParser.Eq_OpContext?,
                   override val ctx: ParserRuleContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() {
            return when(getBaseType()) {
                LitTypes.BoolWacc -> 1
                else -> 4
            }
        }

    override val weight: Int
        get() = left.weight + right.weight + 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        var leftReg : Register
        var rightReg : Register

        var lastReg : Register

        val comparison = codeGenerator.compareWeights(left.weight, right.weight)
        // evaluates expression that needs more registers first
        if(comparison >= 0) {
            left.generateCode(codeGenerator)
            leftReg = codeGenerator.getLastUsedReg()
            right.generateCode(codeGenerator)
            rightReg = codeGenerator.getLastUsedReg()
            lastReg = rightReg
        } else {
            right.generateCode(codeGenerator)
            rightReg = codeGenerator.getLastUsedReg()
            left.generateCode(codeGenerator)
            leftReg = codeGenerator.getLastUsedReg()
            lastReg = leftReg
        }
        // if its a boolean operation, we need an extra instruction for comparing both expressions
        if(getBaseType() == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(leftReg, rightReg, ""))
        }

        getInstruction(leftReg, rightReg, codeGenerator)

        /* Results are stored in the left register, so we need to move the result into the correct register */
        if(lastReg == leftReg){
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(rightReg, leftReg, Condition.AL))
        }
        if(lastReg != Register.r0) {
            codeGenerator.freeReg(lastReg)
        }
    }




    fun getInstruction(reg1: Register, reg2:Register, codeGenerator: CodeGenerator){
        val zeroLabel = "p_check_divide_by_zero"
        if (mulDiv != null) {
            if (mulDiv.MULT() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, SMultInstr(reg1, reg2))
                codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(reg2, reg1, "ASR #31"))
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_throw_overflow_error", Condition.NE))
                codeGenerator.addError(OverflowDef)
                codeGenerator.addHelper("p_throw_overflow_error")
            } else if (mulDiv.DIV() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, reg1))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r1, reg2))
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(zeroLabel))
                codeGenerator.addHelper(zeroLabel)
                codeGenerator.addError(DivZeroDef)
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("__aeabi_idiv"))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, Register.r0))
            } else if (mulDiv.MOD() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, reg1))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r1, reg2))
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(zeroLabel))
                codeGenerator.addHelper(zeroLabel)
                codeGenerator.addError(DivZeroDef)
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("__aeabi_idivmod"))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, Register.r1))
            }
        }
        else if (addSub != null) {
            if (addSub.MINUS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, SubInstr(reg1, reg2, "S"))
            } else if (addSub.PLUS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(reg1, reg1, reg2, "S"))
            }
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_throw_overflow_error", Condition.VS))
            codeGenerator.addHelper("p_throw_overflow_error")
            codeGenerator.addError(OverflowDef)
        }
        else if (eqOp != null) {
            if (eqOp.GREAT() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.GT))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#0", Condition.LE))
                //IDK if maybe we need to add case for notgreater?
            } else if (eqOp.GREAT_EQ() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.GE))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#0", Condition.LT))
            } else if (eqOp.LESS_EQ() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.LE))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#0", Condition.GT))
            } else if (eqOp.LESS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.LT))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#0", Condition.GE))
            } else if (eqOp.EQ() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.EQ))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#0", Condition.NE))
            } else {
                //can only be not equal now
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.NE))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#0", Condition.EQ))
            }
        }
        //moves result to r0
        //codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, codeGenerator.getLastUsedReg()))

        //adds leftReg as last reg used in order to get the result of the operation
        codeGenerator.regsInUse.remove(reg1)
        codeGenerator.regsInUse.add(reg1)
    }

    //differs between a Boolean expression or calculation of two operands
    override fun getBaseType(): LitTypes {
        if (mulDiv != null || addSub != null) {
            return LitTypes.IntWacc
        } else {
            return LitTypes.BoolWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        this.symbolTable = table
        //check the semantics of both operands
        left.semanticCheck(errors, table)
        right.semanticCheck(errors, table)

        var realLeft = left

        /* Get left value from symbol table */
        if (left is IdentNode) {
            val leftValue = table.lookupSymbol(left.id)

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

        if(realLeft is PairNode || realRight is PairNode){
            if (eqOp == null || eqOp.EQ() == null && eqOp.NOTEQ() == null) {
                errors.addError(InvalidOperandTypes(ctx))
                return
            }
            return
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
