package Nodes.PairType

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


open class PairElemTypeNode(val type: ExprNode?, val pair : String?, override val ctx: BasicParser.PairElemTypeContext) : ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }

    override fun getBaseType(): LitTypes {
        if (pair == "") {
            return type?.getBaseType()!!
        }

        return LitTypes.PairWacc
    }

}
