package main.kotlin.Instructions


class EORInstr(val value: Any, val const: Int) : Instruction {

    override fun getString(): String {
        return "EOR $value, $value, #$const"
    }
}