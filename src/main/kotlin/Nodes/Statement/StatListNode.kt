package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class StatListNode(val listStatNodes: MutableList<Node>, override val ctx: BasicParser.StatListContext) : Node{

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
       for (stat in listStatNodes) {
           stat.semanticCheck(errors, table)
       }
    }
}
