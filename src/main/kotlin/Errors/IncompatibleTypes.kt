package main.kotlin.Errors

import Errors.SemanticError
import main.kotlin.Nodes.ArrayLitNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.LHS_Node
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.ParserRuleContext

class IncompatibleTypes(ctx : ParserRuleContext, private val expected : String, val expr : Node, private val symbolTable: SymbolTable) : SemanticError(ctx) {

    override fun printError(): String {
        var newExpr = expr
//        println(expected)
//        println(expr.getType().name)
//        println(1)
        if (expr is IdentNode) {
//            println(2)
//            println(symbolTable.lookupSymbol(expr.id))
            newExpr = symbolTable.lookupSymbol(expr.id)!!
        } else if (expr is LHS_Node) {
            newExpr = symbolTable.lookupSymbol(expr.id)!!
        }
        var actualTypeString = newExpr.getType().toString().toUpperCase().replace("WACC", "")
        if (newExpr is NewPairNode) {
            actualTypeString = newExpr.getPrintableType()
        } else if (newExpr is ArrayLitNode) {
            actualTypeString = newExpr.getPrintableType()
        }
        return super.printError() + "Incompatible type (expected: ${expected.toUpperCase().replace("WACC", "")}, actual: $actualTypeString)"
    }

}
