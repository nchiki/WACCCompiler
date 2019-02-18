package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class ReturnStatNode (val expr : ExprNode, override val ctx: BasicParser.ReturnContext, var type_return: LitTypes?) : Node{

    fun getType(): LitTypes {
        if(type_return != null) {
            return type_return!!
        } else {
            return LitTypes.Null
        }
    }

    fun setFunctionReturn(type : LitTypes) {
        this.type_return = type
    }

    //need to add actual lines and positions
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (type_return != (expr.getBaseType())) {

            if(expr is IdentNode) {
                val idexpr = expr as IdentNode
                val value = table.lookupSymbol(expr.id)

                if (value?.getBaseType() != type_return) {

                    errors.addError(IncompatibleTypes(ctx, type_return.toString(), value!!, table))
                }
            } /*else if (expr is UnaryOpNode) {
                val value = table.lookupSymbol(expr.operand.toString())
                if (value?.getType() != type_return) {
                    println("adding error1")
                    errors.addError(IncompatibleTypes(ctx, type_return.toString(), value!!, table))
                }
            } else if (expr is BinaryOpNode) {
                val value = table.lookupSymbol()*/
            else {

                errors.addError(IncompatibleTypes(ctx, type_return.toString(), expr, table))
            }
        }
    }
}