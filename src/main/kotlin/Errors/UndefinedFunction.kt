package main.kotlin.Errors

import org.antlr.v4.runtime.ParserRuleContext

class UndefinedFunction(ctx : ParserRuleContext, val function : String) : SemanticError(ctx) {

    override fun printError(): String {
        return super.printError() + "Function $function is not defined in this scope"
    }
}