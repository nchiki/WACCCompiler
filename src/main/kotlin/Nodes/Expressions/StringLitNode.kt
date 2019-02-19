package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class StringLitNode(val str : String, override val ctx: BasicParser.StrLitContext) : ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val msg = codeGenerator.data.size
        codeGenerator.data.put("msg_$msg", str)
        codeGenerator.data.put("msg_${msg+1}", "%.*s\\0")
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.StringWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed for Literals
    }

}
