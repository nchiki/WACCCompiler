package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class StrSBInstr(val arg1 : Register, val arg2: Any,val inFun : Boolean? = false) : Instruction {

    override fun getString(): String {
        if(inFun != null && inFun) {
            return "STRSB ${arg1.toString()}, ${arg2.toString()}!"
        }
        return "STRSB ${arg1.toString()}, ${arg2.toString()}"
    }
}