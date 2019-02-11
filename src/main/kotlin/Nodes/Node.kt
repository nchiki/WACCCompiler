package main.kotlin.Nodes

import main.kotlin.SymbolTable
import main.kotlin.ErrorLogger
import main.kotlin.Utils.LitTypes
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier

interface Node {

    //add method signatures
    fun syntaxCheck()

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)

}