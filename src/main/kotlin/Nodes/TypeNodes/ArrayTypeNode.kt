package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class ArrayTypeNode(override val ctx: BasicParser.ArrayTypeContext, val type: TypeNode) : TypeNode {

    override fun getBaseType() : LitTypes {
        return type.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }

    override fun equals(other: Any?): Boolean {
        if(other is ArrayTypeNode){
            return type.equals(other.type)
        }
        return false
    }
}