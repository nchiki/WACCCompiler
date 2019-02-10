package main.kotlin.Errors

import Errors.ErrorNode

class NonExistingVar(val line: Int, val pos: Int) : ErrorNode {

    override fun printError(): String {
        return "The variable found in line: $line at position: $pos  has not been declared yet."
    }
}