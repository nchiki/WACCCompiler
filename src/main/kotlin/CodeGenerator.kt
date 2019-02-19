package main.kotlin

import main.kotlin.Instructions.Instruction
import main.kotlin.Utils.Register
import java.io.File
import java.io.PrintWriter

class CodeGenerator {

    val data = HashMap<String, String>()
    val labels: LinkedHashMap<String, ArrayList<Instruction>> = LinkedHashMap()
    val regsInUse = HashSet<Register>()

    fun newFunction(identifier : String) {

    }


    fun addLabel(label : String) {
        labels.put(label, ArrayList())
    }

    fun addInstruction(label : String, instr : Instruction) {
        labels.get(label)!!.add(instr)
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
            file.appendText("wacc")
            for (entry in data) {
                file.appendText(entry.value + ":")
                file.appendText("\t.word " + entry.key.length)
                file.appendText("\t.ascii \"" + entry.key + "\"")
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
    }

}