package main.kotlin.Errors


class UndeclaredVariableError(val id: String): ErrorNode {

    override fun printError(): String {
        return "$id needs to be declared before it can be used!"
    }

}