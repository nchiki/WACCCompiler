package main.kotlin.Errors

import org.antlr.v4.runtime.ParserRuleContext

class DoubleDeclare(ctx : ParserRuleContext, private val varID : String, private val lineOfFirstDeclaration : Int) : SemanticError(ctx) {

    override fun printError() : String {
        return super.printError() + "Variable \"$varID\" is already defined in this scope on line $lineOfFirstDeclaration"
    }

}
