package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class UnaryOpNode(val operand: ExprNode, val operator: BasicParser.UnaryOperContext, type: Any, override val ctx: BasicParser.UnOpContext) : ExprNode {

    override fun getType(): LitTypes {
       return operand.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (operator.NOT() != null && operand.getType() != LitTypes.BoolWacc
            || operator.MINUS() != null && operand.getType() != LitTypes.IntWacc
            || operator.LEN() != null && operand.getType() != LitTypes.ArrayLit
            || operator.ORD() != null && operand.getType() != LitTypes.CharWacc
            || operator.CHR() != null && operand.getType() != LitTypes.IntWacc)
        {
            errors.addError(InvalidOperandTypes(ctx))
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}