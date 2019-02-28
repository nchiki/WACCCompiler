package main.kotlin.Nodes.Statement

import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import Nodes.ParamNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expressions.BinaryOpNode
import main.kotlin.Nodes.Expressions.BoolOpNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.*
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintLnStatNode(val expr : ExprNode, override val ctx: BasicParser.PrintlnContext) : Node{

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        //load expr into register
        println(codeGenerator.curLabel)
        println(symbolTable!!.sp)
        expr.generateCode(codeGenerator)
        val label = checkType(codeGenerator, expr)
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0,
                codeGenerator.getLastUsedReg(), null))
        if(codeGenerator.getLastUsedReg() != Register.r0) {
            codeGenerator.freeReg(codeGenerator.getLastUsedReg())
        }

        if (expr is IdentNode) {
            var type = symbolTable?.lookupSymbol(expr.id)
            if (type is PairElemNode || type is PairNode || type is PairLitNode || type is NewPairNode) {
                val label = codeGenerator.curLabel

                codeGenerator.addInstruction(label, BLInstr("p_print_reference"))
                codeGenerator.addHelper("p_print_reference")
            }
        } else if (expr is PairElemNode || expr is PairNode || expr is PairLitNode) {
            val label = codeGenerator.curLabel
            codeGenerator.addInstruction(label, BLInstr("p_print_reference"))
            codeGenerator.addHelper("p_print_reference")
        }

        if (expr.getBaseType() == LitTypes.CharWacc) {
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("putchar"))
        } else {
            if(label != "" ) {
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))

            }
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_ln"))

        codeGenerator.addHelper("p_print_ln")
    }

    fun checkType(codeGenerator: CodeGenerator, expr : Node) : String {
        if(expr is ArrayTypeNode) {
            codeGenerator.addHelper("p_print_reference")
            return "p_print_reference"
        }

        if(expr is ParamNode) {
            return checkType(codeGenerator, expr.type)
        }
        if (expr is BaseNode || expr is UnaryOpNode || expr is BinaryOpNode) {
            return checkBaseType(codeGenerator, expr as ExprNode)
        }
        if (expr is IdentNode && expr !is BinaryOpNode) {
            val type = symbolTable!!.lookupSymbol(expr.id)
            return checkType(codeGenerator, type!!)

        }

        //print String
        if (expr is StringLitNode || (expr is ArrayTypeNode && expr.getBaseType().equals(LitTypes.CharWacc)) ) {
            val label = "p_print_string"
            codeGenerator.addHelper(label)

            return label
        }

        if(expr is ArrayElemNode){
            val identifierType = symbolTable?.lookupSymbol(expr.identifier.id)?.getBaseType()!!
            if(identifierType.equals(LitTypes.IntWacc)){
                val label = "p_print_int"
                codeGenerator.addHelper(label)
                //Print().addPrintInstrString(codeGenerator, label, str)
                return label
            }else if(identifierType.equals(LitTypes.CharWacc)){
                return "putchar"
            }else if(identifierType.equals(LitTypes.BoolWacc)){
                val label = "p_print_bool"
                codeGenerator.addHelper(label)
                return label
            }

        }
        //print Integer
        if (expr is IntLitNode || (expr is ExprNode && expr.getBaseType() == LitTypes.IntWacc)) {
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
            return "putchar"
            /*val label = "p_print_string"
            codeGenerator.addHelper(label)
            return label*/
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
