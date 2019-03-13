package main.kotlin.Nodes.Statement

import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.system.exitProcess

class AssignNode(val LHSNode: LHSNode, var RHSNode: RHSNode, override val ctx : BasicParser.AssignContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 0

    override fun generateCode(codeGenerator: CodeGenerator) {
        // Call generateCode on RHS and LHS
        RHSNode.generateCode(codeGenerator)
        LHSNode.generateCode(codeGenerator)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        LHSNode.semanticCheck(errors, table)
        RHSNode.semanticCheck(errors, table)

        /* Attempting to assign to a pair */
        if (LHSNode.nodeType is PairElemNode) {
            val elem = LHSNode.nodeType.elem
            val node = (table.lookupSymbol(LHSNode.id) as PairNode).returnElemNode(elem)
            if (RHSNode.getBaseType() == LitTypes.IdentWacc) {
                if (node != RHSNode.returnIdentType(table)) {

                    errors.addError(IncompatibleTypes(ctx, node.toString(), RHSNode, table))
                }
            } else if (node != RHSNode.getBaseType()) {

                errors.addError(IncompatibleTypes(ctx, node.toString(), RHSNode, table))
            }
            return
        }

        val node = table.lookupSymbol(LHSNode.id)

        if (node == null) {
            errors.addError(UndefinedVariable(ctx, LHSNode.id))
            return
        }

        /* Types match */
        if (node.getBaseType() == RHSNode.getBaseType()) {
            return
        }

        if (LHSNode.nodeType is ArrayElemNode && node.getBaseType() == LitTypes.StringWacc &&
                RHSNode.getBaseType() == LitTypes.CharWacc) {
            return
        }

        val idType = RHSNode.returnIdentType(table)
        if(idType != null){

            if(idType == node.getBaseType()){
                return
            }

            errors.addError(IncompatibleTypes(ctx, idType.toString(), node, table))
            return
        }

        errors.addError(IncompatibleTypes(ctx, node.getBaseType().toString(), RHSNode, table))

    }

    /* Optimises the RHS and then checks if it reduces to an INT or BOOL constant */
    override fun optimise(valueTable: ValueTable): Node {
        RHSNode = RHSNode.optimise(valueTable)

        val lhsExpr = symbolTable!!.lookupSymbol(LHSNode.id)

        if(!lhsExpr!!.getBaseType().equals(LitTypes.IntWacc) && !lhsExpr.getBaseType().equals(LitTypes.BoolWacc)){
            return this
        }

        if(RHSNode.type.equals(RHS_type.expr)){
            val rhsExpr = RHSNode.expr!!
            if(rhsExpr is IntLitNode){
                valueTable.setIntValue(LHSNode.id, rhsExpr.int_val)
            }else if(rhsExpr is BoolLitNode){
                valueTable.setBoolValue(LHSNode.id, rhsExpr.bool_val.toBoolean())
            }else{
                valueTable.setDynamic(LHSNode.id, true)
            }
        }

        return this
    }
}
