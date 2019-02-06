package Nodes

import Errors.VarAlreadyDeclaredError
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.FunctionNode
import main.kotlin.Nodes.Node
import SymbolTable

class ParamNode(id : String, type : Node) : Node {

    val id = id // name of param var
    val type = type // type of the parameter (Node)

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // looks up the id in the symbol table
        val Value = table.lookupSymbol(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (Value != null && !(Value is FunctionNode)) {
            // if there is already a variable with that name -> error
            errors.addError(VarAlreadyDeclaredError())
        }

        type.semanticCheck(errors, table) // checks semantics on the type (valid type)
    }
}