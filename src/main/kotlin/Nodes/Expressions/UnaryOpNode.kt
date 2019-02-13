package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class UnaryOpNode(val operand: ExprNode, val operator: BasicParser.UnaryOperContext, type: Any, override val ctx: BasicParser.UnOpContext) : ExprNode {

    override fun getType(): LitTypes {
        when (operator.text) {
            "!" -> return LitTypes.BoolWacc
            "ord" -> return LitTypes.CharWacc
            "len" -> return LitTypes.ArrayLit
            else -> return LitTypes.IntWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        var op = operand as Node?
        if(operand is IdentNode) {
            op = table.lookupSymbol(operand.id)
            if (op == null) {
                errors.addError(UndefinedVariable(ctx, operand.id))
            }
        }
        if (operator.NOT() != null && op?.getType() != LitTypes.BoolWacc
            || operator.MINUS() != null && op?.getType() != LitTypes.IntWacc
            || operator.LEN() != null && op?.getType() != LitTypes.ArrayLit
            || operator.ORD() != null && op?.getType() != LitTypes.CharWacc
            || operator.CHR() != null && op?.getType() != LitTypes.IntWacc)
        {

            errors.addError(InvalidOperandTypes(ctx))
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}