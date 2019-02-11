package Errors

class DoubleDeclare(line : Int, pos : Int, val varID : String) : SemanticError(line, pos) {

    override fun printError(): String {
        return super.printError() + "Variable $varID is already defined in this scope"
    }

}