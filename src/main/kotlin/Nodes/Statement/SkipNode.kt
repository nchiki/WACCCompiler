package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable

class SkipNode(override val ctx: BasicParser.SkipContext): Node{

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }
}
