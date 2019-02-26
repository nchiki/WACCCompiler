package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.LHS_Node
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.PairElemNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.Utils.getString
import kotlin.system.exitProcess

class ReadStatNode(private val lhs: LHS_Node, override val ctx: BasicParser.ReadContext): Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override fun generateCode(codeGenerator: CodeGenerator) {
        lhs.generateCode(codeGenerator)
        val type = symbolTable!!.lookupSymbol(lhs.id)!!.getBaseType()
        val label = "p_read_${getString(type)}"
        codeGenerator.addHelper(label)
        addInstructions(codeGenerator, type, label)

    }

    fun addInstructions(codeGenerator: CodeGenerator, type : LitTypes, printLabel : String) {
        val reg = codeGenerator.getFreeRegister()
        val label = codeGenerator.curLabel
        codeGenerator.addInstruction(label, AddInstr(codeGenerator.getLastUsedReg(), "sp", 0))
        codeGenerator.addInstruction(label, MovInstr(Register.r0, reg, null))
        codeGenerator.addInstruction(label, BLInstr(printLabel))
        if (type == LitTypes.CharWacc) {
            codeGenerator.addInstruction(label, BLInstr("putchar"))
        }
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
                } else if (value.getBaseType() != LitTypes.CharWacc && value.getBaseType() != LitTypes.IntWacc && lhs.Nodetype !is PairElemNode) {
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
