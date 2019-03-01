package main.kotlin.Instructions

import main.kotlin.Utils.Condition

class BLInstr(val funcName: String, val cond: Condition = Condition.NULL) : Instruction {

    override fun getString(): String {
        return if (cond != Condition.NULL) {
            "BL$cond $funcName"
        } else {
            "BL $funcName"
        }
    }
}