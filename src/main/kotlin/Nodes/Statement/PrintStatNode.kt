package main.kotlin.Nodes.Statement

import main.kotlin.Instructions.AddInstr
import main.kotlin.Instructions.PopInstr
import main.kotlin.Instructions.PushInstr
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.Register
import main.kotlin.Utils.StringAppendDef
import main.kotlin.Utils.StringLitDef
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        //load expr into register
        expr.generateCode(codeGenerator)

        val label = checkType(codeGenerator)

        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0,
                codeGenerator.getLastUsedReg(), null))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))

    }

    fun checkType(codeGenerator: CodeGenerator) : String{
        val str = "msg_${codeGenerator.dataAppendices.size-1}"
        //print String
        if (expr is StringLitNode) {
            codeGenerator.data.put(str, StringLitDef(expr.str))
            codeGenerator.data.put(strApp, StringAppendDef("\"%.*s\\0\""))
            label = "p_print_string"
            codeGenerator.addHelper(label)
            // Print().addPrintInstrString(codeGenerator, label, str)
            return label
        }
        //print Integer
        if (expr is IntLitNode) {
            val label = "p_print_int"
            codeGenerator.addHelper(label)
            Print().addPrintInstrString(codeGenerator, label, str)
            return label
        }
        //print Bool
        if (expr is BoolLitNode) {
            val label = "p_print_bool"
            codeGenerator.addHelper(label)
            return label
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, str))
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, reg, null))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))

    }

    /*
    //needs to be called in CodeGenerator in the end when msg are fixed
    fun addPrintInstr(codeGenerator: CodeGenerator, label : String, msg : String, bool : Boolean?) {
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
    */

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}
