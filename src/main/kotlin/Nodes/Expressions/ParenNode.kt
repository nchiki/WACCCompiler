package main.kotlin.Nodes.Expression

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ParenNode(val expr: ExprNode, override val ctx: BasicParser.ParenContext): ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size = expr.size
    override val weight: Int
        get() = expr.weight

    override fun generateCode(codeGenerator: CodeGenerator) {
        expr.generateCode(codeGenerator)
    }

    override fun getBaseType(): LitTypes {
        return expr.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        expr.semanticCheck(errors, table)
    }
}
