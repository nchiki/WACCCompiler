package main.kotlin.Nodes.Statement

import Instructions.AddInstr
import Instructions.PopInstr
import Instructions.PushInstr
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintLnStatNode(val expr : ExprNode, override val ctx: BasicParser.PrintlnContext) : Node{

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val msg = codeGenerator.data.size
        var label = ""
        val str = "msg_$msg"
        val strApp = "msg_${msg+1}"
        if (expr is StringLitNode) {
            codeGenerator.data.put(str, expr.str)
            codeGenerator.data.put(strApp, "\"%.*s\\0\"")
            label = "p_print_string"
            codeGenerator.addHelper(label)
            addPrintInstr(codeGenerator, label, strApp)
        }
        if (expr is IntLitNode) {
            label = "p_print_int"
            codeGenerator.addHelper("p_print_int")
            addPrintInstr(codeGenerator, label, strApp)
        }
        var index = 0
        var reg = codeGenerator.regsNotInUse.get(index)
        codeGenerator.regsNotInUse.remove(reg)
        while(reg < Register.r4) {
            reg = codeGenerator.regsNotInUse.get(index++)
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, str))
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, reg, null))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))

        codeGenerator.addHelper("p_print_ln")
        val ln = "msg_${msg+2}"
        codeGenerator.data.put(ln, "\"\\0\"")
        addPrintLn(codeGenerator, ln)
    }

    fun addPrintInstr(codeGenerator: CodeGenerator, label : String, msg : String) {
        codeGenerator.addToHelper(label, PushInstr())
        codeGenerator.addToHelper(label, LoadInstr(Register.r1, Register.r0))
        codeGenerator.addToHelper(label, AddInstr(Register.r2, Register.r0, 4))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg))
        codeGenerator.addToHelper(label, AddInstr(Register.r0, Register.r0, 4))
        codeGenerator.addToHelper(label, BLInstr("printf"))
        codeGenerator.addToHelper(label, MovInstr(Register.r0, 0, null))
        codeGenerator.addToHelper(label, BLInstr("fflush"))
        codeGenerator.addToHelper(label, PopInstr())
    }

    fun addPrintLn(codeGen : CodeGenerator, msg : String) {
        codeGen.addToHelper("p_print_ln", PushInstr())
        codeGen.addToHelper("p_print_ln", LoadInstr(Register.r0, msg))
        codeGen.addToHelper("p_print_ln", AddInstr(Register.r0, Register.r0, 4))
        codeGen.addToHelper("p_print_ln", BLInstr("puts"))
        codeGen.addToHelper("p_print_ln", MovInstr(Register.r0, 0, null))
        codeGen.addToHelper("p_print_ln", BLInstr("fflush"))
        codeGen.addToHelper("p_print_ln", PopInstr())

    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}
