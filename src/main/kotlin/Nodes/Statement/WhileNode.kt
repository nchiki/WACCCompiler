package main.kotlin.Nodes.Statement

import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
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
        if (expr.getType() == LitTypes.IdentWacc) {
            val value = table.lookupSymbol((expr as IdentNode).id)
            if (value == null) {
                errors.addError(UndefinedVariable(ctx.expr(), (expr as IdentNode).id))
                return
            } else {
                if (value.getType().equals(BaseNode("bool", null).getType())) {
                    errors.addError(IncompatibleTypes(ctx, "BOOL", value, table))
                    return
                }
            }
        }
        if (!expr.getType().equals(BaseNode("bool", null).getType())) {
            errors.addError(IncompatibleTypes(ctx, "BOOL", expr, table))
        }
        expr.semanticCheck(errors, table)
        stat.semanticCheck(errors, childTable)
        //table.boolExprCheck(expr, errors)

    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}