package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class ArrayTypeNode(override val ctx: BasicParser.ArrayTypeContext, val type: ExprNode): ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBaseType() : LitTypes {
        return type.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

    }

    override fun equals(other: Any?): Boolean {
        if(other is ArrayTypeNode){
            return type.equals(other.type)
        }
        return false
    }
}
