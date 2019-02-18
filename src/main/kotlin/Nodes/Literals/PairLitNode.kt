package Nodes.Literals

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class PairLitNode(override val ctx : BasicParser.PairLitContext): ExprNode {

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for PairLitNode
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.PairWacc
    }

}