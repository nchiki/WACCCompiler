package main.kotlin.Errors

import Errors.ErrorNode

class IncorrectNumParams(val line: Int, val pos: Int) : ErrorNode {

    override fun printError(): String {
        return "Incorrect number of parameters found in line: $line at position: $pos "
    }
}