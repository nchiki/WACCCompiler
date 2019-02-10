package main.kotlin.Errors

import Errors.ErrorNode

class UndeclaredVariableError(val id: String): ErrorNode {

    override fun printError(): String {
        return "$id needs to be declared before it can be used!"
    }

}