package main.kotlin.Instructions

import main.kotlin.Nodes.CharLitNode
import main.kotlin.Utils.Condition

class MovInstr(val dest : Any, val val1 : Any, val cond : Condition? = Condition.NULL) : Instruction{

    override fun getString() : String {

        if(val1 is Int ) {
            return "MOV $dest, #$val1"
        } else if(val1 is CharLitNode) {
            return "MOV $dest, #${val1.char}"
        }
        return "MOV $dest, $val1"

    }

    //add code here

}