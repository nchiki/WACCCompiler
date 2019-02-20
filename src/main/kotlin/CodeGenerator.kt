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
    private var maxLabelNum: Int = 0
    val regsInUse = ArrayList<Register>() //registers being used

    fun initRegs() {
        regsNotInUse.addAll(listOf(Register.r0, Register.r1, Register.r2, Register.r3,
                Register.r4, Register.r5, Register.r6, Register.r7, Register.r8, Register.r9,
                Register.r10, Register.r11, Register.r12, Register.r13, Register.lr, Register.pc, Register.r16))
    }

    fun removeUsedReg() {
        val reg = regsNotInUse[0]
        regsInUse.add(reg)
        regsNotInUse.removeAt(0)
    }

    fun getLastUsedReg() : Register {
        return regsInUse.get(regsInUse.count()-1)
    }

    fun addLabel(label : String) {
        labels[label] = ArrayList()
    }

    fun getNewLabel() : String {
        var label = "L$maxLabelNum"
        while (labels.containsKey(label)) {
            maxLabelNum++
            label = "L$maxLabelNum"
        }
        maxLabelNum++
        return label
    }

    fun addInstruction(label : String, instr : Instruction) {
        labels[label]!!.add(instr)
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
                file.appendText("\t.word ${entry.value.length}\n")
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


    fun compareWeights(weight1 : Int, weight2 : Int) : Int {
        return (weight1-weight2)
    }


}