package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

class FunctionNode (id : String, type : BasicParser.TypeContext) : Node {
    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}