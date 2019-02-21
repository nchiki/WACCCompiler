package main.kotlin.Nodes.Expression

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ParenNode(val expr: ExprNode, override val ctx: BasicParser.ParenContext): ExprNode {
    override val size = expr.size
    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        expr.generateCode(codeGenerator)
    }

    override fun getBaseType(): LitTypes {
        return expr.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}
