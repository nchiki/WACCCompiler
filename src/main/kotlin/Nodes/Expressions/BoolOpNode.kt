package kotlin.Nodes.Expressions

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BoolOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BoolOpContext,
                 override val ctx: ParserRuleContext) : ExprNode {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(left.getType() != LitTypes.BoolWacc || right.getType() != LitTypes.BoolWacc) {

            //checks that both identifiers are bools
            if (left is IdentNode || right is IdentNode) {
                if (left is IdentNode) {
                    val value = table.lookupSymbol(left.id)
                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                    && value.getType() != LitTypes.BoolWacc)) {
                        errors.addError(InvalidOperandTypes(ctx))
                    }
                }

                if (right is IdentNode) {
                    val value = table.lookupSymbol(right.id)
                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                    && value.getType() != LitTypes.BoolWacc)) {
                        errors.addError(InvalidOperandTypes(ctx))
                    }
                }
            }

            //if operands could not be identified, throw Invalid Operand Error
            else {
                errors.addError(InvalidOperandTypes(ctx))
            }
        }
    }


    //returns type of Node
    override fun getType(): LitTypes {
        return LitTypes.BoolWacc
    }

}