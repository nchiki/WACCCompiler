package main.kotlin.Nodes.Statement

import Errors.UndefinedVariable
import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PrintLnStatNode(val expr : ExprNode, override val ctx: BasicParser.PrintlnContext) : Node{
    override fun getType() : LitTypes {
        return expr.getType()
    }

    override fun syntaxCheck() {

    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(expr !is UnaryOpNode) {

        if (expr.getType() == LitTypes.IdentWacc) {
            val value = table.lookupSymbol((expr as IdentNode).id)
            if (value == null) {
                errors.addError(UndefinedVariable(ctx, expr.id))
                return
            } else {
                var v = value
                while(v != null && v?.getType() == LitTypes.IdentWacc) {
                    if(v is PairElemNode) {
                        v = v.expr
                    }
                    v = table.lookupSymbol((v as IdentNode).id)
                }
                if ( v == null ) {
                    errors.addError(UndefinedVariable(ctx, (expr).id))
                    return
                }
                if (v.getType() != LitTypes.StringWacc
                        && v.getType() != LitTypes.CharWacc
                        && v.getType() != LitTypes.IntWacc
                        && v.getType() != LitTypes.BoolWacc
                        && v.getType() != LitTypes.PairWacc) {
                    errors.addError(IncompatibleTypes(ctx, "{STRING, INT, CHAR, PAIR, BOOL}", v, table))
                    return
                }
            }
        } else if (expr.getType() != LitTypes.StringWacc
                && expr.getType() != LitTypes.CharWacc
                && expr.getType() != LitTypes.IdentWacc
                && expr.getType() != LitTypes.IntWacc
                && expr.getType() != LitTypes.BoolWacc
                && expr.getType() != LitTypes.PairWacc) {
            errors.addError(IncompatibleTypes(ctx, "{STRING, INT, CHAR, PAIR, BOOL}", expr, table))
        }}
        expr.semanticCheck(errors, table)
    }
}