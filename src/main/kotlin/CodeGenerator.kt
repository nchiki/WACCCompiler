package main.kotlin

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register
import java.io.File

class CodeGenerator {

    val data= LinkedHashMap<String, String>()
    val labels: LinkedHashMap<String, ArrayList<Instruction>> = LinkedHashMap()
    val helperFuncs = LinkedHashMap<String, ArrayList<Instruction>>()
    val regsNotInUse = ArrayList<Register>() //load all registers in this initially
    var curLabel: String = String()

    fun initRegs() {
        regsNotInUse.addAll(listOf(Register.r0, Register.r1, Register.r2, Register.r3,
                Register.r4, Register.r5, Register.r6, Register.r7, Register.r8, Register.r9,
                Register.r10, Register.r11, Register.r12, Register.r13, Register.lr, Register.pc, Register.r16))
    }

    fun addLabel(label : String) {
        labels.put(label, ArrayList())
    }

    fun addInstruction(label : String, instr : Instruction) {
        labels.get(label)!!.add(instr)
    }

    fun addHelper(label : String) {
        helperFuncs.put(label, ArrayList())
    }

    fun addToHelper(label: String, instr : Instruction) {
        helperFuncs.get(label)!!.add(instr)
    }

    fun writeToFile(fileName : String) {
        var file = File(fileName)
        val created = file.createNewFile()
        if (!created) {
            file.delete()
            file = File(fileName)
            file.createNewFile()
        }
       if (!data.isEmpty()) {
           //need to fix these prints
            file.appendText(".data\n")
            for (entry in data) {
                file.appendText(entry.key + ":")
                file.appendText("\t.word ${entry.value.length-3}\n")
                file.appendText("\t.ascii ${entry.value} \n")
            }
       }
        file.appendText(".text\n")
        file.appendText(".global main\n")
        for (label in labels.asIterable()) {
            file.appendText(label.key + ":\n")
            for (instruction in label.value) {
                file.appendText("\t" + instruction.getString() + "\n")
            }
        }
        for (helper in helperFuncs.entries) {
            file.appendText(helper.key + ":\n")
            for (instruction in helper.value) {
                file.appendText("\t" + instruction.getString() + "\n")
            }
        }
    }

    /*fun loadRegs() {
        regsNotInUse.add(Register.r12)
        regsNotInUse.add(Register.r11)
        regsNotInUse.add(Register.r10)
        regsNotInUse.add(Register.r9)
        regsNotInUse.add(Register.r8)
        regsNotInUse.add(Register.r7)
        regsNotInUse.add(Register.r6)
        regsNotInUse.add(Register.r5)
        regsNotInUse.add(Register.r4)
        regsNotInUse.add(Register.r3)
        regsNotInUse.add(Register.r2)
        regsNotInUse.add(Register.r1)
        regsNotInUse.add(Register.r0)
    }*/

}