package main.kotlin.Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class ArrayTypeNode(override val ctx: BasicParser.ArrayTypeContext, val type: ExprNode) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        TODO("Function need not be called")
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBaseType(): LitTypes {
        return type.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

    fun getDimensions(): Int{
        if(type is ArrayTypeNode){
            return 1 + type.getDimensions()
        }
        return 1
    }

    override fun equals(other: Any?): Boolean {
        if (other is ArrayTypeNode) {
            return type == other.type
        }
        return false
    }
}
