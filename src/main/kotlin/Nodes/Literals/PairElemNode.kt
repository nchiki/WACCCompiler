package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.NullReferDef
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode

class PairElemNode(val expr : ExprNode, override val ctx: BasicParser.PairElemContext, val elem : Int) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = expr.size

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        if (expr is IdentNode) {
            val node = symbolTable?.lookupSymbol(expr.id)
            if (node == null) {
                codeGenerator.addError(NullReferDef)
                codeGenerator.addHelper("p_check_null_pointer")
                codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_check_null_pointer"))
            }
        }
    }

    override fun getBaseType() : LitTypes {
        return expr.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        expr.semanticCheck(errors, table)
    }
}
