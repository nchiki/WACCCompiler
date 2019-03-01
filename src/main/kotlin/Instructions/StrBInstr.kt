package main.kotlin.Instructions

import main.kotlin.Utils.Register

class StrBInstr(val arg1: Register, val arg2: Any, val inFun: Boolean? = false) : Instruction {

    override fun getString(): String {
        if (inFun != null && inFun) {
            return "STRB $arg1, $arg2!"
        } else if (arg2 is Register) {
            return "STRB $arg1, [$arg2]"
        } else {
            return "STRB $arg1, $arg2"
        }
    }
}