package main.kotlin.Instructions

import main.kotlin.Utils.Condition
import main.kotlin.Utils.Register

class MovInstr(val dest : Any, val val1 : Any, val cond : Condition? = Condition.NULL) : Instruction{

    override fun getString() : String {

        return "MOV${cond.toString()} $dest, $val1"

    }

    //add code here

}