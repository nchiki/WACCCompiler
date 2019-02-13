package Errors

import org.antlr.v4.runtime.ParserRuleContext

class InvalidOperandTypes(ctx : ParserRuleContext, val expected : String, val actual : String) : SemanticError(ctx) {

    override fun printError(): String {
        return super.printError() + "Invalid operand types (expected: $expected, actual: $actual"
    }

}
