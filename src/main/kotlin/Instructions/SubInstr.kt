package main.kotlin.Instructions

class SubInstr(val destination: Any, val operand2: Any, val flag: String = "") : Instruction {

    override fun getString(): String {
        return "SUB$flag $destination, $destination, $operand2"
    }

}