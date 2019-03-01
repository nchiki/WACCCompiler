package main.kotlin.Nodes.Statement

import BasicParser
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import Nodes.ParamNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expressions.BinaryOpNode
import main.kotlin.Nodes.Expressions.BoolOpNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.system.exitProcess

class PrintLnStatNode(val expr: ExprNode, override val ctx: BasicParser.PrintlnContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        //load expr into register
        expr.generateCode(codeGenerator)
        val label = checkType(codeGenerator, expr)
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, codeGenerator.getLastUsedReg(), null))
        if (codeGenerator.getLastUsedReg() != Register.r0) {
            codeGenerator.freeReg(codeGenerator.getLastUsedReg())
        }

        if (expr is IdentNode) {
            val type = symbolTable?.lookupSymbol(expr.id)
            if (type is PairElemNode || type is PairNode || type is PairLitNode || type is NewPairNode) {
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_reference"))
            }
        } else if (expr is PairElemNode || expr is PairNode || expr is PairLitNode) {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_reference"))
        }

        if (expr.getBaseType() == LitTypes.CharWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("putchar"))
        } else if (label != "") {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_ln"))
    }

    private fun checkType(codeGenerator: CodeGenerator, expr: Node): String {
        if (expr is ArrayTypeNode) {
            if (expr.getBaseType().equals(LitTypes.CharWacc)) {
                return "p_print_string"
            }
            return "p_print_reference"
        } else if (expr is ParamNode) {
            return checkType(codeGenerator, expr.type)
        } else if (expr is BaseNode || expr is UnaryOpNode || expr is BinaryOpNode) {
            return checkBaseType(expr as ExprNode)
        } else if (expr is IdentNode && expr !is BinaryOpNode) {
            val type = symbolTable!!.lookupSymbol(expr.id)
            return checkType(codeGenerator, type!!)
        }

        //print String
        else if (expr is StringLitNode || (expr is ArrayTypeNode && expr.getBaseType().equals(LitTypes.CharWacc))) {
            return "p_print_string"
        } else if (expr is ArrayElemNode) {
            val identifierType = symbolTable?.lookupSymbol(expr.identifier.id)?.getBaseType()!!
            return when (identifierType) {
                LitTypes.IntWacc -> "p_print_int"
                LitTypes.CharWacc -> "putchar"
                LitTypes.BoolWacc -> "p_print_bool"
                LitTypes.StringWacc -> "p_print_string"
                else -> ""
            }
        }
        //print Integer
        else if (expr is IntLitNode || (expr is ExprNode && expr.getBaseType() == LitTypes.IntWacc)) {
            return "p_print_int"
        }
        //print Bool
        else if (expr is BoolLitNode || expr is BinaryOpNode && expr.getBaseType() == LitTypes.BoolWacc
                || expr is BoolOpNode) {
            //add to data section
            return "p_print_bool"
        }
        return ""
    }

    private fun checkBaseType(expr: ExprNode): String {
        return when (expr.getBaseType()) {
            LitTypes.CharWacc -> "putchar"
            LitTypes.IntWacc -> "p_print_int"
            LitTypes.BoolWacc -> "p_print_bool"
            LitTypes.StringWacc -> "p_print_string"
            else -> ""
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
            exitProcess(100)
        }
        expr.semanticCheck(errors, table)
    }
}
