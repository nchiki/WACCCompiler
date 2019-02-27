package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
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
            var inMemory = "sp"
            if(offset != 0) {
                inMemory = "sp, #${offset}"
            }

            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(addressReg, inMemory))
        }
        if (Nodetype is ArrayElemNode) {
            Nodetype.resolveToAddress(codeGenerator)
        }
        if (Nodetype is PairElemNode) {
            Nodetype.generateCode(codeGenerator)
        }
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

        if (Nodetype is ArrayElemNode) {
            Nodetype.semanticCheck(errors, table)
        }
        if (Nodetype is PairElemNode) {
            Nodetype.semanticCheck(errors, table)
        }
    }

}
