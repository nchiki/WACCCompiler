package main.kotlin.Instructions

import main.kotlin.Utils.Condition
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class LoadInstr(val arg1: Register, val arg2: Any, val cond: Condition?) : Instruction {

    override fun getString(): String {

        val ldr = if (cond == null) "LDR" else "LDR$cond"

        if (arg2 is Int || arg2 is String) {
            return if (arg2 is String && arg2.startsWith("[")) {
                "$ldr $arg1, $arg2"
            } else {
                "$ldr $arg1, =$arg2"
            }
        }

        return when (arg2) {
            is IntLitNode -> "$ldr $arg1, =${arg2.int_val}"
            is Register -> "$ldr $arg1, [$arg2]"
            else -> "$ldr $arg1, $arg2"
        }
    }

}
