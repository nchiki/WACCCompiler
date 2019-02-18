package main.kotlin.Instructions

import main.kotlin.Utils.Condition

class MovInstr(val value1 : Any, val dest : Any, val cond : Condition = Condition.NULL) : Instruction{

    override fun getString() : String {
        return "MOV${cond.toString()} ${value1.toString()}, ${dest.toString()}"

    }

    //add code here

}