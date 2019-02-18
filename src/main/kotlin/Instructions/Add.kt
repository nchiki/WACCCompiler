package Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class Add(val desination : Register, val operand1 : Any, val operand2 : Any) : Instruction {

    override fun getString(): String {
        return "ADD $desination, $operand1, $operand2"
    }

}