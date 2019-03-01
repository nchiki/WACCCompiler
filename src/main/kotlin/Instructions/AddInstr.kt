package main.kotlin.Instructions

import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class AddInstr(private val destination: Register, private val operand1: Any,
               private val operand2: Any, private val flag: String = "") : Instruction {

    override fun getString(): String {
        return if (operand2 is Int || operand2 is IntLitNode) {
            "ADD$flag $destination, $operand1, #$operand2"
        } else {
            "ADDS $destination, $operand1, $operand2"
        }
    }

}