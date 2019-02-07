package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

interface Node {
    //add method signatures

    fun syntaxCheck()

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)
}