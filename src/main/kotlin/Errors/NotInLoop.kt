package main.kotlin.Errors

import org.antlr.v4.runtime.ParserRuleContext

class NotInLoop(val ctx : ParserRuleContext) :ErrorNode{
    override fun printError(): String {
        return "Statement in line: ${ctx.start.line}, ${ctx.start.charPositionInLine} is not in a loop"
    }
}