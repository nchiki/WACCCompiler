package Errors

import org.antlr.v4.runtime.ParserRuleContext

open class SemanticError(val ctx : ParserRuleContext) : ErrorNode {

    override fun printError(): String {
        return "Semantic error detected on line ${ctx.start.line}, at position ${ctx.start.charPositionInLine} (${ctx.children.joinToString(" ") { it.text }}): "
    }

}