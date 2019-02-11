package main.kotlin.Errors

import Errors.SemanticError

class IncorrectNumParams(line: Int, pos: Int, private val expectedNum : Int, private val actualNum : Int) : SemanticError(line, pos) {

    override fun printError(): String {
        return "Incorrect number of parameters in line: $line at position: $pos (expected: $expectedNum, actual: $actualNum"
    }

}