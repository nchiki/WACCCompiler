package main.kotlin.Nodes.Expressions

import BasicParser
import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.InvalidOperandTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.ArrayLitNode
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class BinaryOpNode(var left: ExprNode, var right: ExprNode, val addSub: BasicParser.AddSubContext?,
                   val mulDiv: BasicParser.MultDivContext?, val eqOp: BasicParser.Eq_OpContext?,
                   override val ctx: ParserRuleContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() {
            return when (getBaseType()) {
                LitTypes.BoolWacc -> 1
                else -> 4
            }
        }


    override fun optimise(valueTable: ValueTable): Node {

        left = left.optimise(valueTable) as ExprNode
        right = right.optimise(valueTable) as ExprNode


        /* If the values are not constant then we can't optimize the expression */
        if (left !is IntLitNode && right !is IntLitNode) {
            return this
        }

        /* (Int, Int) -> Int operators*/
        if (eqOp == null) {
            val newVal = getIntOperator()((left as IntLitNode).int_val, (right as IntLitNode).int_val)

            /* Ensure the overflow error occurs at runtime */
            if (overFlowCheck(newVal)) {
                return this
            }

            return IntLitNode(newVal, null)
        }

        /* (Int, Int) -> Boolean operators */
        val boolVal = getCompOperator()((left as IntLitNode).int_val, (right as IntLitNode).int_val)

        return BoolLitNode(boolVal.toString(), null)
    }

    private fun getCompOperator(): (Long, Long) -> (Boolean) {
        if (eqOp!!.GREAT() != null) {
            return { a: Long, b: Long -> a > b }
        }
        if (eqOp.GREAT_EQ() != null) {
            return { a: Long, b: Long -> a >= b }
        }
        if (eqOp.LESS() != null) {
            return { a: Long, b: Long -> a < b }
        }
        if (eqOp.LESS_EQ() != null) {
            return { a: Long, b: Long -> a <= b }
        }
        if (eqOp.EQ() != null) {
            return { a: Long, b: Long -> a == b }
        }

        return { a: Long, b: Long -> a != b }
    }

    private fun getIntOperator(): Long.(Long) -> (Long) {
        if (mulDiv != null) {
            if (mulDiv.MULT() != null) {
                return Long::times
            }
            if (mulDiv.DIV() != null) {
                return Long::div
            }
            return Long::rem
        }
        if (addSub!!.PLUS() != null) {
            return Long::plus
        }
        return Long::minus
    }

    private fun overFlowCheck(value: Long): Boolean {
        return value >= Integer.MAX_VALUE
    }


    override val weight: Int
        get() = left.weight + right.weight + 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        val leftReg: Register
        val rightReg: Register

        val lastReg: Register

        // evaluates expression that needs more registers first
        if (codeGenerator.compareWeights(left.weight, right.weight) >= 0) {
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
        if (getBaseType() == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(leftReg, rightReg))
        }

        getInstruction(leftReg, rightReg, codeGenerator)

        /* Results are stored in the left register, so we need to move the result into the correct register */
        if (lastReg == leftReg) {
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(rightReg, leftReg, Condition.AL))
        }
        if (lastReg != Register.r0) {
            codeGenerator.freeReg(lastReg)
        }
    }


    fun getInstruction(reg1: Register, reg2: Register, codeGenerator: CodeGenerator) {
        if (mulDiv != null) {
            if (mulDiv.MULT() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, SMultInstr(reg1, reg2))
                codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(reg2, reg1, "ASR #31"))
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_throw_overflow_error", Condition.NE))
            } else {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, reg1))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r1, reg2))
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_check_divide_by_zero"))
                if (mulDiv.DIV() != null) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("__aeabi_idiv"))
                    codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, Register.r0))
                } else if (mulDiv.MOD() != null) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("__aeabi_idivmod"))
                    codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, Register.r1))
                }
            }
        } else if (addSub != null) {
            if (addSub.MINUS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, SubInstr(reg1, reg2, "S"))
            } else if (addSub.PLUS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(reg1, reg1, reg2, "S"))
            }
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_throw_overflow_error", Condition.VS))
        } else if (eqOp != null) {
            if (eqOp.GREAT() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.GT))
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#0", Condition.LE))
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

        //adds leftReg as last reg used in order to get the result of the operation
        codeGenerator.regsInUse.remove(reg1)
        codeGenerator.regsInUse.add(reg1)
    }

    //differs between a Boolean expression or calculation of two operands
    override fun getBaseType(): LitTypes {
        return if (mulDiv != null || addSub != null) {
            LitTypes.IntWacc
        } else {
            LitTypes.BoolWacc
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
            val leftValue = table.lookupSymbol((left as IdentNode).id)

            if (leftValue == null) {
                errors.addError(UndefinedVariable(ctx, (left as IdentNode).id))
                return
            }

            realLeft = leftValue
        }

        var realRight = right
        if (right is IdentNode) {
            val rightValue = table.lookupSymbol((right as IdentNode).id)

            if (rightValue == null) {
                errors.addError(UndefinedVariable(ctx, (right as IdentNode).id))
                return
            }

            realRight = rightValue
        }

        if (realLeft is PairNode || realRight is PairNode) {
            if (eqOp == null || eqOp.EQ() == null && eqOp.NOTEQ() == null) {
                errors.addError(InvalidOperandTypes(ctx))
                return
            }
            return
        }

        if (realLeft is ArrayTypeNode || realLeft is ArrayLitNode) {
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realLeft, table))
            return
        }

        if (realRight is ArrayTypeNode || realRight is ArrayLitNode) {
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realRight, table))
            return
        }

        if (realLeft is BoolLitNode && getBaseType() != LitTypes.BoolWacc) {
            errors.addError(InvalidOperandTypes(ctx))
        }

        /* Can only be Integer operator now  */
        if (realLeft.getBaseType() != realRight.getBaseType()) {
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realLeft, table))
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realRight, table))
        }
    }

}
