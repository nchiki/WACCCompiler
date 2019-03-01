package main.kotlin.Instructions

import main.kotlin.Utils.Condition

class BLInstr(val funcName: String, private val cond: Condition? = null) : Instruction {

    override fun getString(): String {
        return if (cond != null) {
            "BL$cond $funcName"
        } else {
            "BL $funcName"
        }
    }
}