package main.kotlin.Instructions

class MovInstr(val value1 : Any, val dest : Any) : Instruction{

    override fun getString() : String {
        return "MOV ${value1.toString()} ${dest.toString()}"

    }

    //add code here

}