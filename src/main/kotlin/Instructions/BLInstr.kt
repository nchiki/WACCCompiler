package main.kotlin.Instructions

class BLInstr(val funcName : String) : Instruction {


    override fun getString(): String {
        return "BL $funcName"
    }
}