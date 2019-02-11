package main.kotlin.Errors

import Errors.SemanticError
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable

class IncompatibleTypes(line: Int, pos: Int, private val expected : String, val expr : Node, private val symbolTable: SymbolTable) : SemanticError(line, pos) {

    override fun printError(): String {
        var newExpr = expr
//        println(expected)
//        println(1)
        if (expr is IdentNode) {
//            println(2)
//            println(symbolTable.lookupSymbol(expr.id))
            newExpr = symbolTable.lookupSymbol(expr.id)!!
        }
        return super.printError() + "Incompatible type (expected: ${expected.toUpperCase()}, actual: ${newExpr.getType().toString().toUpperCase().replace("WACC", "")})"
    }

}
