package main.kotlin.Instructions

import main.kotlin.Utils.Register

class AndInstr(val op1: Register, val op2: Any) : Instruction {

    override fun getString(): String {
        return "AND $op1, $op1, $op2"
    }
}