package main.kotlin.Errors

import Errors.SemanticError
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.ParserRuleContext

class IncompatibleTypes(ctx : ParserRuleContext, private val expected : String, val expr : Node, private val symbolTable: SymbolTable) : SemanticError(ctx) {

    override fun printError(): String {
        var newExpr = expr
//        println(expected)
//        println(1)
        if (expr is IdentNode) {
//            println(2)
//            println(symbolTable.lookupSymbol(expr.id))
            newExpr = symbolTable.lookupSymbol(expr.id)!!
        }
        return super.printError() + "Incompatible type (expected: ${expected.toUpperCase().replace("WACC", "")}, actual: ${newExpr.getType().toString().toUpperCase().replace("WACC", "")})"
    }

}
