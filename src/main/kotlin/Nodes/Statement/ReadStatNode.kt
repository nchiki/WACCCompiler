package main.kotlin.Nodes.Statement

import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.LHSNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.PairElemNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.NullReferDef
import main.kotlin.Utils.Register
import main.kotlin.Utils.getString
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.system.exitProcess

class ReadStatNode(private val lhs: LHSNode, override val ctx: BasicParser.ReadContext): Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {

        // Read Identity node
        if (lhs.nodeType is IdentNode) {
            lhs.generateCode(codeGenerator)
        }

        val label: String
        var type = symbolTable!!.lookupSymbol(lhs.id)

        // Read Pair Elem
        if (lhs.nodeType is PairElemNode) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(codeGenerator.regsNotInUse.peek(), "[sp]", null))
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, codeGenerator.regsNotInUse.peek()))
            codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_check_null_pointer"))

            type as PairNode
            var elemPair = type.fstNode
            if (lhs.nodeType.elem == 1) {
                elemPair = type.sndNode
            }
            label = "p_read_${getString(elemPair.type?.getBaseType()!!)}"
        } else {
            val type = symbolTable!!.lookupSymbol(lhs.id)!!.getBaseType()
            label = "p_read_${getString(type)}"
        }
        codeGenerator.addHelper(label)
        if (type == null) {
            codeGenerator.addError(NullReferDef)
        }
        if (type != null) {
            addInstructions(codeGenerator, type.getBaseType(), label)
        }

    }

    /* If a value is input from standard input then set is as dynamic */
    override fun optimise(valueTable: ValueTable): Node {
        if(!lhs.nodeType!!.equals(LitTypes.IdentWacc)){
            return this
        }

        val type = symbolTable?.lookupSymbol(lhs.id)!!

        if(type is IntLitNode || type is BoolLitNode){
            valueTable.setDynamic(lhs.id, true)
        }

        return this
    }

    // Adds necessary Read instructions
    private fun addInstructions(codeGenerator: CodeGenerator, type : LitTypes, printLabel : String) {
        val reg = codeGenerator.getFreeRegister()
        val label = codeGenerator.curLabel
        var offset = 0
        if(lhs.getBaseType() == LitTypes.IdentWacc) {
            offset = symbolTable!!.getValueOffset(lhs.id, codeGenerator)
        }
        codeGenerator.addInstruction(label, AddInstr(codeGenerator.getLastUsedReg(), "sp", offset))
        codeGenerator.addInstruction(label, MovInstr(Register.r0, reg, null))
        codeGenerator.freeReg(reg)
        codeGenerator.addInstruction(label, BLInstr(printLabel))
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        lhs.semanticCheck(errors, table)
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        if (lhs.getBaseType() == LitTypes.IdentWacc) {

            var value = table.lookupSymbol(lhs.id)

            if (value == null) {
                errors.addError(UndefinedVariable(ctx, lhs.id))
            } else {
                while (value != null && value.getBaseType() == LitTypes.IdentWacc) {
                    if (value is PairElemNode) {
                        value = value.expr
                    }
                    value = table.lookupSymbol((value as IdentNode).id)
                }
                if (value == null) {
                    errors.addError(UndefinedVariable(ctx, lhs.id))
                } else if (value.getBaseType() != LitTypes.CharWacc && value.getBaseType() != LitTypes.IntWacc && lhs.nodeType !is PairElemNode) {
                    errors.addError(IncompatibleTypes(ctx, "CHAR or INT", value, table))
                }
            }
        } else {
            if (lhs.getBaseType() != LitTypes.CharWacc && lhs.getBaseType() != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes(ctx, "CHAR or INT", lhs, table))
            }
        }
    }
}
