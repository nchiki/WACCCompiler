package main.kotlin.Instructions

import main.kotlin.Utils.Condition

class BLInstr(val funcName: String, val cond: Condition = Condition.NULL) : Instruction {

    override fun getString(): String {
        if (cond != Condition.NULL) {
            return "BL$cond $funcName"
        }
        return "BL $funcName"
    }
}