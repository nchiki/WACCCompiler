package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.UnaryOpNode
import main.kotlin.SymbolTable
import kotlin.reflect.KClassifier

class WhileNode(val expr: Node, val stat: Node): Node {

    var symbolTable: SymbolTable? = null

    override fun getType(): KClassifier {
        return WhileNode::class
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        symbolTable = SymbolTable(table)

        table.boolExprCheck(expr, errors, table)

        expr.semanticCheck(errors, symbolTable!!)
        stat.semanticCheck(errors, symbolTable!!)
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}