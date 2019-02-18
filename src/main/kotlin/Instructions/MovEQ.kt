package kotlin.Instructions

import main.kotlin.Instructions.Instruction

class MovEQ(val value1 : Any, val dest : Any) : Instruction{

    override fun getString(): String {
        return "MOVEQ ${value1.toString()} ${dest.toString()}"
    }
}