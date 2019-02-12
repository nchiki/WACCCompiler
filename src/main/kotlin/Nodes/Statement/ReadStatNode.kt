package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.LHS_Node
import main.kotlin.Nodes.Node
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
        if (lhs.Nodetype != LitTypes.CharWacc && lhs.Nodetype != LitTypes.IntWacc) {
            errors.addError(IncompatibleTypes(ctx, "{CHAR, INT}", lhs, table))
        }
    }
}