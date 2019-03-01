package main.kotlin.Instructions

import main.kotlin.Utils.Register

class SMultInstr(val reg1: Register, val reg2: Register) : Instruction {

    override fun getString(): String {
        return "SMULL $reg1, $reg2, $reg1, $reg2"
    }
}