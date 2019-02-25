package main.kotlin

import main.kotlin.Errors.OverflowError
import main.kotlin.Instructions.AddInstr
import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.*
import java.io.File
import java.util.*
import kotlin.collections.LinkedHashMap

class CodeGenerator {

    val data= LinkedHashMap<String,LiteralDefs>() //data section to be printed before main
    val errors = LinkedList<LiteralDefs>()
    val labels: LinkedHashMap<String, ArrayList<Instruction>> = LinkedHashMap()
    val helperFuncs = LinkedHashMap<String, ArrayList<Instruction>>()
    val regsNotInUse = ArrayList<Register>() //load all registers in this initially
    var curLabel: String = String()
    private var maxLabelNum: Int = 0
    val regsInUse = ArrayList<Register>() //registers being used
    val idsAddresses = LinkedHashMap<String, Int>()

    private var lastUsedReg: Register = Register.r0


    fun initRegs() {
        regsNotInUse.addAll(Register.values())
        regsNotInUse.remove(Register.pc)
        regsNotInUse.remove(Register.sp)
        regsNotInUse.remove(Register.r16)
        regsNotInUse.remove(Register.lr)
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
        return regsInUse.get(regsInUse.count()-1)
    }

    fun getParamReg() : Register {
        var reg = regsNotInUse.get(0)
        var index = 1
        while(reg == Register.r0 || reg == Register.r1 || reg == Register.r2 ||
                reg == Register.r3 || reg == Register.r16 || reg == Register.pc) {
            index++

            reg = regsNotInUse.get(index)
        }

        lastUsedReg = reg
        regsInUse.add(lastUsedReg)
        regsNotInUse.remove(lastUsedReg)
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



    fun addInstruction(label : String, instr : Instruction) {
        labels[label]!!.add(instr)
    }

    fun addHelper(label : String) {
        helperFuncs.put(label, ArrayList())
    }

    fun addToHelper(label: String, instr : Instruction) {
        helperFuncs.get(label)!!.add(instr)
    }

    fun addError(error : LiteralDefs) {
        if (!errors.contains(error)) {
            errors.addLast(error)
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
        file.appendText(".data\n")

        checkErrors()
        checkPrints()

        //print all strings and appendices
        for (entry in data.entries) {
            val str = entry.value
            file.appendText(entry.key+":\n")
            file.appendText("\t.word ${str.getLength()}\n")
            file.appendText("\t.ascii ${str.getString()}\n")
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

    fun checkErrors() {
        for (error in errors) {
            val msg = "msg_${data.size}"
            data.put(msg, error)
        }
    }

    fun checkPrints() {
        for (func in helperFuncs) {
            if (func.key.equals("p_throw_overflow_error")) {
                val msg = "msg_${data.size}"
                Print_Read().addPrintOverflowError(this, "p_throw_overflow_error", msg)
            }
            if (func.key.equals("p_print_string")) {
                val msg = "msg_${data.size}"
                data.put(msg, StringAppendDef())
                Print_Read().addPrintInstrString(this, "p_print_string", msg)
            }
            if (func.key.equals("p_print_int")) {
                val msg = "msg_${data.size}"
                data.put(msg, IntAppendDef())
                Print_Read().addPrintInstrInt(this, "p_print_int", msg)
            }
            if (func.key.equals("p_print_bool")) {
                val msg = "msg_${data.size}"
                val trueInd = data.size
                data.put(msg, TrueDef())
                data.put("msg_${data.size}", FalseDef())
                Print_Read().addPrintInstrBool(this, "p_print_bool", trueInd)
            }
            if (func.key.equals("p_print_ln")) {
                val msg = "msg_${data.size}"
                data.put(msg, NewLineDef())
                Print_Read().addPrintLn(this, msg)
            }
            if (func.key.equals("p_read_int")) {
                val msg = "msg_${data.size}"
                data.put(msg, ReadIntApp())
                Print_Read().addRead(this, "p_read_int", msg)
            }
            if (func.key.equals("p_read_char")) {
                val msg = "msg_${data.size}"
                data.put(msg, ReadCharApp())
                Print_Read().addRead(this, "p_read_char", msg)
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

    fun restoreLastReg() {
        while(!regsInUse.isEmpty()) {
            regsNotInUse.add(getLastUsedReg().name.substring(1).toInt(), getLastUsedReg())
            regsInUse.remove(getLastUsedReg())
        }
    }




}
