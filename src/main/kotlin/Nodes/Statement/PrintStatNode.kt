package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{
    override fun getType() : LitTypes {
        return expr.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}