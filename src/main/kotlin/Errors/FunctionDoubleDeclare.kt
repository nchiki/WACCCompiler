package Errors

import org.antlr.v4.runtime.ParserRuleContext

class FunctionDoubleDeclare(ctx : ParserRuleContext?, private val funcID : String) : SemanticError(ctx!!) {

    override fun printError(): String {
        return super.printError() + "Function $funcID is already defined in this scope"
    }

}