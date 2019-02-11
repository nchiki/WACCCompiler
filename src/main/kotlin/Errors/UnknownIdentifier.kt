package main.kotlin.Errors

import Errors.ErrorNode

class UnknownIdentifier(val line : Int, val pos: Int) : ErrorNode {
    override fun printError(): String {
        return "Identifier in line $line : $pos is not valid and unknown"
    }
}