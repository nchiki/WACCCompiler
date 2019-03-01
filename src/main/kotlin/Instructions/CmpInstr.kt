package main.kotlin.Instructions

import src.main.kotlin.Nodes.Literals.IntLitNode

class CmpInstr(val operand1: Any, val operand2: Any, val constant: String?) : Instruction {

    override fun getString(): String {
        return if (!constant.equals("") && constant != null) {
            "CMP $operand1, $operand2, $constant"
        } else if (operand2 is Int || operand2 is IntLitNode) {
            "CMP $operand1, #$operand2"
        } else {
            "CMP $operand1, $operand2"
        }
    }

}