package kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Condition

class BranchInstr(val label : String, val cond : Condition = Condition.NULL) : Instruction{


    override fun getString(): String {
        return "B${cond.toString()} label"
    }

}