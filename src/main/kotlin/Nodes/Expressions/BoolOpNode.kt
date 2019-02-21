package main.kotlin.Nodes.Expressions

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.CmpInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BoolOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BoolOpContext,
                 override val ctx: ParserRuleContext) : ExprNode {

    override val size = 1
    override val weight: Int
        get() = left.weight + right.weight + 1

    override fun generateCode(codeGenerator: CodeGenerator) {

        var leftReg: Register? = null
        var rightReg: Register? = null

        if(left.weight > right.weight){
            left.generateCode(codeGenerator)
            leftReg = codeGenerator.getLastUsedReg()
            right.generateCode(codeGenerator)
            rightReg = codeGenerator.getLastUsedReg()
        }else{
            right.generateCode(codeGenerator)
            rightReg = codeGenerator.getLastUsedReg()
            left.generateCode(codeGenerator)
            leftReg = codeGenerator.getLastUsedReg()
        }

        // gets the correct instruction depending on the operator and adds it to codeGenerator


        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(leftReg, "#0", Condition.AL))

        codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(leftReg, rightReg))

        if(operator.AND() != null){
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(leftReg, "#1", Condition.EQ))
        }else if(operator.OR() != null){
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(leftReg, "#1", Condition.NE))
        }

        codeGenerator.regsNotInUse.removeAt(0)
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

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

        /* Get left value from symbol table */
        if (right is IdentNode) {
            val rightValue = table.lookupSymbol(right.id)

            if (rightValue == null) {
                errors.addError(UndefinedVariable(ctx, right.id))
                return
            }

            realRight = rightValue
        }


        if(realLeft is ArrayTypeNode || !realLeft.getBaseType().equals(LitTypes.BoolWacc)){
            errors.addError(IncompatibleTypes(ctx, LitTypes.BoolWacc.toString(), realLeft, table))
        }

        if(realRight is ArrayTypeNode || !realRight.getBaseType().equals(LitTypes.BoolWacc)){
            errors.addError(IncompatibleTypes(ctx, LitTypes.BoolWacc.toString(), realRight, table))
        }

    }


    //returns type of Node
    override fun getBaseType(): LitTypes {
        return LitTypes.BoolWacc
    }

}
