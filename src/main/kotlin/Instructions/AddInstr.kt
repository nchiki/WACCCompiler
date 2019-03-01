package main.kotlin.Instructions

import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class AddInstr(val destination: Register, val operand1: Any, val operand2: Any, val flag: String = "") : Instruction {

    override fun getString(): String {
        if (operand2 is Int || operand2 is IntLitNode) {
            return "ADD$flag $destination, $operand1, #$operand2"
        }
        return "ADDS $destination, $operand1, $operand2"
    }

}