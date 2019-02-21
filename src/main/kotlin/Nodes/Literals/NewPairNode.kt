package main.kotlin.Nodes.Literals

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class NewPairNode(override val ctx:BasicParser.AssignR_PairContext, val exprNode1: ExprNode, val exprNode2: ExprNode) : ExprNode {

    override val size: Int
        get() = 8

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBaseType(): LitTypes {
        return LitTypes.PairWacc
    }

    //returns one PairNode
    fun returnElemNode(i : Int) : ExprNode {
        if (i == 1) {
            return exprNode2
        } else {
            return exprNode1
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for NewPairNode
    }
}
