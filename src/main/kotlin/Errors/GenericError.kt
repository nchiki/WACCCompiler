package main.kotlin.Errors

class GenericError(val message : String) : ErrorNode {
    override fun printError(): String {
        return message
    }
}