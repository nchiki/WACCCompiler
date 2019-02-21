package main.kotlin.Utils

import main.kotlin.CodeGenerator
import main.kotlin.Instructions.*

class Print_Read {

    fun addPrintInstrString(codeGenerator: CodeGenerator,label : String, msg : String) {

        codeGenerator.addToHelper(label, PushInstr())
        codeGenerator.addToHelper(label, LoadInstr(Register.r1, Register.r0, null))
        codeGenerator.addToHelper(label, AddInstr(Register.r2, Register.r0, 4))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg, null))
        codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
        codeGenerator.addToHelper(label, BLInstr("printf"))
        codeGenerator.addToHelper(label, MovInstr(Register.r0, 0, null))
        codeGenerator.addToHelper(label, BLInstr("fflush"))
        codeGenerator.addToHelper(label, PopInstr())
    }

    fun addPrintInstrBool(codeGenerator: CodeGenerator, label : String, msg : Int) {
        val trueMsg = "msg_$msg"
        val falseMsg = "msg_${msg+1}"
        codeGenerator.addToHelper(label, PushInstr())
        codeGenerator.addToHelper(label, CmpInstr(Register.r0, 0))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, trueMsg, Condition.NE))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, falseMsg, Condition.EQ))
        codeGenerator.addToHelper(label, AddInstr(Register.r2, Register.r0, 4))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg, null))
        codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
        codeGenerator.addToHelper(label, BLInstr("printf"))
        codeGenerator.addToHelper(label, MovInstr(Register.r0, 0, null))
        codeGenerator.addToHelper(label, BLInstr("fflush"))
        codeGenerator.addToHelper(label, PopInstr())
    }


    fun addPrintInstrInt(codeGenerator: CodeGenerator, label : String, msg : String) {
        val trueMsg = "msg_$msg"
        val falseMsg = "msg_${msg+1}"
        codeGenerator.addToHelper(label, PushInstr())
        codeGenerator.addToHelper(label, CmpInstr(Register.r0, 0))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, trueMsg, Condition.NE))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, falseMsg, Condition.EQ))
        codeGenerator.addToHelper(label, AddInstr(Register.r2, Register.r0, 4))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg, null))
        codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
        codeGenerator.addToHelper(label, BLInstr("printf"))
        codeGenerator.addToHelper(label, MovInstr(Register.r0, 0, null))
        codeGenerator.addToHelper(label, BLInstr("fflush"))
        codeGenerator.addToHelper(label, PopInstr())
        //add intvalue to data section
    }

    fun addPrintLn(codeGen : CodeGenerator, msg : String) {
        codeGen.addToHelper("p_print_ln", PushInstr())
        codeGen.addToHelper("p_print_ln", LoadInstr(Register.r0, msg, null))
        codeGen.addToHelper("p_print_ln", AddInstr(Register.r0, Register.r0, 4))
        codeGen.addToHelper("p_print_ln", BLInstr("puts"))
        codeGen.addToHelper("p_print_ln", MovInstr(Register.r0, 0, null))
        codeGen.addToHelper("p_print_ln", BLInstr("fflush"))
        codeGen.addToHelper("p_print_ln", PopInstr())
    }

    fun addRead(codeGenerator: CodeGenerator, label : String, msg : String) {
        codeGenerator.addToHelper(label, PushInstr())
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg))
        codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
        codeGenerator.addToHelper(label, BLInstr("scanf"))
        codeGenerator.addToHelper(label, PopInstr())
    }


}
