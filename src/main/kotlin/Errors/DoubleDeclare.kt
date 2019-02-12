package Errors

import org.antlr.v4.runtime.ParserRuleContext

class DoubleDeclare(ctx : ParserRuleContext, val varID : String) : SemanticError(ctx) {

    override fun printError(): String {
        return super.printError() + "Variable $varID is already defined in this scope"
    }

}