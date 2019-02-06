package Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import SymbolTable

class IfCondNode(expr: Node?, ifTrueStat: Node?, elseStat: Node?) : Node {

    val expr = expr //condition (should evaluate to boolean val

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
