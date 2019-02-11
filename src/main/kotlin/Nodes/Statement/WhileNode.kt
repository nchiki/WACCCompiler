package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.ParserRuleContext

class WhileNode(val expr: Node, val stat: Node, val ctx: BasicParser.WhileContext): Node {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val childTable = SymbolTable(table)

        expr.semanticCheck(errors, childTable)
        stat.semanticCheck(errors, childTable)

        table.boolExprCheck(expr, errors)
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}