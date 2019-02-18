package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode

class LHS_Node(val Nodetype: Any?, val id: String, val line: Int, val pos : Int, override val ctx: BasicParser.AssignLHSContext) : Node {


    override fun getType(): LitTypes {
        if (Nodetype is ArrayElemNode) {
            return Nodetype.getType()
        } else if (Nodetype is PairElemNode) {
            return Nodetype.getType()
        } else {
            return LitTypes.IdentWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val value = table.lookupSymbol(id)

        if( value == null || value is FunctionNode) {
            errors.addError(UndefinedVariable(ctx, id))
        }
    }


    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}
