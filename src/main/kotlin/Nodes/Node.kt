package main.kotlin.Nodes

import main.kotlin.SymbolTable
import main.kotlin.ErrorLogger
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext

interface Node {
    val ctx : ParserRuleContext?

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)
}