package main.kotlin.Instructions

import main.kotlin.Utils.Register

class StrSBInstr(val arg1: Register, val arg2: Any, val inFun: Boolean? = false) : Instruction {

    override fun getString(): String {
        return if (inFun != null && inFun) {
            "STRSB $arg1, $arg2!"
        } else {
            "STRSB $arg1, $arg2"
        }
    }
}