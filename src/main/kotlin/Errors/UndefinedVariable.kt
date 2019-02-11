package Errors

class UndefinedVariable(line : Int, pos : Int, val varID : String) : SemanticError(line, pos) {

    override fun printError(): String {
        return super.printError() + "Variable $varID is not defined in this scope"
    }

}