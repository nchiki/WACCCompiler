package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import kotlin.Nodes.LHS_Node
import kotlin.Nodes.RHS_Node
import kotlin.Nodes.RHS_type
import kotlin.reflect.KClass

class AssignNode(LHS_Node : LHS_Node, RHS_Node : RHS_Node) : Node {

    val LHS_Node = LHS_Node
    val RHS_Node = RHS_Node


    override fun getType() : KClass<AssignNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (RHS_Node.type == RHS_type.call) {
            val Func  = table.lookupSymbol(RHS_Node.id) as FunctionNode
            val returnT = Func.type
            if( returnT != LHS_Node.type.toString()) {
                errors.addError(IncompatibleTypes())
            }
            LHS_Node.semanticCheck(errors,table)
            RHS_Node.semanticCheck(errors, table)

        }
    }
}