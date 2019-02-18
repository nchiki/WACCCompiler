package Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.DoubleDeclare
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ParamNode(
        val id: String,
        val type: ExprNode, override val ctx: BasicParser.ParamContext) : ExprNode {

    override fun getBaseType() : LitTypes{
        var v = type
        while (v is ArrayTypeNode) {
            v = v.type
        }
        val toType = v
        return toType.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // looks up the id in the symbol table
        val value = table.lookupSymbol(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (value != null && (value !is FunctionNode)) {
            // if there is already a variable with that name -> error
            errors.addError(DoubleDeclare(ctx, id, value.ctx!!.start.line))
        } else {
            table.add(this, id)
        }

    }
}