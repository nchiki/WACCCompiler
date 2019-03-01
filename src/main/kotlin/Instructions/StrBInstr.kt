package main.kotlin.Instructions

import main.kotlin.Utils.Register

class StrBInstr(val arg1: Register, val arg2: Any, val inFun: Boolean? = false) : Instruction {

    override fun getString(): String {
        return if (inFun != null && inFun) {
            "STRB $arg1, $arg2!"
        } else if (arg2 is Register) {
            "STRB $arg1, [$arg2]"
        } else {
            "STRB $arg1, $arg2"
        }
    }
}