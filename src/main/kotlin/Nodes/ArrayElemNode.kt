package src.main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UnknownIdentifier
import main.kotlin.Nodes.BaseNode
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
        return exprs[0].getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        println("in semantic check of array")
        val arrayType = table.lookupSymbol(identifier)?.getType()
        println("arraytype is ${arrayType.toString()}")
        for (expr in exprs) {
            var tempExpr = expr
            if(expr is IdentNode){
                val lookup = table.lookupSymbol(expr.id)
                if(lookup != null){
                    tempExpr = lookup as ExprNode
                }else {
                    errors.addError(UnknownIdentifier(ctx.start.line, ctx.start.charPositionInLine))
                    continue
                }
            }

            if(arrayType == null){
                continue
            }

            if (tempExpr.getType() != arrayType) {
                errors.addError(IncompatibleTypes(ctx, arrayType.toString(), tempExpr, table))
            }

            expr.semanticCheck(errors, table)
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}