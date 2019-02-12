package main.kotlin.Nodes.Statement

import Errors.UndefinedVariable
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import Nodes.StatementNode
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

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() == LitTypes.IdentWacc) {
            val value = table.lookupSymbol((expr as IdentNode).id)
            if (value == null) {
                errors.addError(UndefinedVariable(ctx, (expr as IdentNode).id))
                return
            } else {
                if (value.getType() != LitTypes.StringWacc
                        && value.getType() != LitTypes.CharWacc
                        && value.getType() != LitTypes.IntWacc
                        && value.getType() != LitTypes.BoolWacc
                        && value.getType() != LitTypes.PairWacc) {
                    errors.addError(IncompatibleTypes(ctx, "{STRING, INT, CHAR, PAIR, BOOL}", value, table))
                    return
                }
            }
        }
        if (expr.getType() != LitTypes.StringWacc
                && expr.getType() != LitTypes.CharWacc
                && expr.getType() != LitTypes.IdentWacc
                && expr.getType() != LitTypes.IntWacc
                && expr.getType() != LitTypes.BoolWacc
                && expr.getType() != LitTypes.PairWacc) {
            errors.addError(IncompatibleTypes(ctx, "{STRING, INT, CHAR, PAIR, BOOL}", expr, table))
        }
        expr.semanticCheck(errors, table)
    }
}