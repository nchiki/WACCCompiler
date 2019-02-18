package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ExitStatNode(val expr : ExprNode, override val ctx : BasicParser.ExitContext) : Node {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getBaseType() != LitTypes.IntWacc) {
            if (expr.getBaseType() == LitTypes.IdentWacc) {
                val idexpr = expr as IdentNode
                val value = table.lookupSymbol(expr.id)
                if (value?.getBaseType() != LitTypes.IntWacc) {
                    errors.addError(IncompatibleTypes(ctx, "INT", value!!, table))
                }
            } else {
                errors.addError(IncompatibleTypes(ctx, "INT", expr, table))
            }
        }
        expr.semanticCheck(errors, table)
    }
}
