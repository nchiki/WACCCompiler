package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expressions.BinaryOpNode
import main.kotlin.Nodes.Expressions.BoolOpNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.*
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        //load expr into register
        expr.generateCode(codeGenerator)

        val label = checkType(codeGenerator, expr)

        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0,
                codeGenerator.getLastUsedReg(), null))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))

    }

    fun checkType(codeGenerator: CodeGenerator, expr : Node) : String {
        if (expr is BaseNode) {
           return checkBaseType(codeGenerator, expr)
        }
        //get the node mapped to the identifier from symboltable
        if (expr is IdentNode) {
            println("goes into identNode case")
            val type = symbolTable!!.lookupSymbol(expr.id)
            println(type)
            return checkType(codeGenerator, type!!)
        }

        if (expr is CharLitNode) {
            if (expr is StringLitNode) {
                val label = "p_print_string"
                codeGenerator.addHelper(label)
                return label
            }
        }
        //print String
        if (expr is StringLitNode) {
            val label = "p_print_string"
            codeGenerator.addHelper(label)
            return label
        }
        //print Integer
        if (expr is IntLitNode) {
            val label = "p_print_int"
            codeGenerator.addHelper(label)
            return label
        }
        //print Bool
        if (expr is BoolLitNode || expr is BinaryOpNode && expr.getBaseType() == LitTypes.BoolWacc
                || expr is BoolOpNode) {
            val label = "p_print_bool"
            codeGenerator.addHelper(label)
            return label
        }
        return ""
    }

    fun checkBaseType(codeGenerator: CodeGenerator, expr: BaseNode) : String {
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
