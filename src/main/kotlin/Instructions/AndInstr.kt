package main.kotlin.Instructions

import main.kotlin.Utils.Register

class AndInstr(private val op1: Register, private val op2: Any) : Instruction {

    override fun getString(): String {
        return "AND $op1, $op1, $op2"
    }
}