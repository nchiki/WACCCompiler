package main.kotlin.Nodes.Literals

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class BoolLitNode(val bool_val : String, override val ctx: BasicParser.BoolLitContext) : ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBaseType() : LitTypes {
        return LitTypes.BoolWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //base types do not need to be checked
    }

}
