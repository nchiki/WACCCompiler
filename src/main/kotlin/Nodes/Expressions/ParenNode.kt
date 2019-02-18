package main.kotlin.Nodes.Expression

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ParenNode(val expr: ExprNode, override val ctx: BasicParser.ParenContext): ExprNode {

    override fun getBaseType(): LitTypes {
        return expr.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}