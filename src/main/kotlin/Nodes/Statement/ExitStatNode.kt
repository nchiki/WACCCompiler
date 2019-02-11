package main.kotlin.Nodes.Statement

import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class ExitStatNode(val expr : ExprNode, val ctx : BasicParser.ExitContext) : StatementNode {
    override fun getType(): LitTypes {
        TODO()
    }

    override fun syntaxCheck() {
        //not needed
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() != LitTypes.IntWacc) {
            if(expr.getType() == LitTypes.IdentWacc) {
                val idexpr = expr as IdentNode
                val value = table.lookupSymbol(expr.id)
                if (value?.getType() != LitTypes.IntWacc) {
                    errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
                }
            }else {
                errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
            }
        }
        expr.semanticCheck(errors, table)
    }
}