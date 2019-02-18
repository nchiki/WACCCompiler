package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class SubInstr(val destination : Register, val operand2 : Any) : Instruction {

    override fun getString(): String {
        return "SUB $destination, $destination, $operand2"
    }

}