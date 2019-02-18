package Nodes.PairType

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


open class PairElemTypeNode(val type: ExprNode?, val pair : String?, override val ctx: BasicParser.PairElemTypeContext) : ExprNode {


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }

    override fun getBaseType(): LitTypes {
        if (pair == "") {
            return type?.getBaseType()!!
        }

        return LitTypes.PairWacc
    }

}
