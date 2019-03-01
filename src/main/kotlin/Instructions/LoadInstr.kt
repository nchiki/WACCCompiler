package main.kotlin.Instructions

import main.kotlin.Utils.Condition
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class LoadInstr(val arg1: Register, val arg2: Any, val cond: Condition?) : Instruction {

    override fun getString(): String {
        var ldr = ""
        if (cond == null) {
            ldr = "LDR"
        } else {
            ldr = "LDR$cond"
        }
        if (arg2 is Int || arg2 is String) {
            if (arg2 is String && arg2.startsWith("[")) {
                return "$ldr $arg1, $arg2"
            }
            return "$ldr $arg1, =$arg2"
        }
        if (arg2 is IntLitNode) {
            return "$ldr $arg1, =${arg2.int_val}"
        }
        if (arg2 is Register) {
            return "$ldr $arg1, [$arg2]"
        }
        //default case
        else {
            return "$ldr $arg1, $arg2"

        }
    }
}
