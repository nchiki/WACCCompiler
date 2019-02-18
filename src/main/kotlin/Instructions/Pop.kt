package Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class Pop(val register : Register = Register.pc) : Instruction {

    override fun getString(): String {
        return "POP {$register}"
    }

}