package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.SymbolTable
import main.kotlin.ErrorLogger
import org.antlr.v4.runtime.ParserRuleContext

interface Node {
    val ctx : ParserRuleContext?
    val weight : Int

    var symbolTable: SymbolTable?

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)

    fun generateCode(codeGenerator : CodeGenerator)

}
