package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class PopInstr(val register : Register = Register.pc) : Instruction {

    override fun getString(): String {
        return "POP {$register}"
    }

}