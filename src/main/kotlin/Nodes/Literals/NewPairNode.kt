package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class NewPairNode(override val ctx:BasicParser.AssignR_PairContext, val exprNode1: ExprNode, val exprNode2: ExprNode) :Node {

    override fun getType(): LitTypes {
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

    override fun syntaxCheck() {
        //not needed for NewPairNode
    }
}