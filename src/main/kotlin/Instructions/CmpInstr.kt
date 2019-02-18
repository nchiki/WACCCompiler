package kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class CmpInstr(val operand1 :Any, val operand2 : Any) : Instruction {

    override fun getString(): String {
        return "CMP $operand1, $operand2"
    }

}