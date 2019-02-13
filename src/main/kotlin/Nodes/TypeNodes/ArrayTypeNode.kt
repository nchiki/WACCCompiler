package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class ArrayTypeNode(override val ctx: BasicParser.ArrayTypeContext, val type: TypeNode) : TypeNode {

    override fun getType() : LitTypes {
        return type.getType()
    }

    override fun syntaxCheck() {
        //not needed
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }
}