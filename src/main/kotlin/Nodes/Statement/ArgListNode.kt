package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.AddInstr
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
        for (expr in exprs) {
            println(expr)
            if (expr is IdentNode) {
                val type = symbolTable?.lookupSymbol(expr.id)
                println(type)
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(Register.sp, Register.sp, type!!.size))
            } else {
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(Register.sp, Register.sp, expr.size))
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
