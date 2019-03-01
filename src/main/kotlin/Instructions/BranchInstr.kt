package main.kotlin.Instructions

import main.kotlin.Utils.Condition

class BranchInstr(val label: String, val cond: Condition? = null) : Instruction {

    override fun getString(): String {
        if (cond != null) {
            return "B${cond.s} $label"
        }
        return "B $label"
    }

}