package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.reflect.KClass

class PrintStatNode(val expr : ExprNode, val ctx : BasicParser.PrintContext) : Node {
    override fun getType() : LitTypes {
        return expr.getType()
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr !is StringLitNode && expr !is CharLitNode && expr !is IdentNode && expr !is IntLitNode) {
            errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
        }
        expr.semanticCheck(errors, table)
    }
}