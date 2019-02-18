package main.kotlin

import main.kotlin.Utils.Register
import java.util.*
import main.kotlin.Instructions.Instruction


class CodeGeneration {

    var stack = Stack<String>()
    var registers = HashMap<Register, Any>()

    fun pushToStack(value: Any) {
        // increment PC by 4 bytes
        registers.replace(Register.r15, (this.getPC() as Int).plus(4))

        //stack.push(value)
    }

    /*fun popFromStack() : String{
        // decrement PC by 4 bytes
        registers.replace(Register.r15, (this.getPC()!! as Int).minus(4))

        //return stack.pop()
    }*/

    fun loadPC() {
        registers.put(Register.r15, 0)
    }

    fun getPC() : Any? {
        return registers[Register.r15]
    }

    fun loadToReg(value : Any, reg : Register) {
        registers.replace(reg, value)
    }

    fun subReg(value: Int, reg :Register) {

    }

    fun addReg(value: Int, reg :Register) {

    }

    // MAYBE PARAMS FOR THIS FUNC?
    fun saveRegs() {

    }

    // MAYBE PARAMS FOR THIS FUNC?
    fun restoreRegs() {

    }


    fun translateCode(value : List<Instruction>) {

        val beginnningAssembly = ".text\n" +
                "\t\n" +
                "\t.global main\n" +
                "\tmain:\n" +
                "\t\tPUSH {lr}\n"

        val endingAssembly =
                "\t\tLDR r0, =0\n" +
                "\t\tPOP {pc}\n" +
        		"\t\t.ltorg"

        var instructions = ""
        for (vals in value) {
            instructions += "\t\t"
            instructions += vals.getString()
            instructions += "\n"
        }

        val program = beginnningAssembly + instructions + endingAssembly
    }
}