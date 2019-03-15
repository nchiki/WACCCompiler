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
import main.kotlin.Nodes.Expressions.BinaryLit
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

class PrintLnStatNode(var expr: ExprNode, override val ctx: BasicParser.PrintlnContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        // Load expr into register
        expr.generateCode(codeGenerator)


        val label = checkType(codeGenerator, expr)

        // Move last used register into argument register
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, codeGenerator.getLastUsedReg()))
        if (codeGenerator.getLastUsedReg() != Register.r0) {
            codeGenerator.freeReg(codeGenerator.getLastUsedReg())
        }

        // Get value from symbol table if it is an identifier
        if (expr is IdentNode) {
            val type = symbolTable?.lookupSymbol((expr as IdentNode).id)
            if (type is PairElemNode || type is PairNode || type is PairLitNode || type is NewPairNode) {
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_reference"))
            }
        } else if (expr is PairElemNode || expr is PairNode || expr is PairLitNode) {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_reference"))
        }
        // Branch to putchar if it is a Char
        if (expr.getBaseType() == LitTypes.CharWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("putchar"))
        } else if (label != "") {
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr(label))
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_print_ln"))
    }

    override fun optimise(valueTable: ValueTable): Node {
        expr = expr.optimise(valueTable) as ExprNode
        return this
    }

    // Returns the print function for the epxr node that is passed in
    private fun checkType(codeGenerator: CodeGenerator, expr: Node): String {

        // Print Array
        if (expr is ArrayTypeNode) {
            if (expr.getBaseType().equals(LitTypes.CharWacc)) {
                return "p_print_string"
            }
            return "p_print_reference"
        }

        // Print ParamNode
        else if (expr is ParamNode) {
            return checkType(codeGenerator, expr.type)
        }

        // Print Operator nodes
        else if (expr is BaseNode || expr is UnaryOpNode || expr is BinaryOpNode) {
            return checkBaseType(expr as ExprNode)
        }

        // Print Ident nodes
        else if (expr is IdentNode && expr !is BinaryOpNode) {
            val type = symbolTable!!.lookupSymbol(expr.id)
            return checkType(codeGenerator, type!!)
        }

        // Print String
        else if (expr is StringLitNode || (expr is ArrayTypeNode && expr.getBaseType().equals(LitTypes.CharWacc))) {
            return "p_print_string"
        }

        // Print Array Element
        else if (expr is ArrayElemNode) {
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
