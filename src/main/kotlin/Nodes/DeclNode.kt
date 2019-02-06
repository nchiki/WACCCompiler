package Nodes

import Errors.VarAlreadyDeclaredError
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.FunctionNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable

class DeclNode(id: String, type: Node?, rhs: Node?) : Node {

    val id = id // var name
    val type = type // type of var
    val rhs = rhs // assigned rhs


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
        // call semantic check on the rest of elements
        type?.semanticCheck(errors, table)
        rhs?.semanticCheck(errors, table)
    }


}
