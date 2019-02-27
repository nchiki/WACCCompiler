package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register

class StoreInstr(val arg1 : Register, val arg2: Any, val inFun : Boolean? = false) : Instruction {

    override fun getString(): String {
        if(inFun != null && inFun) {
            return "STR ${arg1.toString()}, ${arg2.toString()}!"
        }
        if (arg2 is Register) {
            return "STR ${arg1}, [${arg2}]"
        }
        return "STR ${arg1.toString()}, ${arg2.toString()}"
    }
}