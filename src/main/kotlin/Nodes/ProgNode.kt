package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

class ProgNode (funcDefs: List<FunctionNode>) : Node {

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        /* check if functions are already in the table and if not add them
            then create child symbol tables for those
            symbol table for program body
         */

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}