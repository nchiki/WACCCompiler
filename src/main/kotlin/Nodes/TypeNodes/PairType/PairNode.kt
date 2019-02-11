package Nodes.PairType

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class PairNode(fstNode: PairElemTypeNode, sndNode: PairElemTypeNode, override val ctx: BasicParser.Pair_typeContext) : TypeNode, Node {

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