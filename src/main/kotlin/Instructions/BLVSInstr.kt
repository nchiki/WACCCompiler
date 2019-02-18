package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class BLVSInstr(val arg1 : String) : Instruction {

    // Calls subroutine on an overflow

    override fun getString(): String {
        return "BLVS ${arg1.toString()}"
    }
}