package src.main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.IdentNode
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
            expr.semanticCheck(errors, table)
        }
    }
}