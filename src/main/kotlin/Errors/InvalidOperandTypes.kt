package Errors

class InvalidOperandTypes(val line: Int, val pos: Int) : ErrorNode {

    override fun printError(): String {
        return "Invalid operand types found in line: $line at position: $pos "
    }
}
