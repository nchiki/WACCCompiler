package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BinaryOperContext, ctx: BasicParser.BinOperContext) : ExprNode {

    override fun getType(): LitTypes {
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

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (getType() == LitTypes.IntWacc && left.getType() != LitTypes.IntWacc) {
            errors.addError(IncompatibleTypes(operator.start.line, operator.start.charPositionInLine, "INT", left, table))
        } else if (getType() == LitTypes.IntWacc && right.getType() != LitTypes.IntWacc) {
            errors.addError(IncompatibleTypes(operator.start.line, operator.start.charPositionInLine, "INT", right, table))
        } else if (left.getType() != right.getType()) {
            errors.addError(IncompatibleTypes(operator.start.line, operator.start.charPositionInLine, left.getType().toString(), right, table))
        }
        if ((operator.MULT() != null
                        || operator.DIV() != null
                        || operator.MOD() != null
                        || operator.MINUS() != null
                        || operator.PLUS() != null)
                && left.getType() != LitTypes.IntWacc) {
            errors.addError(InvalidOperandTypes(operator.start.line, operator.start.charPositionInLine))
        }

    }

}