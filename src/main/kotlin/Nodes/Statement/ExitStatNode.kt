package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class ExitStatNode(val expr : ExprNode, val ctx : BasicParser.ExitContext) : Node {
    override fun getType(): LitTypes {
        TODO()
    }

    override fun syntaxCheck() {
        //not needed
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr !is IntLitNode) {
//            println(5)
//            println(table.table)
            errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine, "INT", expr, table))
        }
        expr.semanticCheck(errors, table)
    }
}