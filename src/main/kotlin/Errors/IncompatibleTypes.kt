package main.kotlin.Errors

import main.kotlin.Nodes.IdentNode
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class IncompatibleTypes(ctx: ParserRuleContext, private val expected: String, val expr: ExprNode, private val symbolTable: SymbolTable) : SemanticError(ctx) {

    override fun printError(): String {
        var newExpr = expr

        if (expr is IdentNode) {
            newExpr = symbolTable.lookupSymbol(expr.id)!!
        }
        return super.printError() + "Incompatible type (expected: ${expected.toUpperCase().replace("WACC", "")}, actual: ${newExpr.getBaseType().toString().toUpperCase().replace("WACC", "")})"
    }

}
