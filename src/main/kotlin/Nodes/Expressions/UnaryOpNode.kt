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
            "chr" -> return LitTypes.CharWacc
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
        println(operand.getType())
        if (operator.text == "!" && op!!.getType() != LitTypes.BoolWacc
            || operator.text == "minus" && op!!.getType() != LitTypes.IntWacc
            || operator.text == "len" && op!!.getType() != LitTypes.ArrayLit
            || operator.text == "ord" && op!!.getType() != LitTypes.CharWacc
            || operator.text == "chr" && op!!.getType() != LitTypes.IntWacc)
        {

            errors.addError(InvalidOperandTypes(ctx))
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}