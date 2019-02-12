package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

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
//        println(0)
        val line = operator.start.line
        val pos = operator.start.charPositionInLine
        var leftType = left.getType()
        if (left is IdentNode) {
            val leftValue = table.lookupSymbol(left.id)
            if (leftValue == null) {
                errors.addError(UndefinedVariable(line, pos, left.id))
                return
            }
            leftType = leftValue.getType()
        }
        var rightType = right.getType()
        if (right is IdentNode) {
            val rightValue = table.lookupSymbol(right.id)
            if (rightValue == null) {
                errors.addError(UndefinedVariable(line, pos, right.id))
                return
            }
            rightType = rightValue.getType()
        }
        if (getType() == LitTypes.IntWacc && leftType != LitTypes.IntWacc) {
            errors.addError(IncompatibleTypes(line, pos, "INT", left, table))
        } else if (getType() == LitTypes.IntWacc && rightType != LitTypes.IntWacc) {
            errors.addError(IncompatibleTypes(line, pos, "INT", right, table))
        } else if (leftType != rightType) {
            errors.addError(IncompatibleTypes(line, pos, leftType.toString(), right, table))
        }
        if ((operator.MULT() != null
                        || operator.DIV() != null
                        || operator.MOD() != null
                        || operator.MINUS() != null
                        || operator.PLUS() != null
                        ||operator.LESS() != null
                        || operator.LESS_EQ() != null
                        || operator.GREAT() != null
                        || operator.GREAT_EQ() != null)
                && (left.getType() != LitTypes.IntWacc || right.getType() != LitTypes.IntWacc)) {
            errors.addError(InvalidOperandTypes(operator.start.line, operator.start.charPositionInLine))
        } else if ((operator.AND() != null
                        || operator.OR() != null)
                && (left.getType() != LitTypes.BoolWacc || right.getType() != LitTypes.BoolWacc)) {
            errors.addError(InvalidOperandTypes(operator.start.line, operator.start.charPositionInLine))


        }



    }

}