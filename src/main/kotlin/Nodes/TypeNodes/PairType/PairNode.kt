package Nodes.PairType

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PairNode(val fstNode: PairElemTypeNode, val sndNode: PairElemTypeNode,
               override val ctx: BasicParser.Pair_typeContext) : ExprNode, Node {

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {}

    override fun getBaseType(): LitTypes {
        return LitTypes.PairWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

    fun returnElemNode(elem: Int): LitTypes {
        return when (elem) {
            1 -> sndNode.getBaseType()
            else -> fstNode.getBaseType()
        }
    }

}
