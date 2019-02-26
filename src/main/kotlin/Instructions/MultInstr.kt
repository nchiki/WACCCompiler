package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class MultInstr(val reg1: Register, val reg2: Register): Instruction {

    override fun getString(): String {
        return "MUL ${reg1.toString()}, ${reg1.toString()}, ${reg2.toString()}"
    }
}