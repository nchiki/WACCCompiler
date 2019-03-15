package main.kotlin.Errors

import org.antlr.v4.runtime.ParserRuleContext

class IncorrectNumParams(ctx: ParserRuleContext, private val expectedNum: Int, private val actualNum: Int) : SemanticError(ctx) {

    override fun printError(): String {
        return "Incorrect number of parameters (expected: $expectedNum, actual: $actualNum)"
    }

}