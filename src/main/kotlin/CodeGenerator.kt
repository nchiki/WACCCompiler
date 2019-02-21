package main.kotlin

import main.kotlin.Instructions.AddInstr
import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.*
import java.io.File
import java.util.*
import kotlin.collections.LinkedHashMap

class CodeGenerator {

    val data= LinkedHashMap<String,LiteralDefs>() //data section to be printed before main
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
        regsNotInUse.addAll(Register.values())
    }

    fun freeReg(reg : Register) {
        regsInUse.add(reg)
        regsNotInUse.remove(reg)
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
            var value = 0 - sp
            sp += value
            while(value > 1024) {
                addInstruction(curLabel, AddInstr(Register.sp, Register.sp, 1024))
                value -= 1024
            }
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

    fun addInstrToHelper(label : String, instrs : List<Instruction>) {
        for (instr in instrs) {
            addToHelper(label, instr)
        }
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

            checkPrints()

            //print all strings and appendices
            for (entry in data.entries) {
                val str = entry.value
                file.appendText(entry.key+":\n")
                file.appendText("\t.word ${str.getLength()}\n")
                file.appendText("\t.ascii ${str.getString()}\n")
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

    fun checkPrints() {
        if (helperFuncs.containsKey("p_print_string")) {
            val msg = "msg_${data.size}"
            data.put(msg, StringAppendDef())
            Print_Read().addPrintInstrString(this, "p_print_string", msg)
        }
        if (helperFuncs.containsKey("p_print_int")) {
            val msg = "msg_${data.size}"
<<<<<<< HEAD
            data.put(msg, IntAppendDef())
            Print_Read().addPrintInstrInt(this, "p_print_int", msg)
=======
            data.put("msg_${data.size}", IntAppendDef())
            Print().addPrintInstrInt(this, "p_print_int", msg)
>>>>>>> 202b7cacb9b471fda670b1cfdadac1ea15ccb3bb
        }
        if (helperFuncs.containsKey("p_print_bool")) {
            val msg = "msg_${data.size}"
            val trueInd = data.size
            data.put(msg, TrueDef())
            data.put("msg_${data.size}", FalseDef())
            Print_Read().addPrintInstrBool(this, "p_print_bool", trueInd)
        }
        if (helperFuncs.containsKey("p_print_ln")) {
            val msg = "msg_${data.size}"
            data.put(msg, NewLineDef())
            Print_Read().addPrintLn(this, msg)
        }
        if (helperFuncs.containsKey("p_read_int")) {
            val msg = "msg_${data.size}"
            data.put(msg, ReadIntApp())
            Print_Read().addRead(this, "p_read_int", msg)
        }
        if (helperFuncs.containsKey("p_read_char")) {
            val msg = "msg_${data.size}"
            data.put(msg, ReadCharApp())
            Print_Read().addRead(this, "p_read_char", msg)
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