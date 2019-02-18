package main.kotlin.Nodes.Statement

import Errors.UndefinedVariable
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.LHS_Node
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.PairElemNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class ReadStatNode(private val lhs: LHS_Node, override val ctx: BasicParser.ReadContext): Node {

    override fun getType() : LitTypes {
        return lhs.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (lhs.getType() == LitTypes.IdentWacc) {

            var value = table.lookupSymbol(lhs.id)

            if (value == null) {
                errors.addError(UndefinedVariable(ctx, lhs.id))
            } else {
                while (value != null && value.getType() == LitTypes.IdentWacc) {
                    if (value is PairElemNode) {
                        value = value.expr
                    }
                    value = table.lookupSymbol((value as IdentNode).id)
                }
                if (value == null) {
                    errors.addError(UndefinedVariable(ctx, lhs.id))
                } else if (value.getType() != LitTypes.CharWacc && value.getType() != LitTypes.IntWacc && lhs.Nodetype !is PairElemNode) {
                    errors.addError(IncompatibleTypes(ctx, "CHAR or INT", value, table))
                }
            }
        } else {
            if (lhs.getType() != LitTypes.CharWacc && lhs.getType() != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes(ctx, "CHAR or INT", lhs, table))
            }
        }
    }
}