package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class LoadSBInstr(val arg1 : Register, val arg2: Any) : Instruction {

    override fun getString(): String {
       return "LDRSB ${arg1.toString()} ${arg2.toString()}"
    }
}