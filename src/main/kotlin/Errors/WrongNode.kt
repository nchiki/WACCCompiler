package main.kotlin.Errors


import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.ParserRuleContext

class WrongNode(ctx : ParserRuleContext, private val expected : String, val node : Node, private val symbolTable: SymbolTable) : SemanticError(ctx) {

    override fun printError(): String {

        return super.printError() + "Incompatible node(expected: ${expected.toUpperCase().replace("WACC", "")}, actual: ${node})"
    }

}
