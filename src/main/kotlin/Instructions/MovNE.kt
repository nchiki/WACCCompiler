package kotlin.Instructions

import main.kotlin.Instructions.Instruction

class MovNE(val value1 : Any, val dest : Any) : Instruction {

    override fun getString() : String {
        return "MOVNE ${value1.toString()} ${dest.toString()}"
    }
}