package main.kotlin.Errors

import Errors.ErrorNode

class IncorrectReturnTypes(val line :Int, val pos :Int) : ErrorNode {
    override fun printError(): String {
        return "Incorrect return type (line $line, pos $pos). Expected exit or return."
    }
}