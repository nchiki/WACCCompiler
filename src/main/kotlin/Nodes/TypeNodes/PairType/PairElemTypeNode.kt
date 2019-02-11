package Nodes.PairType

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


open class PairElemTypeNode(val type: TypeNode?, val pair : String?, override val ctx: BasicParser.PairElemTypeContext) : TypeNode {


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(): LitTypes {
        if(pair != "") {
            return type!!.getType()
        } else {
            return LitTypes.PairWacc
        }
    }

}
