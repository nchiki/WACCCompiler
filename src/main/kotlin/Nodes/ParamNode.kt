package Nodes

import Errors.VarAlreadyDeclaredError
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.FunctionNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class ParamNode(// name of param var
        val id: String, // type of the parameter (Node)
        val type: Node, val ctx: BasicParser.ParamContext) : Node {
    override fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // looks up the id in the symbol table
        val Value = table.lookupSymbol(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (Value != null && !(Value is FunctionNode)) {
            // if there is already a variable with that name -> error
            errors.addError(VarAlreadyDeclaredError(ctx.start.line, ctx.start.charPositionInLine))
        }

        type.semanticCheck(errors, table) // checks semantics on the type (valid type)
    }
}