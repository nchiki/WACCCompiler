package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode

class LHS_Node(val Nodetype: Any?, val id: String, val line: Int, val pos : Int,
               override val ctx: BasicParser.AssignLHSContext) : ExprNode {

    override val size: Int
        get() = 4
    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        if (getBaseType() == LitTypes.IdentWacc) {
            Nodetype as IdentNode
            Nodetype.generateCode(codeGenerator)
        }
        if (Nodetype is ArrayElemNode) {
            Nodetype.generateCode(codeGenerator)
        }
        if (Nodetype is PairElemNode) {
            Nodetype.generateCode(codeGenerator)
        }
    }

    override fun getBaseType(): LitTypes {
        if (Nodetype is ArrayElemNode) {
            return Nodetype.getBaseType()
        } else if (Nodetype is PairElemNode) {
            return Nodetype.getBaseType()
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

}
