package main.kotlin.Instructions

import main.kotlin.Nodes.Node
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class LoadInstr(val arg1 : Register, val arg2: Any) : Instruction {

    override fun getString(): String {
        return "LDR ${arg1.toString()}, ${arg2.toString()}"
    }
}