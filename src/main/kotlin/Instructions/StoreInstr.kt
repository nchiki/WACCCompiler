package main.kotlin.Instructions

import main.kotlin.Utils.Register

class StoreInstr(val arg1: Register, val arg2: Any, val inFun: Boolean? = false) : Instruction {

    override fun getString(): String {
        return if (inFun != null && inFun) {
            "STR $arg1, $arg2!"
        } else if (arg2 is Register) {
            "STR $arg1, [$arg2]"
        } else {
            "STR $arg1, $arg2"
        }
    }
}