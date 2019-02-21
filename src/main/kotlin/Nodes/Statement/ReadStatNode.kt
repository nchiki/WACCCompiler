package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.Instruction
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.LHS_Node
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.PairElemNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import kotlin.system.exitProcess

class ReadStatNode(private val lhs: LHS_Node, override val ctx: BasicParser.ReadContext): Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override fun generateCode(codeGenerator: CodeGenerator) {
        var instrs = arrayListOf<Instruction>()
        if (lhs.getBaseType() == LitTypes.BoolWacc) {
            //instrs.add(LoadInstr()) //need to add constructor and add params here
        } else if (lhs.getBaseType() == LitTypes.IntWacc) {
            //instrs.add(LoadInstr())
        } else { // ??
        }
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
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
