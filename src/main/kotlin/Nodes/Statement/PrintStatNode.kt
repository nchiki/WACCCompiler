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
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.system.exitProcess

class PrintStatNode(var expr: ExprNode, override val ctx: BasicParser.PrintContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        //load expr into register
        expr.generateCode(codeGenerator)
        val label = checkType(codeGenerator, expr)
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0,
                codeGenerator.getLastUsedReg(), null))

        if (codeGenerator.getLastUsedReg() != Register.r0) {
            codeGenerator.freeReg(codeGenerator.getLastUsedReg())
        }

        if (expr is PairElemNode || expr is PairNode || expr is PairLitNode || expr is NewPairNode) {
            val label = codeGenerator.curLabel
            codeGenerator.addInstruction(label, BLInstr("p_print_reference"))
        }

        if (expr.getBaseType() == LitTypes.CharWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("putchar"))
        } else {
            if (label != "") {
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))

            }
        }
    }

    override fun optimise(valueTable: ValueTable): Node {
        expr = expr.optimise(valueTable) as ExprNode
        return this
    }

    fun checkType(codeGenerator: CodeGenerator, expr: Node): String {
        if (expr is ArrayTypeNode) {
            if (expr.getBaseType().equals(LitTypes.CharWacc)) {
                val label = "p_print_string"
                codeGenerator.addHelper(label)

                return label
            }
            return "p_print_reference"
        }

        if (expr is ParamNode) {
            return checkType(codeGenerator, expr.type)
        }

        if (expr is BaseNode || expr is UnaryOpNode || expr is BinaryOpNode) {
            return checkBaseType(expr as ExprNode)
        }

        if (expr is ArrayElemNode) {
            val identifierType = symbolTable?.lookupSymbol(expr.identifier.id)?.getBaseType()!!
            return when (identifierType) {
                LitTypes.IntWacc -> "p_print_int"
                LitTypes.CharWacc -> "putchar"
                LitTypes.BoolWacc -> "p_print_bool"
                LitTypes.StringWacc -> "p_print_string"
                else -> ""
            }
        }

        if (expr is IdentNode && expr !is BinaryOpNode) {

            val type = symbolTable?.lookupSymbol(expr.id)
            if (type is PairElemNode || type is PairNode || type is PairLitNode || type is NewPairNode || type is ArrayTypeNode) {
                return "p_print_reference"
            } else {
                return checkType(codeGenerator, type!!)
            }
        }
        //print String
        if (expr is StringLitNode) {
            val label = "p_print_string"
            // Print().addPrintInstrString(codeGenerator, label, str)
            return label
        }
        //print Integer
        if (expr is IntLitNode) {
            val label = "p_print_int"
            //Print().addPrintInstrString(codeGenerator, label, str)
            return label
        }
        //print Bool
        if (expr is BoolLitNode || expr is BinaryOpNode && expr.getBaseType() == LitTypes.BoolWacc
                || expr is BoolOpNode) {
            //add to data section
            val label = "p_print_bool"
            return label
        }

        return ""
    }

    // Returns the print function for the epxr node that is passed in
    private fun checkBaseType(expr: ExprNode): String {
        val type = expr.getBaseType()

        // Print Char
        if (type == LitTypes.CharWacc) {
            return "putchar"
        }

        // Print Int
        if (type == LitTypes.IntWacc) {
            val label = "p_print_int"
            return label
        }

        // Print Bool
        if (type == LitTypes.BoolWacc) {
            val label = "p_print_bool"
            return label
        }

        // Print String
        if (type == LitTypes.StringWacc) {
            val label = "p_print_string"
            return label
        }

        return ""
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
            exitProcess(100)
        }
        expr.semanticCheck(errors, table)
    }
}
