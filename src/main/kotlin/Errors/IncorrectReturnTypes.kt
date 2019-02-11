package main.kotlin.Errors

import Errors.ErrorNode

class IncorrectReturnTypes(val line: Int, val charPositionInLine: Int) : ErrorNode {

    override fun printError(): String {
        return "Incorrect return type in line $line , pos $charPositionInLine"
    }

}
