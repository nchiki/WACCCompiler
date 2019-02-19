package main.kotlin.Instructions

import main.kotlin.Utils.Condition

class MovInstr(val dest : Any, val value : Any, val cond : Condition = Condition.NULL) : Instruction{

    override fun getString() : String {
        return "MOV${cond.s} ${dest.toString()}, ${value.toString()}"

    }

    //add code here

}