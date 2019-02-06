package main.kotlin.Nodes

import Nodes.Node
import SymbolTable

class ProgNode : Node {
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