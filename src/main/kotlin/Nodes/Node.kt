package main.kotlin.Nodes

import main.kotlin.SymbolTable
import main.kotlin.ErrorLogger
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier

interface Node {
    //add method signatures

    fun syntaxCheck()

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)

    fun getType() : KClassifier
}