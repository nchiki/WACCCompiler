package main.kotlin.Nodes.Statement

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.StoreInstr
import main.kotlin.Instructions.StrBInstr
import main.kotlin.Nodes.ArrayLitNode
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode

class ArgListNode(val exprs: List<ExprNode>, override val ctx: BasicParser.ArgListContext?) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 0

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.curLabel
        val exprsRev = exprs.reversed()

        // Iterate through reverse expressions and generate each of their code
        for (expr in exprsRev) {
            expr.generateCode(codeGenerator)
            val value = expr.size
            symbolTable!!.sp += value
            val spValue = symbolTable!!.sp

            val inMemory = if (value == 0) {
                "[sp, #-$spValue]"
            } else {
                "[sp, #-$value]"
            }

            var exprNode = expr
            // Check if it is an identifier
            if (expr.getBaseType() == LitTypes.IdentWacc) {

                if (exprNode is ArrayElemNode) {
                    exprNode = exprNode.identifier
                }
                val node = symbolTable!!.lookupSymbol((exprNode as IdentNode).id)
                if (node == null) {
                    codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
                    return
                }
                val type = node.getBaseType()
                // Check if the base type is a Char or Bool
                if ((type == LitTypes.CharWacc || type == LitTypes.BoolWacc)
                        && symbolTable!!.lookupSymbol((exprNode).id) !is ArrayTypeNode) {
                    codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory, true))
                } else {
                    codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
                }
                // Check if it is a Char or Bool
            } else if ((expr.getBaseType() == LitTypes.CharWacc || expr.getBaseType() == LitTypes.BoolWacc) && expr !is ArrayLitNode) {
                codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory, true))
            } else {
                codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
            }

        }
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        for (expr in exprs) {
            expr.semanticCheck(errors, table)
        }
    }
}
