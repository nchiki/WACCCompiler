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

class UnaryOpNode(val operand: ExprNode, val operator: BasicParser.UnaryOperContext, type : Any,
                  override val ctx: BasicParser.UnOpContext) : ExprNode {

    //return the type of the operand
    override fun getBaseType(): LitTypes {
        when (operator.text) {
            "!" -> return LitTypes.BoolWacc
            "chr" -> return LitTypes.CharWacc
            else -> return LitTypes.IntWacc
        }
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        var op = operand

        //get type of operand from Symboltable
        if(operand is IdentNode) {
            val opValue = table.lookupSymbol(operand.id)
            if (opValue == null) {
                errors.addError(UndefinedVariable(ctx, operand.id))
                return
            }
            op = opValue
        }

        //check whether operand and operator are compatible
        if (operator.text == "!" && op.getBaseType() != LitTypes.BoolWacc
            || operator.text == "minus" && op.getBaseType() != LitTypes.IntWacc
            || operator.text == "len" && op !is ArrayTypeNode
            || operator.text == "ord" && op.getBaseType() != LitTypes.CharWacc
            || operator.text == "chr" && op.getBaseType() != LitTypes.IntWacc)
        {

            errors.addError(InvalidOperandTypes(ctx))
        }
    }

}