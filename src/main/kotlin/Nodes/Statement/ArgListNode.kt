package main.kotlin.Nodes.Statement

import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.AddInstr
import main.kotlin.Instructions.StoreInstr
import main.kotlin.Instructions.StrBInstr
import main.kotlin.Nodes.ArrayLitNode
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode

class ArgListNode(val exprs : List<ExprNode>, override val ctx: BasicParser.ArgListContext?) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        val label = codeGenerator.curLabel
        val exprsRev = exprs.reversed()
        for(expr in exprsRev) {
            expr.generateCode(codeGenerator)

            var inMemory : String
            val value = expr.size
            symbolTable!!.sp += value
            val spValue = symbolTable!!.sp
            inMemory = "[sp, #-$spValue]"
            if(value != 0) {
                inMemory = "[sp, #-$value]"
            }
            if(expr.getBaseType() == LitTypes.IdentWacc) {
                val type = symbolTable!!.lookupSymbol((expr as IdentNode).id)!!.getBaseType()
                if((type == LitTypes.CharWacc || type == LitTypes.BoolWacc)
                        && symbolTable!!.lookupSymbol((expr as IdentNode).id) !is ArrayTypeNode) {
                    codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory, true))
                } else {
                    codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
                }
            } else {
                if ((expr.getBaseType() == LitTypes.CharWacc || expr.getBaseType() == LitTypes.BoolWacc)&& expr !is ArrayLitNode) {
                    codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory, true))
                } else {
                    codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
                }
            }
            if(codeGenerator.getLastUsedReg() != Register.r0) {
                codeGenerator.freeReg(codeGenerator.getLastUsedReg())
            }
        }
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        for (expr in exprs) {
            expr.semanticCheck(errors, table)
        }
    }
}
