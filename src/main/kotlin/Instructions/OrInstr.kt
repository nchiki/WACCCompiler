package main.kotlin.Instructions

import main.kotlin.Utils.Register

class OrInstr(val op1 : Register, val op2 : Any) : Instruction {

    override fun getString(): String {
        return "ORR $op1, $op1, $op2"
    }
}