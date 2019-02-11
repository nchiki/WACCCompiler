package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import main.kotlin.Errors.UnknownIdentifier


class BoolLitNode(val bool_val : String, override val ctx: BasicParser.BoolLitContext) : ExprNode {

    override fun getType() : LitTypes {
        return LitTypes.BoolWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (!bool_val.equals("true") && !bool_val.equals("false")) {
            errors.addError(UnknownIdentifier(ctx.start.line, ctx.start.charPositionInLine))
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
