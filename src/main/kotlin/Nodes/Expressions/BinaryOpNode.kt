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
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode
import main.kotlin.Errors.OverflowError
import main.kotlin.Utils.LiteralDefs

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
        var reg1 : Register
        var reg2 : Register

        val comparison = codeGenerator.compareWeights(left.weight, right.weight)
        // evaluates expression that needs more registers first
        if(comparison >= 0) {
            left.generateCode(codeGenerator)
            right.generateCode(codeGenerator)
            reg1 = codeGenerator.regsInUse.get(codeGenerator.regsInUse.count()-2)
            reg2 = codeGenerator.regsInUse.get(codeGenerator.regsInUse.count()-1)
        } else {
            right.generateCode(codeGenerator)
            left.generateCode(codeGenerator)
            reg2 = codeGenerator.regsInUse.get(codeGenerator.regsInUse.count()-2)
            reg1 = codeGenerator.regsInUse.get(codeGenerator.regsInUse.count()-1)
            }
        // if its a boolean operation, we need an extra instruction for comparing both expressions
        if(getBaseType() == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(reg1, reg2, ""))
        }
        // gets the correct instruction depending on the operator and adds it to codeGenerator
        getInstruction(reg1, reg2, codeGenerator)

        codeGenerator.regsNotInUse.removeAt(0)
        //MAYBE WE JUST NEED TO REMOVE THE ONE THAT HOLDS THE RESULT
        //codeGenerator.regsNotInUse.removeAt(1)
    }

    fun checkErrorTypes(codeGenerator: CodeGenerator) : String? {
        if (getBaseType() == LitTypes.IntWacc) {
            codeGenerator.addError(OverflowError)
            return "p_throw_overflow_error"
        }
        return ""
    }



    fun getInstruction(reg1: Register, reg2:Register, codeGenerator: CodeGenerator){
        val errorLabel = checkErrorTypes(codeGenerator)
        if (errorLabel != "") {
            codeGenerator.addHelper(errorLabel!!)
        }
        if (mulDiv != null) {
            if (mulDiv.MULT() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MultInstr(reg1, reg2))
                codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(reg2, reg1, "ASR #31"))
                if (errorLabel != "") {
                    codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(errorLabel!!, Condition.NE))
                }
            } else if (mulDiv.DIV() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("__aeabi_idiv"))
            } else if (mulDiv.MOD() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("__aeabi_idivmod"))
            }
        }
        else if (addSub != null) {
            if (addSub.MINUS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, SubInstr(reg1, reg2))
            } else if (addSub.PLUS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(reg1, reg1, reg2))
                if (errorLabel != "") {
                    codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(errorLabel, Condition.VS))
                }
            }
        }
        else if (eqOp != null) {
            if (eqOp.GREAT() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1,"#1", Condition.GT))
                //IDK if maybe we need to add case for notgreater?
            } else if (eqOp.GREAT_EQ() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1,"#1", Condition.GE))
            } else if (eqOp.LESS_EQ() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.LE))
            } else if (eqOp.LESS() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.LT))
            } else if (eqOp.EQ() != null) {
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.EQ))
            }
        } else {
            //can only be not equal now
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg1, "#1", Condition.NE))
        }
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
