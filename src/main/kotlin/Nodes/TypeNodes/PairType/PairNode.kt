package Nodes.PairType

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class PairNode(val fstNode: PairElemTypeNode, val sndNode: PairElemTypeNode,
               override val ctx: BasicParser.Pair_typeContext) : TypeNode, Node {

    override fun getType() : LitTypes {
        return LitTypes.PairWacc
    }

    override fun syntaxCheck() {
        //not needed
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
       //not needed
    }

    fun returnElemNode(i: Int): LitTypes {
        return when(i) {
            1 -> sndNode.getType()
            else -> fstNode.getType()
        }
    }

}