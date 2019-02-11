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
        val rhs: RHS_Node, val ctx : BasicParser.DeclContext) : StatementNode  {


    override fun getType() : LitTypes {
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

            if (type.getType() != rhs.getType()) {

                if(rhs.getType() == LitTypes.IdentWacc) {
                   val value = rhs.returnIdentType(table)

                    if (value == null || value != type.getType()) {
                        errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
                    }
                } else {
                    errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
                }
            } else {
                rhs.semanticCheck(errors, table)
            }
            rhs.addToTable(table,id)
            // call semantic check on the rest of elements
            //type.semanticCheck(errors, tabl
        }



}


