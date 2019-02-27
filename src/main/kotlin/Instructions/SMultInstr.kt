package main.kotlin.Instructions

import main.kotlin.Utils.Register

class SMultInstr(val reg1 : Register, val reg2 : Register) : Instruction {

    override fun getString(): String {
        return "SMULL ${reg1.toString()}, ${reg2.toString()}, ${reg1.toString()}, ${reg2.toString()}"
    }
}