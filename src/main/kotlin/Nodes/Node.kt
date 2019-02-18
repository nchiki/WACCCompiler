package main.kotlin.Nodes

import main.kotlin.CodeGeneration
import main.kotlin.SymbolTable
import main.kotlin.ErrorLogger
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier

interface Node {

    // number of registers used in the node when translating it
    val weight : Int

    val ctx : ParserRuleContext?

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)

    fun generateCode(codeGeneration: CodeGeneration)


}