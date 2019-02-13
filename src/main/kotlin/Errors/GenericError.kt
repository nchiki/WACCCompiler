package main.kotlin.Errors

import Errors.ErrorNode


class GenericError(val message : String) : ErrorNode {
    override fun printError(): String {
        return message
    }
}