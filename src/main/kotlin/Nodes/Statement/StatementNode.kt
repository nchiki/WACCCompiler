package Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class StatementNode(val stat : Node, override val ctx: BasicParser.StatementContext): Node {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        var childTable = SymbolTable(table)
        stat.semanticCheck(errors, childTable)
    }
}