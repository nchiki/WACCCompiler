package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.*
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
            val label = "p_print_bool"
            codeGenerator.addHelper(label)
            return label
        }
        return ""
    }

    /*
    //needs to be called in CodeGenerator in the end when msg are fixed
    fun addPrintInstr(codeGenerator: CodeGenerator, label : String, msg : String, bool : Boolean?) {
        codeGenerator.addToHelper(label, PushInstr())
        if (bool!!) {
            val trueDef = codeGenerator.dataAppendices.distinctBy { it is TrueDef }.get(0)
            val trueMsg = "msg_${codeGenerator.dataAppendices.indexOf(trueDef)+codeGenerator.data.size}"
            val falseMsg = "msg_${codeGenerator.dataAppendices.indexOf(trueDef)+codeGenerator.data.size+1}"
            codeGenerator.addToHelper(label, CmpInstr(Register.r0, 0))
            codeGenerator.addToHelper(label, LoadInstr(Register.r0, trueMsg, Condition.NE))
            codeGenerator.addToHelper(label, LoadInstr(Register.r0, falseMsg, Condition.EQ))
        } else {
            codeGenerator.addToHelper(label, LoadInstr(Register.r1, Register.r0, null))
        }
        codeGenerator.addToHelper(label, AddInstr(Register.r2, Register.r0, 4))
        codeGenerator.addToHelper(label, LoadInstr(Register.r0, msg, null))
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
