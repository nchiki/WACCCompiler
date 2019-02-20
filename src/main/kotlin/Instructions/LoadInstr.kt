package main.kotlin.Instructions

import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class LoadInstr(val arg1 : Register, val arg2: Any) : Instruction {

    override fun getString(): String {
        if (arg2 is Int || arg2 is String) {
            return "LDR $arg1, =$arg2"
        }
        if (arg2 is IntLitNode) {
            return "LDR $arg1, =${arg2.int_val}"
        }
        if (arg2 is Register) {
            return "LDR $arg1, [$arg2]"
        }
        //default case
        else {
            return "LDR $arg1, $arg2"

        }
    }
}