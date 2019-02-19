package main.kotlin.Instructions

import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class LoadInstr(val arg1 : Register, val arg2: Any) : Instruction {

    override fun getString(): String {
        if (arg2 is Int) {
            return "LDR ${arg1.toString()}, =${arg2.toString()}"
        }
        if (arg2 is IntLitNode) {
            return "LDR ${arg1.toString()}, =${arg2.int_val.toString()}"
        } else {
            return "LDR ${arg1.toString()}, ${arg2.toString()}"
        }
    }
}