package Nodes

import Errors.VarAlreadyDeclaredError
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes



class DeclNode(// var name
        val id: String, // type of var
        val type: TypeNode, // assigned rhs
        val rhs: RHS_Node, val ctx : BasicParser.DeclContext) : Node {

    fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

            // looks up the id in the symbol table
            val value = table.lookupSymbol(id)

            //if it's not there or there is a function with the same name, don't add an error
            if (value != null && (value !is FunctionNode)) {
                // if there is already a variable with that name -> error
                errors.addError(VarAlreadyDeclaredError(ctx.start.line, ctx.start.charPositionInLine))
            }

            if (false) {
                errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
            }

            // call semantic check on the rest of elements
            //type.semanticCheck(errors, table)
            rhs.semanticCheck(errors, table)
        }



}


