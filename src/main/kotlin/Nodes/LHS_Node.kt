package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.NonExistingVar
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode


class LHS_Node(val Nodetype: Node, val id: String, val line: Int, val pos : Int) : Node {


    fun getType(): LitTypes {
        if (Nodetype is ArrayElemNode) {
            return Nodetype.type as LitTypes
        } else if (Nodetype is PairElemNode) {
            return Nodetype.getType()
        } else {
            return LitTypes.IdentWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val value = table.lookupSymbol(id)
        if( value == null || value is FunctionNode) {
            errors.addError(NonExistingVar(line, pos))
        }
    }


    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
