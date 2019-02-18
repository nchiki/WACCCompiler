package Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PairLitNode(override val ctx : BasicParser.PairLitContext): ExprNode {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for PairLitNode
    }

    override fun getType(): LitTypes {
        return LitTypes.PairWacc
    }

}