package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expressions.BinaryOpNode
import main.kotlin.Nodes.Expressions.BoolOpNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintLnStatNode(val expr : ExprNode, override val ctx: BasicParser.PrintlnContext) : Node{

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {

        //load expr into register
        expr.generateCode(codeGenerator)

        val label = checkType(codeGenerator, expr)
        //println(expr)
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0,
                codeGenerator.getLastUsedReg(), null))
        codeGenerator.restoreLastReg()

        if (expr.getBaseType() == LitTypes.CharWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("putchar"))
        } else {
            if(label != "") {
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))

            }
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_ln"))

        codeGenerator.addHelper("p_print_ln")
    }

    fun checkType(codeGenerator: CodeGenerator, expr : Node) : String{

        if (expr is BaseNode || expr is UnaryOpNode || expr is BinaryOpNode) {
            return checkBaseType(codeGenerator, expr as ExprNode)
        }
        if (expr is IdentNode && expr !is BinaryOpNode) {
            val type = symbolTable!!.lookupSymbol(expr.id)
            return checkType(codeGenerator, type!!)
        }
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
        if (expr is BoolLitNode || expr is BinaryOpNode && expr.getBaseType() == LitTypes.BoolWacc
                || expr is BoolOpNode) {
            //add to data section
            val label = "p_print_bool"
            codeGenerator.addHelper(label)
            return label
        }
        return ""
    }
    fun checkBaseType(codeGenerator: CodeGenerator, expr: ExprNode) : String {
        val type = expr.getBaseType()

        if (type == LitTypes.CharWacc) {
            val label = "p_print_string"
            codeGenerator.addHelper(label)
            return label
        }
        if (type == LitTypes.IntWacc) {
            val label = "p_print_int"
            codeGenerator.addHelper(label)
            return label
        }
        if (type == LitTypes.BoolWacc) {
            val label = "p_print_bool"
            codeGenerator.addHelper(label)
            return label
        }
        if (type == LitTypes.StringWacc) {
            val label = "p_print_string"
            codeGenerator.addHelper(label)
            return label
        }
        return ""
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        expr.semanticCheck(errors, table)
    }
}
