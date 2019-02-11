package Errors

open class SemanticError(val line : Int, val pos : Int) : ErrorNode {

    override fun printError(): String {
        return "Semantic error detected on line $line, at position $pos: "
    }

}