package Nodes.PairType

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import kotlin.Nodes.TypeNodes.TypeNode

class PairNode(fstNode: PairElemTypeNode, sndNode: PairElemTypeNode) : TypeNode {

    override fun getType() : LitTypes {
        return LitTypes.PairWacc
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}