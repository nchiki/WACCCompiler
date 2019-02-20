package main.kotlin.Instructions

import main.kotlin.Utils.Condition

class MovInstr(val dest : Any, val val1 : Any, val cond : Condition? = Condition.NULL) : Instruction{

    override fun getString() : String {

        if(val1 is Int ) {
            return "MOV $dest, #$val1"
        }
        return "MOV $dest, $val1"

    }

    //add code here

}