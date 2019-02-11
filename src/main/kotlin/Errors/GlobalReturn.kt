package Errors

class GlobalReturn(line : Int, pos : Int) : SemanticError(line, pos) {

    override fun printError(): String {
        return super.printError() + "Cannot return from the global scope"
    }

}