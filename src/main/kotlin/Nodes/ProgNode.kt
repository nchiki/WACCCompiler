package main.kotlin.Nodes

import Nodes.FunctionNode
import Nodes.StatementNode
import main.kotlin.ErrorLogger
import SymbolTable

class ProgNode (val funcDefs: List<FunctionNode>, val stats : StatementNode) : Node {

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        /* check if functions are already in the table and if not add them
            then create child symbol tables for those
            symbol table for program body
         */

        for (func in funcDefs) {

        }


    }
}