package main.kotlin.Errors

import Errors.SemanticError
import org.antlr.v4.runtime.ParserRuleContext

class IncorrectReturnTypes(ctx : ParserRuleContext) : SemanticError(ctx) {

    override fun printError(): String {
        return "Incorrect return type. Expected exit or return."
    }

}