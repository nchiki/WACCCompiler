package main.kotlin.Instructions

import main.kotlin.Nodes.CharLitNode
import main.kotlin.Utils.Condition

class MovInstr(val dest: Any, val val1: Any, val cond: Condition? = Condition.NULL) : Instruction {

    override fun getString(): String {
        var condition = ""
        if (cond != null && cond != Condition.NULL) {
            condition = cond.s
        }
        if (val1 is Int) {
            return "MOV$condition $dest, #$val1"
        } else if (val1 is CharLitNode) {
            if (val1.char == "'\\0'") {
                return "MOV$condition $dest, #0"
            }
            return "MOV$condition $dest, #${val1.char}"
        }
        return "MOV$condition $dest, $val1"
    }

    //add code here

}