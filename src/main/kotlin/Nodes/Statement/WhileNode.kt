package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.UnknownIdentifier
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class WhileNode(val expr: ExprNode, val stat: Node, override val ctx: BasicParser.WhileContext): Node {

    override fun getType(): LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val childTable = SymbolTable(table)
        if (expr.getType() != LitTypes.BoolWacc) {
            errors.addError(UnknownIdentifier(ctx.start.line, ctx.start.charPositionInLine))
        }
        expr.semanticCheck(errors, table)
        stat.semanticCheck(errors, childTable)
        //table.boolExprCheck(expr, errors)

    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}