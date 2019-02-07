package main.kotlin.Nodes

import main.kotlin.SymbolTable
import main.kotlin.ErrorLogger

interface Node {
    //add method signatures

    fun syntaxCheck()

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)
}