package Errors

import org.antlr.v4.runtime.ParserRuleContext

class InvalidOperandTypes(ctx : ParserRuleContext) : SemanticError(ctx) {

    override fun printError(): String {
        return "Invalid operand types at ${ctx.start.line} ${ctx.start.charPositionInLine}"
    }

}
