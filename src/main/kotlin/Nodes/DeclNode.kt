package Nodes

import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.DoubleDeclare
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode

import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

import kotlin.system.exitProcess


class DeclNode(// var name
        val id: String, // type of var
        val type: ExprNode, // assigned rhs
        var rhs: RHSNode, override val ctx: BasicParser.DeclContext?, val ctxStruct : BasicParser.StructMemberContext?) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {

        val label = codeGenerator.curLabel
        val offset = rhs.getSizeOfOffset() //gets size of the data type

        symbolTable?.declareVariable(id, symbolTable!!.sp, offset) //Save variable location in symbol table
        if (rhs.ArrayLit == null) {
            symbolTable!!.sp += offset // add offset to stack pointer
            codeGenerator.addInstruction(label, SubInstr(Register.sp, "#$offset")) //Subtract stack pointer
        } else {
            symbolTable!!.sp += offset // add offset to stack pointer
        }
        rhs.generateCode(codeGenerator) // generates code of rhs and assigns value to last used reg


        //get offset of sp of current symboltable
        val offsetSp = -symbolTable!!.getValueOffset(id, codeGenerator)
        var inMemory = "[sp]"
        if (offsetSp != 0) {
            inMemory = "[sp, #${offsetSp}]"
        }

        //check if rhs is PairNode
        if (rhs.PairLit != null || rhs.getBaseType() == LitTypes.PairWacc) {
            if ( type !is ArrayTypeNode && (rhs.getBaseType() == LitTypes.CharWacc
                            || rhs.getBaseType() == LitTypes.BoolWacc)) {
                codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), "[sp]"))
            } else {
                codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), "[sp]"))
            }
        }

        //check if rhs is a BaseType
        else if (rhs.type == RHS_type.expr && (rhs.expr!!.getBaseType() == LitTypes.CharWacc
                        || rhs.expr!!.getBaseType() == LitTypes.BoolWacc)) {
            codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory))
        }

        else if (rhs.getBaseType() != LitTypes.IdentWacc && rhs.getBaseType() != LitTypes.PairWacc) {
            //if RHS is function call
            if (rhs.type == RHS_type.call) {
                if (!symbolTable!!.isHigherOrderFunction(rhs.funId!!)) {
                    val funType = symbolTable!!.getFunction(rhs.funId!!)!!.getBaseType()
                    if (funType == LitTypes.BoolWacc || funType == LitTypes.CharWacc) {
                        codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory))
                    } else {
                        codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory))
                    }
                }
            } else {
                codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory))
            }
        }

        //declaration of newpair
        else if (rhs.type == RHS_type.newpair) {
            codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory))
        }

        else {
            codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory))
        }

        if (codeGenerator.getLastUsedReg() != Register.r0) {
            codeGenerator.freeReg(codeGenerator.getLastUsedReg())
        }
    }


    override fun optimise(valueTable: ValueTable): Node {
        rhs = rhs.optimise(valueTable)

        /* Optimise only for integer and boolean constants */
        if(type !is IntLitNode && type !is BoolLitNode){
            return this
        }

        if(rhs.type.equals(RHS_type.expr)){
            val rhsExpr = rhs.expr!!
            if(rhsExpr is IntLitNode){
                valueTable.addIntValue(id, rhsExpr.int_val)
            }else if(rhsExpr is BoolLitNode){
                valueTable.addBoolValue(id, rhsExpr.bool_val.toBoolean())
            }else{
                /* The value of the rhs is dynamic */
                if(type.getBaseType().equals(LitTypes.IntWacc)){
                    valueTable.addIntValue(id, 0)
                    valueTable.setDynamic(id, true)
                }else if(type.getBaseType().equals(LitTypes.BoolWacc)){
                    valueTable.addBoolValue(id, true)
                    valueTable.setDynamic(id, true)
                }
            }
        }


        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
            exitProcess(100)
        }

        // looks up the id in the symbol table
        val value = table.lookupLocal(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (value != null) {
            // if there is already a variable with that name -> error
            if (ctx != null) {
                errors.addError(DoubleDeclare(ctx, id, value.ctx!!.start.line))
            } else {
                errors.addError(DoubleDeclare(ctxStruct!!, id, value.ctx!!.start.line))
            }
        }

        addToTable(table, id)
        rhs.semanticCheck(errors, table)

        /* RHS is a pair assignment*/
        if (rhs.type == RHS_type.pair_elem) {
            val nodeT = checkType(table, (rhs.PairLit!!.expr as IdentNode).id, rhs.PairLit!!)

            if (nodeT != type.getBaseType()) {
                errors.addError(IncompatibleTypes(ctx!!, type.getBaseType().toString(), rhs.PairLit!!.expr, table))
            }
            return
        }

        /* Easy declaration */
        if (type.getBaseType().equals(rhs.getBaseType())) {
            return
        }

        /* RHS Could be an empty array */
        if (type is ArrayTypeNode) {
            if (rhs.getBaseType().equals(LitTypes.ArrayLit)) {
                return
            }
        }

        if (rhs.type == RHS_type.array_lit) {
            val list = rhs.ArrayLit!!.exprList
            if (list.count() > 0) {
                if (list[0].getBaseType() == type.getBaseType()) {
                    return
                }
                if (list[0].getBaseType() == LitTypes.IdentWacc) {
                    val typeId = table.lookupSymbol((list[0] as IdentNode).id)?.getBaseType()
                    if (typeId == type.getBaseType()) {
                        return
                    }
                }

            } else {
                return
            }

        }

        /* RHS can only be an identifier to something now */
        val realType = rhs.returnIdentType(table)

        if (realType == null) {
            errors.addError(UndefinedVariable(rhs.ctx, id))
            return
        }

        /* Type Match */
        if (type.getBaseType().equals(realType)) {
            return
        }

        /* Types don't match */
        if (ctx != null) {
            errors.addError(IncompatibleTypes(ctx, type.getBaseType().toString(), rhs, table))
        } else {
            errors.addError(IncompatibleTypes(ctxStruct!!, type.getBaseType().toString(), rhs, table))
        }

    }

    fun checkType(table: SymbolTable, id: String, node: ExprNode): LitTypes {

        if (node is PairElemNode) {
            val elem = node.elem
            val node = (table.lookupSymbol((node.expr as IdentNode).id))
            if (node is PairNode) {
                val node = node.returnElemNode(elem)
                if (node != LitTypes.IdentWacc) {
                    return node
                }
            } else if (node is IdentNode) {
                var n = node
                while (n !is PairNode) {

                    n = (table.lookupSymbol((n as IdentNode).id))
                }
                return n.returnElemNode(elem)
            }
        } else if (node.getBaseType() == LitTypes.IdentWacc) {

            val elem = node as IdentNode
            val node = (table.lookupSymbol(elem.id))
            if (node is PairElemNode) {
                return checkType(table, (node.expr as IdentNode).id, node)
            } else if (node is IdentNode) {
                return checkType(table, (node).id, node)
            }
        }
        return node.getBaseType()

    }

    fun addToTable(table: SymbolTable, id: String) {
        table.add(type, id)
    }

}


