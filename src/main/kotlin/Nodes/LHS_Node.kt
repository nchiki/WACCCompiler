package main.kotlin.Nodes

import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.NullReferDef
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode

class LHS_Node(val Nodetype: Any?, val id: String, val line: Int, val pos : Int,
               override val ctx: BasicParser.AssignLHSContext) : ExprNode {

    override val size: Int
        get() = 4
    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var symbolTable: SymbolTable? = null

    /* Puts the address of the variable into a register */
    override fun generateCode(codeGenerator: CodeGenerator) {
        if (getBaseType() == LitTypes.IdentWacc) {
            val addressReg = codeGenerator.getFreeRegister()

            val offset = symbolTable?.getValueOffset(id, codeGenerator)!!

            codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(addressReg, "sp", "#$offset"))

        }
        if (Nodetype is ArrayElemNode) {
            Nodetype.resolveToAddress(codeGenerator)
        }
        if (Nodetype is PairElemNode) {
            val offset = symbolTable?.getValueOffset((Nodetype.expr as IdentNode).id, codeGenerator)!!
            var inMemory = "[sp]"
            if (offset != 0) {
                inMemory = "[sp, #${offset}]"
            }
            val reg = codeGenerator.getFreeRegister()
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, inMemory, null))
            if (Nodetype.elem == 0) {
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, reg, null))
            } else {
                val node = symbolTable?.lookupSymbol((Nodetype.expr as IdentNode).id) as PairNode
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, "[$reg, #${node.fstNode.size}]", null))
            }
        }
    }

    fun generateStoreFunc(regRHS : Register, inMemory: Register, codeGenerator: CodeGenerator) {
        var type = getBaseType()
        if(type == LitTypes.IdentWacc) {
            type = symbolTable!!.lookupSymbol(id)!!.getBaseType()
        }
        if(type == LitTypes.CharWacc || type == LitTypes.BoolWacc) {
            codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$inMemory]"))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, "[$inMemory]"))
        }

        /*if(RHS_Node.getBaseType() == LitTypes.CharWacc || RHS_Node.getBaseType() == LitTypes.BoolWacc) {
            if (RHS_Node.type == RHS_type.pair_elem) {
                codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[sp]"))
            } else {
                codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$inMemory]"))
            }
        }else if(RHS_Node.type == RHS_type.call && ( RHS_Node.returnIdentType(symbolTable!!) == LitTypes.CharWacc
                        || RHS_Node.returnIdentType(symbolTable!!) == LitTypes.BoolWacc) ) {
            codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$inMemory]"))
        } else if (LHS_Node.Nodetype is PairElemNode) {
            if(RHS_Node.getBaseType() == LitTypes.IdentWacc) {
                val type = symbolTable!!.lookupSymbol((RHS_Node.expr!! as IdentNode).id)!!.getBaseType()
                if(type == LitTypes.CharWacc || type == LitTypes.BoolWacc) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$inMemory]"))
                }
            } else {
                if (RHS_Node.getBaseType() == LitTypes.CharWacc || RHS_Node.getBaseType() == LitTypes.BoolWacc) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[$inMemory]"))
                } else {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, "[$inMemory]"))
                }
            }
        } else {
            if(RHS_Node.type == RHS_type.pair_elem && (RHS_Node.PairLit!!.getBaseType() == LitTypes.CharWacc||
                            RHS_Node.PairLit!!.getBaseType() == LitTypes.BoolWacc)) {
                codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(regRHS, "[sp]"))
                /*}else if(RHS_Node.type == RHS_type.pair_elem && RHS_Node.PairLit!!.getBaseType() == LitTypes.IdentWacc) {
                            symbolTable.*/
            } else {

                if(LHS_Node.getBaseType() == LitTypes.IdentWacc) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, "[$inMemory]"))
                } else {
                    codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(regRHS, "[sp]"))
                }
            }
        }*/
        codeGenerator.freeReg(inMemory)
        codeGenerator.freeReg(regRHS)
    }

    override fun getBaseType(): LitTypes {
        if (Nodetype is ArrayElemNode) {
            return Nodetype.getBaseType()
        } else if (Nodetype is PairElemNode) {
            return Nodetype.getBaseType()
        } else {
            return LitTypes.IdentWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        val value = table.lookupSymbol(id)

        if( value == null || value is FunctionNode) {
            errors.addError(UndefinedVariable(ctx, id))
        }

        if(Nodetype is IdentNode){
            Nodetype.semanticCheck(errors, table)
        }

        if (Nodetype is ArrayElemNode) {
            Nodetype.semanticCheck(errors, table)
        }
        if (Nodetype is PairElemNode) {
            Nodetype.semanticCheck(errors, table)
        }
    }

}
