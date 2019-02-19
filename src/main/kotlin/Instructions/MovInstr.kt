package main.kotlin.Instructions

import main.kotlin.Utils.Condition
import main.kotlin.Utils.Register

class MovInstr(val value1 : Any, val dest : Any, val cond : Condition?) : Instruction{

    override fun getString() : String {
        if (cond != null) {
            return "MOV${cond.toString()} ${value1.toString()}, ${dest.toString()}"
        }
        return "MOV ${value1.toString()}, ${dest.toString()}"


    }

    //add code here

}