package Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class AddInstr(val destination : Register, val operand1 : Any, val operand2 : Any) : Instruction {

    override fun getString(): String {
        return "ADD $destination, $operand1, $operand2"
    }

}