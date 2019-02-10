package main.kotlin.Errors

import Errors.ErrorNode

class UndeclaredVariableError(val id: String, val line: Int, val pos: Int): ErrorNode {

    override fun printError(): String {
        return "Variable in line $line at position $pos needs to be declared before use."
    }

}