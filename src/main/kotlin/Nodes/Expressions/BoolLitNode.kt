package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class BoolLitNode(val bool_val : String, override val ctx: BasicParser.BoolLitContext) : ExprNode {

    override fun getBaseType(): LitTypes {
        return LitTypes.BoolWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //base types do not need to be checked
    }
    
}
