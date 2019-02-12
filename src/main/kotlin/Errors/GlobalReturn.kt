package Errors

import org.antlr.v4.runtime.ParserRuleContext

class GlobalReturn(ctx : ParserRuleContext) : SemanticError(ctx) {

    override fun printError(): String {
        return super.printError() + "Cannot return from the global scope"
    }

}