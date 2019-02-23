package main.kotlin.Instructions

import main.kotlin.Utils.Condition
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class LoadInstr(val arg1 : Register, val arg2: Any, val cond : Condition? = Condition.NULL) : Instruction {

    override fun getString(): String {
        var ldr = "LDR"
        if (cond != null && cond != Condition.NULL) {
            ldr += cond
        }

        if (arg2 is Int || arg2 is String) {
            return "$ldr $arg1, =$arg2"
        } else if (arg2 is IntLitNode) {
            return "$ldr $arg1, =${arg2.int_val}"
        } else if (arg2 is Register) {
            return "$ldr $arg1, [$arg2]"
        } else {
            return "$ldr $arg1, $arg2"

        }
    }

}
