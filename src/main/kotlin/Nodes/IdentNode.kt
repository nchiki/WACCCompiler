package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class IdentNode(val id : String, override val ctx: BasicParser.IdContext?) : ExprNode {

    override fun getType() : LitTypes {
        return LitTypes.IdentWacc
    }

    fun getValueType(table: SymbolTable) : Node? {
        return table.lookupSymbol(id)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(table.lookupSymbol(id) == null){
            errors.addError(UndefinedVariable(ctx as ParserRuleContext, id))
        }
    }
}