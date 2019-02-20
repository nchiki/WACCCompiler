package main.kotlin.Instructions

import main.kotlin.Utils.Condition
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class MovInstr(val dest : Any, val val1 : Any, val cond : Condition?) : Instruction{

    override fun getString() : String {
        if (dest is Int || dest is IntLitNode) {
            if (cond == null) {
                return "MOV $value1, #$dest"
            } else {
                return "MOV$cond $value1, #$dest"
            }
        }
        if (cond != null) {
            return "MOV${cond.toString()} ${dest.toString()}, ${val1.toString()}"
        }
        return "MOV ${dest.toString()}, ${val1.toString()}"


    }

    //add code here

}