package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction

class BLInstr(val funcName : String) : Instruction {


    override fun getString(): String {
        return "BL $funcName"
    }
}