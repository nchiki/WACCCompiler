package Errors

class FunctionDoubleDeclare(line : Int, pos : Int, private val funcID : String) : SemanticError(line, pos) {

    override fun printError(): String {
        return super.printError() + "Function $funcID is already defined in this scope"
    }

}