package main.kotlin.Instructions

import main.kotlin.Utils.Register

class StrSBInstr(val arg1: Register, val arg2: Any, val inFun: Boolean? = false) : Instruction {

    override fun getString(): String {
        if (inFun != null && inFun) {
            return "STRSB $arg1, $arg2!"
        } else {
            return "STRSB $arg1, $arg2"
        }
    }
}