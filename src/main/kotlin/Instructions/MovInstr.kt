package main.kotlin.Instructions

import main.kotlin.Nodes.CharLitNode
import main.kotlin.Utils.Condition

class MovInstr(val dest: Any, val val1: Any, val cond: Condition? = null) : Instruction {

    override fun getString(): String {

        val condition = cond?.s ?: ""

        return when (val1) {
            is Int -> "MOV$condition $dest, #$val1"
            is CharLitNode ->
                if (val1.char == "'\\0'") {
                    "MOV$condition $dest, #0"
                } else {
                    "MOV$condition $dest, #${val1.char}"
                }
            else -> "MOV$condition $dest, $val1"
        }
    }

}