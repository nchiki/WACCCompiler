package Nodes.PairType

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PairNode(val fstNode: PairElemTypeNode, val sndNode: PairElemTypeNode,
               override val ctx: BasicParser.Pair_typeContext) : ExprNode, Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBaseType() : LitTypes {
        return LitTypes.PairWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }

    fun returnElemNode(i: Int): LitTypes {
        return when(i) {
            1 -> sndNode.getBaseType()
            else -> fstNode.getBaseType()
        }
    }

}
