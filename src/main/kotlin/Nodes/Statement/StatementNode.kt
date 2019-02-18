package Nodes

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class StatementNode(val stat : Node, override val ctx: BasicParser.StatementContext): Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        var childTable = SymbolTable(table)
        stat.semanticCheck(errors, childTable)
    }
}
