package main.kotlin.Nodes.Expressions

import Errors.InvalidOperandTypes
import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.ArrayLitNode
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BinaryOperContext, override val ctx: ParserRuleContext) : ExprNode {

    //differs between a Boolean expression or calculation of two operands
    override fun getBaseType(): LitTypes {
        if(operator.MULT() != null
                || operator.DIV() != null
                || operator.MOD() != null
                || operator.MINUS() != null
                || operator.PLUS() != null) {
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

        if(realLeft is ArrayTypeNode){
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realLeft, table))
            return
        }

        if(realRight is ArrayTypeNode){
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realRight, table))
            return
        }

        if(getBaseType().equals(LitTypes.BoolWacc)){
            if(realLeft.getBaseType() != LitTypes.BoolWacc){
                errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realLeft, table))
            }
            if(realRight.getBaseType() != LitTypes.BoolWacc){
                errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realRight, table))
            }
            return
        }

        /* Can only be Integer operator now  */
        if(realLeft.getBaseType() != LitTypes.IntWacc){
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realLeft, table))
        }
        if(realRight.getBaseType() != LitTypes.IntWacc){
            errors.addError(IncompatibleTypes(ctx, getBaseType().toString(), realRight, table))
        }
    }

}
