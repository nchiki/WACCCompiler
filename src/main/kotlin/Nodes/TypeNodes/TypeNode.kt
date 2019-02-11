package main.kotlin.Nodes.TypeNodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


interface TypeNode : Node {

    fun getType() : LitTypes
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

    }
}