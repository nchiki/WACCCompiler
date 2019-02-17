package Nodes.PairType

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


open class PairElemTypeNode(val type: TypeNode?, val pair : String?, override val ctx: BasicParser.PairElemTypeContext) : TypeNode {


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }

    override fun syntaxCheck() {
        //not needed
    }

    override fun getType(): LitTypes {
        if (pair == "") {
            return type!!.getType()
        } else {
           return LitTypes.PairWacc
        }
    }

}
