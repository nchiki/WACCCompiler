package main.kotlin.Utils

import main.kotlin.CodeGenerator
import main.kotlin.Instructions.*

// Helper function for printing instruction strings
fun addPrintInstrString(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, LoadInstr(Register.r1, Register.r0))
    codeGenerator.addToHelper(label, AddInstr(Register.r2, Register.r0, 4))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg))
    codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
    codeGenerator.addToHelper(label, BLInstr("printf"))
    codeGenerator.addToHelper(label, MovInstr(Register.r0, 0))
    codeGenerator.addToHelper(label, BLInstr("fflush"))
    codeGenerator.addToHelper(label, PopInstr())
}

// Helper function for checking the array bounds
fun addArrayCheck(codeGenerator: CodeGenerator, label: String, msg: Int) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, CmpInstr(Register.r0, "#0"))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, "msg_$msg", Condition.LT))
    codeGenerator.addToHelper(label, BLInstr("p_throw_runtime_error", Condition.LT))
    codeGenerator.addToHelper(label, LoadInstr(Register.r1, "[r1]"))
    codeGenerator.addToHelper(label, CmpInstr(Register.r0, Register.r1))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, "msg_${msg + 1}", Condition.CS))
    codeGenerator.addToHelper(label, BLInstr("p_throw_runtime_error", Condition.CS))
    codeGenerator.addToHelper(label, PopInstr())
    addRuntimeError(codeGenerator)
}

// Helper function for checking division by zero
fun addDivZeroError(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, CmpInstr(Register.r1, "#0"))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg, Condition.EQ))
    codeGenerator.addToHelper(label, BLInstr("p_throw_runtime_error", Condition.EQ))
    codeGenerator.addToHelper(label, PopInstr())
    addRuntimeError(codeGenerator)
}

fun addPrintReference(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, MovInstr(Register.r1, Register.r0))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg))
    codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
    codeGenerator.addToHelper(label, BLInstr("printf"))
    codeGenerator.addToHelper(label, MovInstr(Register.r0, 0))
    codeGenerator.addToHelper(label, BLInstr("fflush"))
    codeGenerator.addToHelper(label, PopInstr())
}

// Helper function for printing overflow errors
fun addPrintOverflowError(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg))
    codeGenerator.addToHelper(label, BLInstr("p_throw_runtime_error"))
    addRuntimeError(codeGenerator)
}

fun addRuntimeError(codeGenerator: CodeGenerator) {
    val label = "p_throw_runtime_error"
    codeGenerator.addHelper(label)
    codeGenerator.addToHelper(label, BLInstr("p_print_string"))
    codeGenerator.addHelper("p_print_string")
    codeGenerator.addToHelper(label, MovInstr(Register.r0, -1))
    codeGenerator.addToHelper(label, BLInstr("exit"))
}

// Helper function for dereferencing null values
fun addNullDerefError(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, CmpInstr(Register.r0, "#0"))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg, Condition.EQ))
    codeGenerator.addToHelper(label, BLInstr("p_throw_runtime_error", Condition.EQ))
    codeGenerator.addToHelper(label, PopInstr())
    addRuntimeError(codeGenerator)
}

fun addNullDerefPairError(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, CmpInstr(Register.r0, "#0"))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg, Condition.EQ))
    codeGenerator.addToHelper(label, BranchInstr("p_throw_runtime_error", Condition.EQ))
    codeGenerator.addToHelper(label, PushInstr(Register.r0))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, "[r0]"))
    codeGenerator.addToHelper(label, BLInstr("free"))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, "[sp]"))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, "[r0, #4]"))
    codeGenerator.addToHelper(label, BLInstr("free"))
    codeGenerator.addToHelper(label, PopInstr(Register.r0))
    codeGenerator.addToHelper(label, BLInstr("free"))
    codeGenerator.addToHelper(label, PopInstr())
    addRuntimeError(codeGenerator)
}

// Helper function for printing bools
fun addPrintInstrBool(codeGenerator: CodeGenerator, label: String, msg: Int) {
    val trueMsg = "msg_$msg"
    val falseMsg = "msg_${msg + 1}"
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, CmpInstr(Register.r0, 0, ""))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, trueMsg, Condition.NE))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, falseMsg, Condition.EQ))
    codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
    codeGenerator.addToHelper(label, BLInstr("printf"))
    codeGenerator.addToHelper(label, MovInstr(Register.r0, 0))
    codeGenerator.addToHelper(label, BLInstr("fflush"))
    codeGenerator.addToHelper(label, PopInstr())
}

// Helper function for printing Ints
fun addPrintInstrInt(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, MovInstr(Register.r1, Register.r0))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg))
    codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
    codeGenerator.addToHelper(label, BLInstr("printf"))
    codeGenerator.addToHelper(label, MovInstr(Register.r0, 0))
    codeGenerator.addToHelper(label, BLInstr("fflush"))
    codeGenerator.addToHelper(label, PopInstr())
}

fun addPrintLn(codeGen: CodeGenerator, msg: String) {
    codeGen.addToHelper("p_print_ln", PushInstr())
    codeGen.addToHelper("p_print_ln", LoadInstr(Register.r0, msg))
    codeGen.addToHelper("p_print_ln", AddInstr(Register.r0, Register.r0, 4))
    codeGen.addToHelper("p_print_ln", BLInstr("puts"))
    codeGen.addToHelper("p_print_ln", MovInstr(Register.r0, 0))
    codeGen.addToHelper("p_print_ln", BLInstr("fflush"))
    codeGen.addToHelper("p_print_ln", PopInstr())
}

// Helper function to handle reads
fun addRead(codeGenerator: CodeGenerator, label: String, msg: String) {
    codeGenerator.addToHelper(label, PushInstr())
    codeGenerator.addToHelper(label, MovInstr(Register.r1, Register.r0))
    codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg))
    codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
    codeGenerator.addToHelper(label, BLInstr("scanf"))
    codeGenerator.addToHelper(label, PopInstr())
}