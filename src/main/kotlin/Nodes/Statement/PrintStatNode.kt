package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.StringLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClass

class PrintStatNode(val expr : ExprNode, val ctx : BasicParser.PrintContext) : Node {
    fun getType() : BaseNode {
        return BaseNode("string")
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.type != LitTypes.StringWacc && expr.type != LitTypes.IntWacc) {
            errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
        }
        expr.semanticCheck(errors, table)
    }
}