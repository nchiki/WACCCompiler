package main.kotlin.Nodes.Expression

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClassifier

class ParenNode(val expr: Node): Node {

    override fun getType(): KClassifier {
        return ParenNode::class
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}