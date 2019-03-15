package main.kotlin.Errors

import org.antlr.v4.runtime.ParserRuleContext

class UndefinedVariable(ctx: ParserRuleContext, val varID: String) : SemanticError(ctx) {

    override fun printError(): String {
        return super.printError() + "Variable $varID is not defined in this scope"
    }
}