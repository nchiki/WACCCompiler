package main.kotlin.Instructions

import main.kotlin.Utils.Condition
import main.kotlin.Utils.Register

class MovInstr(val dest : Any, val val1 : Any, val cond : Condition?) : Instruction{

    override fun getString() : String {
        if (cond != null) {
            return "MOV${cond.toString()} ${dest.toString()}, ${val1.toString()}"
        }
        return "MOV ${dest.toString()}, ${val1.toString()}"


    }

    //add code here

}