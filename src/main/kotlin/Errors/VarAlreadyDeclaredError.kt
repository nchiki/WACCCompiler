package Errors

class VarAlreadyDeclaredError(val line : Int, val pos : Int) : ErrorNode {

    /* Error that occurs whenever we try to declare a variable that has already been declared
    * in the current scope. Returns adequate error message.
    * WOULD BE GOOD TO PASS THE LINE OR POSITION OF THE VARIABLE TO RETURN A USEFUL MESSAGE*/

    override fun printError(): String {
        return "Variable in line $line at position $pos has already been declared."
    }
}