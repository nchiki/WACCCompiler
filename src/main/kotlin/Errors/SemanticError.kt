package main.kotlin.Errors

import org.antlr.v4.runtime.ParserRuleContext

open class SemanticError(val ctx : ParserRuleContext) : ErrorNode {

    override fun printError() : String {
        return "Semantic error detected at ${ctx.start.line}:${ctx.start.charPositionInLine} in \"${ctx.children.joinToString(" ") { it.text }}\": "
    }

}