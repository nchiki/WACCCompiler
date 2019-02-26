package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class StrBInstr(val arg1 : Register, val arg2: Any, val inFun : Boolean? = false) : Instruction {

    override fun getString(): String {
        if(inFun != null && inFun) {
            return "STRB ${arg1.toString()}, ${arg2.toString()}!"
        }
        return "STRB ${arg1.toString()}, ${arg2.toString()}"
    }
}