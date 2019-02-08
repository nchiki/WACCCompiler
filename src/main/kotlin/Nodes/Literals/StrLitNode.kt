package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClassifier

class StrLitNode : Node {
    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(): KClassifier {
        return StrLitNode::class
    }
}