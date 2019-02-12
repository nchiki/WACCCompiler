package main.kotlin.Nodes

import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class AssignNode(val LHS_Node: LHS_Node, val RHS_Node: RHS_Node, override val ctx : BasicParser.AssignContext) : Node {


    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(LHS_Node.Nodetype is PairElemNode) {
            val elem = LHS_Node.Nodetype.elem
            val node = (table.lookupSymbol(LHS_Node.id) as NewPairNode).returnElemNode(elem)
            if (node.getType() != RHS_Node.getType()) {
                errors.addError(IncompatibleTypes(ctx, node.getType().toString(), RHS_Node, table))
            }
        } else {
            val node = table.lookupSymbol(LHS_Node.id)
            if (node == null) {
                errors.addError(UndefinedVariable(ctx, LHS_Node.id))
            } else if (node.getType() != RHS_Node.getType()) {
                errors.addError(IncompatibleTypes(ctx, node.getType().toString(), RHS_Node, table))
            }
            if (RHS_Node.type == RHS_type.call) {
                if (RHS_Node.funId != null) {
                    val Func = table.lookupSymbol(RHS_Node.funId) as FunctionNode
                    val returnT = Func.getType()
                    if (returnT != LHS_Node.getType()) {
                        if (LHS_Node.getType() == LitTypes.IdentWacc) {
                            val idLHS = LHS_Node as IdentNode
                            if (table.lookupSymbol(idLHS.id) == null) {
                                errors.addError(IncompatibleTypes(ctx, LHS_Node.getType().toString(), Func, table))
                            }
                        }
                        errors.addError(IncompatibleTypes(ctx, LHS_Node.getType().toString(), Func, table))
                    }
                }
            }
        }
        LHS_Node.semanticCheck(errors,table)
        RHS_Node.semanticCheck(errors, table)
    }
}