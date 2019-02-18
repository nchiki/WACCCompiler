package src.main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class IntLitNode(val int_val : Long, override val ctx: BasicParser.IntLitContext) : ExprNode {

    override fun getBaseType(): LitTypes {
        return LitTypes.IntWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for the Literals
    }
}