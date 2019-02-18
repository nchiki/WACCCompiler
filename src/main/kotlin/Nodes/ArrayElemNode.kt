package src.main.kotlin.Nodes

import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class ArrayElemNode(val identifier : String, var exprs : List<ExprNode>, override val ctx: BasicParser.ArrayElemContext) : ExprNode {

    fun getId() : String{
        val idBase = IdentNode(identifier, null)
        return idBase.id

    }
    override fun getType() : LitTypes {
        return LitTypes.IdentWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val arrayType = table.lookupSymbol(identifier)?.getType()

       for (expr in exprs) {
            /*var tempExpr = expr as Node
            if(expr is IdentNode){
                val lookup = table.lookupSymbol(expr.id)
                if(lookup != null){
                     tempExpr = lookup

                }else {
                    errors.addError(UndefinedVariable(ctx, identifier))
                    continue
                }
            }

            if (arrayType == null) {
                continue
            }

            if (tempExpr.getType() != arrayType) {
                errors.addError(IncompatibleTypes(ctx, arrayType.toString(), tempExpr, table))
            }*/

            expr.semanticCheck(errors, table)
        }
    }
}