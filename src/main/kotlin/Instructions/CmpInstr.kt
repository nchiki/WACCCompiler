package main.kotlin.Instructions

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode

class CmpInstr(val operand1 :Any, val operand2 : Any) : Instruction {

    override fun getString(): String {
        if (operand2 is Int || operand2 is IntLitNode) {
            return "CMP $operand1, #$operand2"
        }
        return "CMP $operand1, $operand2"
    }

}