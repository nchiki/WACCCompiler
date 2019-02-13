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

class ReadStatNode(val lhs: LHS_Node, override val ctx: BasicParser.ReadContext): Node {

    override fun getType() : LitTypes {
        return lhs.getType()
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (lhs.getType() == LitTypes.IdentWacc) {
            val node = lhs
            val value = table.lookupSymbol((node).id)
            if (value == null) {
                errors.addError(UndefinedVariable(ctx, node.id))
                return
            } else {
                var v = value
                while (v != null && v?.getType() == LitTypes.IdentWacc) {
                    if (v is PairElemNode) {
                        v = v.expr
                    }
                    v = table.lookupSymbol((v as IdentNode).id)
                }
                if (v == null) {
                    errors.addError(UndefinedVariable(ctx, (lhs).id))
                    return
                }

                if(v.getType() != LitTypes.CharWacc && v.getType() != LitTypes.IntWacc && lhs.Nodetype !is PairElemNode) {
                    errors.addError(IncompatibleTypes(ctx, "CHAR or INT", lhs, table))
                }
            }
        } else {
            if (lhs.getType() != LitTypes.CharWacc && lhs.getType() != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes(ctx, "CHAR or INT", lhs, table))
            }
        }
    }
}