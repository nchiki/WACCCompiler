package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class AssignNode : Node {
    override fun getType() : KClass<AssignNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}