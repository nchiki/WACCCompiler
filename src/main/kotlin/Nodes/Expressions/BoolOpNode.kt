package main.kotlin.Nodes.Expressions

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.AndInstr
import main.kotlin.Instructions.OrInstr
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BoolOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BoolOpContext,
                 override val ctx: ParserRuleContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size = 1
    override val weight: Int
        get() = left.weight + right.weight + 1

    override fun generateCode(codeGenerator: CodeGenerator) {

        var leftReg: Register?
        var rightReg: Register?

        // evaluates expression that needs more registers first
        if (left.weight > right.weight) {
            left.generateCode(codeGenerator)
            leftReg = codeGenerator.getLastUsedReg()
            right.generateCode(codeGenerator)
            rightReg = codeGenerator.getLastUsedReg()
        } else {
            right.generateCode(codeGenerator)
            rightReg = codeGenerator.getLastUsedReg()
            left.generateCode(codeGenerator)
            leftReg = codeGenerator.getLastUsedReg()
        }

        /* Compare the two expression results */

        if (operator.AND() != null) {
            codeGenerator.addInstruction(codeGenerator.curLabel, AndInstr(leftReg, rightReg))
        } else if (operator.OR() != null) {
            codeGenerator.addInstruction(codeGenerator.curLabel, OrInstr(leftReg, rightReg))
        }

        codeGenerator.regsInUse.remove(rightReg)
        codeGenerator.regsNotInUse.add(rightReg)
        //adds leftReg as last reg used in order to get the result of the operation
        codeGenerator.regsInUse.remove(leftReg)
        codeGenerator.regsInUse.add(leftReg)
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
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


        if (realLeft is ArrayTypeNode || !realLeft.getBaseType().equals(LitTypes.BoolWacc)) {
            errors.addError(IncompatibleTypes(ctx, LitTypes.BoolWacc.toString(), realLeft, table))
        }

        if (realRight is ArrayTypeNode || !realRight.getBaseType().equals(LitTypes.BoolWacc)) {
            errors.addError(IncompatibleTypes(ctx, LitTypes.BoolWacc.toString(), realRight, table))
        }

        left.semanticCheck(errors, table)
        right.semanticCheck(errors, table)

    }


    //returns type of Node
    override fun getBaseType(): LitTypes {
        return LitTypes.BoolWacc
    }

}
