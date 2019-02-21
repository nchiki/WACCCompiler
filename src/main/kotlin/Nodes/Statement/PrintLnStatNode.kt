package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintLnStatNode(val expr : ExprNode, override val ctx: BasicParser.PrintlnContext) : Node{

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {

        //load expr into register
        expr.generateCode(codeGenerator)

        val label = checkType(codeGenerator)

        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0,
                codeGenerator.getLastUsedReg(), null))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_ln"))

        codeGenerator.addHelper("p_print_ln")
    }

    fun checkType(codeGenerator: CodeGenerator) : String{
        //print String
        if (expr is StringLitNode) {
            val label = "p_print_string"
            codeGenerator.addHelper(label)
            // Print().addPrintInstrString(codeGenerator, label, str)
            return label
        }
        //print Integer
        if (expr is IntLitNode) {
            val label = "p_print_int"
            codeGenerator.addHelper(label)
            //Print().addPrintInstrString(codeGenerator, label, str)
            return label
        }
        //print Bool
        if (expr is BoolLitNode) {
            //add to data section
            val label = "p_print_bool"
            codeGenerator.addHelper(label)
            return label
        }
        return ""
    }

    /*
    //needs to be called in the end in CodeGenerator when iterating over helperFuncs
    fun addPrintInstrString(codeGenerator: CodeGenerator, label : String, msg : String) {
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

    fun addPrintLn(codeGen : CodeGenerator, msg : String) {
        codeGen.addToHelper("p_print_ln", PushInstr())
        codeGen.addToHelper("p_print_ln", LoadInstr(Register.r0, msg, null))
        codeGen.addToHelper("p_print_ln", AddInstr(Register.r0, Register.r0, 4))
        codeGen.addToHelper("p_print_ln", BLInstr("puts"))
        codeGen.addToHelper("p_print_ln", MovInstr(Register.r0, 0, null))
        codeGen.addToHelper("p_print_ln", BLInstr("fflush"))
        codeGen.addToHelper("p_print_ln", PopInstr())
    }
*/
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        expr.semanticCheck(errors, table)
    }
}
