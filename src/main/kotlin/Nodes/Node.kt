package Nodes

import src.ErrorLogger
import src.SymbolTable


interface Node {
    //add method signatures

    fun syntaxCheck()

    fun semanticCheck(errors : ErrorLogger, table : SymbolTable)
}