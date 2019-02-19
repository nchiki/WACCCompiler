package main.kotlin.Nodes.Statement

import Nodes.PairType.PairNode
import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Nodes.LHS_Node
import main.kotlin.Nodes.Node
import main.kotlin.Nodes.PairElemNode
import main.kotlin.Nodes.RHS_Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode
import kotlin.system.exitProcess

class AssignNode(val LHS_Node: LHS_Node, val RHS_Node: RHS_Node, override val ctx : BasicParser.AssignContext) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn){
            exitProcess(100)
        }

        LHS_Node.semanticCheck(errors, table)
        RHS_Node.semanticCheck(errors, table)


        /* Attempting to assign to a pair */
        if (LHS_Node.Nodetype is PairElemNode) {
            val elem = LHS_Node.Nodetype.elem
            println(LHS_Node.id)
            val node = (table.lookupSymbol(LHS_Node.id) as PairNode).returnElemNode(elem)
            if (RHS_Node.getBaseType() == LitTypes.IdentWacc) {
                if (node != RHS_Node.returnIdentType(table)) {

                    errors.addError(IncompatibleTypes(ctx, node.toString(), RHS_Node, table))
                }
            } else if (node != RHS_Node.getBaseType()) {

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
        if (node.getBaseType() == RHS_Node.getBaseType()) {
            return
        }

        if (LHS_Node.Nodetype is ArrayElemNode && node.getBaseType() == LitTypes.StringWacc &&
                RHS_Node.getBaseType() == LitTypes.CharWacc) {
            return
        }

        val idType = RHS_Node.returnIdentType(table)
        if(idType != null){

            if(idType == node.getBaseType()){
                return
            }

            errors.addError(IncompatibleTypes(ctx, idType.toString(), node, table))
            return
        }

        errors.addError(IncompatibleTypes(ctx, node.getBaseType().toString(), RHS_Node, table))

    }
}
