package Nodes

import Errors.DoubleDeclare
import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class ParamNode(
        val id: String,
        val type: Node, override val ctx: BasicParser.ParamContext) : Node {


    override val weight =type.weight

    override fun generateCode() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType() : LitTypes{
        var v = type
        while (v is ArrayTypeNode) {
            v = v.type
        }
        val toType = v
        return toType.getType()
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
            errors.addError(DoubleDeclare(ctx, id, value.ctx!!.start.line))
        } else {
            table.add(this, id)
        }

    }
}