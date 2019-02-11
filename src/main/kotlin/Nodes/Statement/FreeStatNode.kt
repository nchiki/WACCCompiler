package main.kotlin.Nodes.Statement

import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.PairElemNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClass

class FreeStatNode(val expr : ExprNode, val ctx: BasicParser.FreeContext) : Node {
    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() != LitTypes.PairWacc) {
            if (expr.getType() == LitTypes.IdentWacc) {
                val IdExpr = expr as IdentNode
                val value = IdExpr.getValueType(table)
                if (value !is PairNode && value !is PairLitNode) {
                    errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine, "PAIR", value!!, table))
                }
            } else {
                errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine, "PAIR", expr, table))
            }
        }
    }
}