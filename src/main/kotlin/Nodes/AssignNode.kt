package main.kotlin.Nodes

import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode

class AssignNode(val LHS_Node: LHS_Node, val RHS_Node: RHS_Node, override val ctx : BasicParser.AssignContext) : Node {


    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        LHS_Node.semanticCheck(errors, table)
        RHS_Node.semanticCheck(errors, table)


        /* Attempting to assign to a pair */
        if (LHS_Node.Nodetype is PairElemNode) {
            val elem = LHS_Node.Nodetype.elem
            println(LHS_Node.id)
            val node = (table.lookupSymbol(LHS_Node.id) as PairNode).returnElemNode(elem)
            if (RHS_Node.getType() == LitTypes.IdentWacc) {
                if (node != RHS_Node.returnIdentType(table)) {

                    errors.addError(IncompatibleTypes(ctx, node.toString(), RHS_Node, table))
                }
            } else if (node != RHS_Node.getType()) {

                errors.addError(IncompatibleTypes(ctx, node.toString(), RHS_Node, table))
            }
            return
        }

        val node = table.lookupSymbol(LHS_Node.id)

        if (node == null) {
            errors.addError(UndefinedVariable(ctx, LHS_Node.id))
            return
        }

        /* Types match */
        if (node.getType() == RHS_Node.getType()) {
            return
        }

        if (LHS_Node.Nodetype is ArrayElemNode && node.getType() == LitTypes.StringWacc &&
                RHS_Node.getType() == LitTypes.CharWacc) {
            // ITS FINE
            return
        }

        val idType = RHS_Node.returnIdentType(table)
        if(idType != null){

            if(idType == node.getType()){
                return
            }

            errors.addError(IncompatibleTypes(ctx, idType.toString(), node, table))
            return
        }

        errors.addError(IncompatibleTypes(ctx, node.getType().toString(), RHS_Node, table))

    }
}