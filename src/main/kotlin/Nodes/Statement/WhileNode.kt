package kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClassifier

class WhileNode(val expr: Node, val stat: Node): Node {

    override fun getType(): KClassifier {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}