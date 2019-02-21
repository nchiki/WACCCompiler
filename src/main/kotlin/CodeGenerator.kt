package main.kotlin

import main.kotlin.Instructions.AddInstr
import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.*
import java.io.File
import java.util.*

class CodeGenerator {

    val data= LinkedList<LiteralDefs>() //data section to be printed before main
    val dataAppendices = LinkedList<LiteralDefs>()
    val labels: LinkedHashMap<String, ArrayList<Instruction>> = LinkedHashMap()
    val helperFuncs = LinkedHashMap<String, ArrayList<Instruction>>()
    val regsNotInUse = ArrayList<Register>() //load all registers in this initially
    var curLabel: String = String()
    private var maxLabelNum: Int = 0
    val regsInUse = ArrayList<Register>() //registers being used
    var sp = 0
    val idsAddresses = LinkedHashMap<String, Int>()

    private var lastUsedReg: Register = Register.r0


    fun initRegs() {
        regsNotInUse.addAll(listOf(Register.r0, Register.r1, Register.r2, Register.r3,
                Register.r4, Register.r5, Register.r6, Register.r7, Register.r8, Register.r9,
                Register.r10, Register.r11, Register.r12, Register.r13, Register.lr, Register.pc, Register.r16))
    }


    fun freeReg(reg : Register) {
        regsNotInUse.add(reg)
        regsInUse.remove(reg)
    }


    fun removeUsedReg() {
        val reg = regsNotInUse[0]
        regsInUse.add(reg)
        regsNotInUse.removeAt(0)
    }

    fun getLastUsedReg() : Register {
        return lastUsedReg
    }

    fun getParamReg() : Register {
        var reg = regsNotInUse.get(0)
        var index = 1
        while(reg < Register.r4) {
            reg = regsNotInUse.get(index++)
        }
        lastUsedReg = reg
        regsInUse.add(lastUsedReg)
        //freeReg(reg)
        return reg
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

    fun recoverSp() {
        //checks if we have loaded any variable to memory in current scope so
        // sp has decreased, and adds the offset to the sp
        if(sp < 0) {
            val value = 0 - sp
            addInstruction(curLabel, AddInstr(Register.sp, Register.sp, value))
        }
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
            file.appendText(".data\n")
           var ind = 0

           //print all strings
           for (str in data) {
               file.appendText("msg_$ind")
               file.appendText("\t.word ${str.getLength()}\n")
               file.appendText("\t.ascii ${str.getString()}\n")
               ind++
           }

           //print all appendices
           for (app in dataAppendices.distinctBy {it -> it.javaClass}) {
               file.appendText("msg_$ind")
               file.appendText("\t.word ${app.getLength()}\n")
               file.appendText("\t.ascii ${app.getString()}\n")
               ind++
           }
           if (helperFuncs.contains("p_print_ln")) {
               file.appendText("msg_$ind")
               file.appendText("\t.word 1\n")
               file.appendText("\t.ascii \"\\0\"\n")
           }

       }

        //print main
        file.appendText(".text\n")
        file.appendText(".global main\n")
        for (label in labels.asIterable()) {
            file.appendText(label.key + ":\n")
            for (instruction in label.value) {
                file.appendText("\t" + instruction.getString() + "\n")
            }
        }

        //print helper methods
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

    fun saveOffset(id : String, address : Int) {
        idsAddresses.put(id, address)
    }

    fun returnOffset(id :String) : Int?{
        return idsAddresses.get(id)
    }


}