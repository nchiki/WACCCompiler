package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext

interface Node {
    val ctx: ParserRuleContext?
    val weight: Int

    var symbolTable: SymbolTable?

    fun semanticCheck(errors: ErrorLogger, table: SymbolTable)

    fun generateCode(codeGenerator: CodeGenerator)

    fun optimise(valueTable: ValueTable): Node
}
