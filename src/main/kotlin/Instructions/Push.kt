package Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class Push(val register : Register = Register.lr) : Instruction {

    override fun getString(): String {
        return "PUSH {$register}"
    }

}