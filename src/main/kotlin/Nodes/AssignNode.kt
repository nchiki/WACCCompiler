package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import kotlin.reflect.KClass

class AssignNode(val LHS_Node: LHS_Node, val RHS_Node: RHS_Node, val ctx : BasicParser.AssignContext) : Node {


    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (RHS_Node.type == RHS_type.call) {
            if(RHS_Node.funId != null) {
                val Func = table.lookupSymbol(RHS_Node.funId) as FunctionNode
                val returnT = Func.getType()
                if (returnT != LHS_Node.getType()) {
                    errors.addError(IncompatibleTypes(ctx.start.line, ctx.start.charPositionInLine))
                }
            }
            LHS_Node.semanticCheck(errors,table)
            RHS_Node.semanticCheck(errors, table)

        }
    }
}