package main.kotlin

import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

class CodeGenerator {

    val data = LinkedHashMap<String, LiteralDefs>() //data section to be printed before main
    val errors = LinkedList<LiteralDefs>()

    val scopedLabels: LinkedHashMap<String, ArrayList<String>> = LinkedHashMap()
    var curScope: String? = null

    val labels: LinkedHashMap<String, ArrayList<Instruction>> = LinkedHashMap()

    val helperFuncs = LinkedHashMap<String, ArrayList<Instruction>>()
    val regsNotInUse = PriorityQueue<Register>() //load all registers in this initially
    var curLabel: String = String()
    private var maxLabelNum: Int = 0

    val regsInUse = PriorityQueue<Register>()

    private var lastUsedReg: Register = Register.r0

    val functions = LinkedHashMap<String, ArrayList<String>>()
    var curFunction: String = "main"

    fun initRegs() {
        regsNotInUse.addAll(Register.values())
        regsNotInUse.remove(Register.r0)
        regsNotInUse.remove(Register.r1)
        regsNotInUse.remove(Register.r2)
        regsNotInUse.remove(Register.r3)
        regsNotInUse.remove(Register.pc)
        regsNotInUse.remove(Register.sp)
        regsNotInUse.remove(Register.r16)
        regsNotInUse.remove(Register.lr)
    }

    /* Frees the register */
    fun freeReg(reg: Register) {
        regsInUse.remove(reg)
        regsNotInUse.add(reg)
    }

    /* Returns the last used register */
    fun getLastUsedReg(): Register {
        return if (regsInUse.isEmpty()) {
            lastUsedReg
        } else {
            regsInUse.reversed()[0]
        }
    }

    /* Returns a free register and marks it as used */
    fun getFreeRegister(): Register {
        var reg = regsNotInUse.poll()
        while (reg == Register.r0 || reg == Register.r1 || reg == Register.r2 || reg == Register.r3) {
            reg = regsNotInUse.poll()
        }
        regsInUse.add(reg)
        return reg
    }

    fun switchFunctions(function: String) {
        functions.put(function, ArrayList())
        addLabel(function, null)
        curFunction = function
        curLabel = function
        curScope = function
    }

    fun addLabel(label: String, scope: String?) {
        if (scope == null) {
            scopedLabels[label] = ArrayList()
            scopedLabels[label]?.add(label)
        } else {
            scopedLabels[scope]?.add(label)
        }
        labels[label] = ArrayList()
        functions[curFunction]?.add(label)
    }

    fun getNewLabel(): String {
        var label = "L$maxLabelNum"
        while (labels.containsKey(label)) {
            maxLabelNum++
            label = "L$maxLabelNum"
        }
        maxLabelNum++
        return label
    }

    fun addInstruction(label: String, instr: Instruction) {
        if (instr is BLInstr) {
            when (instr.funcName) {
                "p_throw_overflow_error" -> {
                    addHelper(instr.funcName)
                    addError(OverflowDef)
                }
                "p_check_null_pointer" -> {
                    addHelper(instr.funcName)
                    addError(NullReferDef)
                }
                "p_check_array_bounds" -> {
                    addHelper(instr.funcName)
                    addError(ArrayBoundNegativeDef)
                    addError(ArrayBoundsLargeDef)
                }
                "p_print_ln" -> addHelper(instr.funcName)
                "p_print_reference" -> addHelper(instr.funcName)
                "p_print_int" -> addHelper(instr.funcName)
            }
        }
        labels[label]!!.add(instr)
    }

    fun addHelper(label: String) {
        helperFuncs[label] = ArrayList()
    }

    fun addToHelper(label: String, instr: Instruction) {
        helperFuncs[label]!!.add(instr)
    }

    fun addError(error: LiteralDefs) {
        if (!errors.contains(error)) {
            errors.addLast(error)
        }
    }

    fun writeToFile(fileName: String) {
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
            file.appendText("${entry.key}:\n")
            file.appendText("\t.word ${str.getLength()}\n")
            file.appendText("\t.ascii ${str.getString()}\n")
        }

        file.appendText(".text\n")
        file.appendText(".global main\n")

        for ((function: String, funcLabels: ArrayList<String>) in functions) {
            for (funcLabel: String in funcLabels) {
                file.appendText("$funcLabel:\n")

                for (instruction in labels[funcLabel]!!.asIterable()) {
                    file.appendText("\t ${instruction.getString()} \n")
                }
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
            if (error is OverflowDef) {
                Print_Read().addPrintOverflowError(this, "p_throw_overflow_error", msg)
            } else if (error is DivZeroDef) {
                Print_Read().addDivZeroError(this, "p_check_divide_by_zero", msg)
            } else if (error is NullReferDef) {
                val label: String
                if (helperFuncs.contains("p_free_pair")) {
                    label = "p_free_pair"
                    Print_Read().addNullDerefPairError(this, label, msg)
                } else if (helperFuncs.contains("p_check_null_pointer")) {
                    label = "p_check_null_pointer"
                    Print_Read().addNullDerefError(this, label, msg)
                }
            } else if (error is ArrayBoundNegativeDef) {
                Print_Read().addArrayCheck(this, "p_check_array_bounds", data.size)
            }
            data[msg] = error
        }
    }

    fun checkPrints() {
        if (helperFuncs.containsKey("p_print_reference")) {
            val msg = "msg_${data.size}"
            data.put(msg, PairDef())
            Print_Read().addPrintReference(this, "p_print_reference", msg)
        }
        if (helperFuncs.containsKey("p_print_string")) {
            val msg = "msg_${data.size}"
            data.put(msg, StringAppendDef())
            Print_Read().addPrintInstrString(this, "p_print_string", msg)
        }
        if (helperFuncs.containsKey("p_print_int")) {
            val msg = "msg_${data.size}"
            data.put(msg, IntAppendDef())
            Print_Read().addPrintInstrInt(this, "p_print_int", msg)
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

    fun compareWeights(weight1: Int, weight2: Int): Int {
        return weight1 - weight2
    }

}
