package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class UnaryOpNode(val operand: ExprNode, val operator: BasicParser.UnaryOperContext, type: Any, override val ctx: BasicParser.UnOpContext) : ExprNode {

    override fun getType(): LitTypes {
       return operand.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        operand.semanticCheck(errors, table)
        var expected = ""
        if (operator.NOT() != null && operand.getType() != LitTypes.BoolWacc) {
            expected = "BOOL"
        } else if (operator.MINUS() != null && operand.getType() != LitTypes.IntWacc
                    || operator.CHR() != null && operand.getType() != LitTypes.IntWacc) {
            expected = "INT"
        } else if (operator.LEN() != null && operand.getType() != LitTypes.ArrayLit) {
            expected = "T[]"
        } else if (operator.ORD() != null && operand.getType() != LitTypes.CharWacc) {
            expected = "CHAR"
        }

        if (expected != "") {
            errors.addError(IncompatibleTypes(ctx, expected, operand, table))
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}