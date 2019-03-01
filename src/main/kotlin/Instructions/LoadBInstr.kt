package main.kotlin.Instructions

import main.kotlin.Utils.Register

class LoadBInstr(val arg1: Register, val arg2: Any) : Instruction {

    override fun getString(): String {
        return "LDRB $arg1, $arg2"
    }
}