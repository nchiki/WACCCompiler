package src.main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class ArrayElemNode(val baseType : String, val expr : ExprNode, override val ctx: BasicParser.ArrayElemContext) : ExprNode {

    fun getId() : String{
        val idBase = IdentNode(baseType, null)
        return idBase.id

    }
    override fun getType() : LitTypes {
        return expr.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        var tempExpr = expr

        if(expr is IdentNode){
            tempExpr = table.lookupSymbol(expr.id) as ExprNode
        }

        if (tempExpr.getType() != BaseNode(baseType, null).getType()) {
            errors.addError(IncompatibleTypes(ctx, baseType.toString(), tempExpr, table))
        }

        expr.semanticCheck(errors, table)
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}