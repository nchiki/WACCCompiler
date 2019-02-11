package main.kotlin.Nodes.Statement

import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class StatListNode(val stats : List<Node>) : Node {
    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        for (stat in stats) {
            stat.semanticCheck(errors, table)
        }
    }

    override fun getType(): LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}