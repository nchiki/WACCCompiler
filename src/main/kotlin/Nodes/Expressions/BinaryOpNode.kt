package main.kotlin.Nodes

import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BinaryOperContext, override val ctx: BasicParser.BinOperContext) : ExprNode {

    override fun getType(): LitTypes {
        if(operator.MULT() != null
                || operator.DIV() != null
                || operator.MOD() != null
                || operator.MINUS() != null
                || operator.PLUS() != null
                || operator.LESS() != null
                || operator.LESS_EQ() != null
                || operator.GREAT() != null
                || operator.GREAT_EQ() != null) {
            return LitTypes.IntWacc
        } else {
            return LitTypes.BoolWacc
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        left.semanticCheck(errors, table)
        right.semanticCheck(errors, table)
        var leftType = left.getType()
        if (left is IdentNode) {
            if (left.getValueType(table) is ArrayLitNode) {
                leftType = LitTypes.ArrayLit
            } else {
                val leftValue = table.lookupSymbol(left.id)
                if (leftValue == null) {
                    errors.addError(UndefinedVariable(ctx, left.id))
                    return
                }
                leftType = leftValue.getType()
            }
        }
        var rightType = right.getType()
        if (right is IdentNode) {
            if (right.getValueType(table) is ArrayLitNode) {
                rightType = LitTypes.ArrayLit
            } else {
                val rightValue = table.lookupSymbol(right.id)
                if (rightValue == null) {
                    errors.addError(UndefinedVariable(ctx, right.id))
                    return
                }
                rightType = rightValue.getType()
            }
        }
//        println(leftType)
//        println(rightType)
//        println(getType())
//        println((left as IdentNode).getValueType(table))
//        println(right)

        if (getType() == LitTypes.IntWacc) {
            if (leftType != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes(ctx.expr(0), "INT", left, table))
            }
            if (rightType != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes(ctx.expr(1), "INT", right, table))
            }
        } else if (getType() == LitTypes.BoolWacc) {
            if (leftType != LitTypes.BoolWacc) {
                errors.addError(IncompatibleTypes(ctx.expr(0), "BOOL", left, table))
            }
            if (rightType != LitTypes.BoolWacc) {
                errors.addError(IncompatibleTypes(ctx.expr(1), "BOOL", right, table))
            }
        }
    }

}