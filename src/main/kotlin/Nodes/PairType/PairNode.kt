package Nodes.PairType

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable

class PairNode(fstNode: PairElemTypeNode, sndNode: PairElemTypeNode) : Node {
    override fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}