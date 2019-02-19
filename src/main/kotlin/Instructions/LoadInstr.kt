package main.kotlin.Instructions

import main.kotlin.Nodes.Node
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class LoadInstr(val arg1 : Register, val arg2: Node) : Instruction {

    override fun getString(): String {
        if (checkConstant()) {
            arg2 as IntLitNode
            return "LDR ${arg1.toString()}, =${arg2.int_val}"
        }
        return "LDR ${arg1.toString()}, ${arg2.toString()}"
    }

    fun checkConstant() : Boolean{
        return (arg2 is IntLitNode)
    }


}