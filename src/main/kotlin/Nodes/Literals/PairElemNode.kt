package main.kotlin.Nodes

import BasicParser
import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.NullReferDef
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode

class PairElemNode(val expr: ExprNode, override val ctx: BasicParser.PairElemContext, val elem: Int) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() {
            val node = symbolTable?.lookupSymbol((expr as IdentNode).id)
            if (node is PairNode) {
                return if (elem == 0) {
                    node.fstNode.size
                } else {
                    node.sndNode.size
                }
            }
            return 0
        }

    override val weight: Int
        get() = TODO("not needed")

    override fun generateCode(codeGenerator: CodeGenerator) {
        val offset = symbolTable?.getValueOffset((expr as IdentNode).id, codeGenerator)!!
        var inMemory = "[sp]"
        if (offset != 0) {
            inMemory = "[sp, #$offset]"
        }

        val reg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, inMemory, null))
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, reg))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_check_null_pointer"))

        //load first element of pair
        if (elem == 0) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, Register.r4, null))
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, Register.r4, null))
        }
        //load second element of pair
        else {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, "[$reg, #4]", null))
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, "[$reg]", null))
        }
    }

    //look up base type of pair in symboltable
    override fun getBaseType(): LitTypes {
        if (expr is IdentNode) {
            val node = symbolTable!!.lookupSymbol(expr.id)
            if (node is PairNode) {
                return if (elem == 0) {
                    node.fstNode.getBaseType()
                } else {
                    node.sndNode.getBaseType()
                }
            }
        }
        return expr.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        expr.semanticCheck(errors, table)
    }
}
