package main.kotlin.Nodes.Statement

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class WhileNode(val expr: ExprNode, val stat: Node, override val ctx: BasicParser.WhileContext): Node {


    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        val childTable = SymbolTable(table)
        if (expr.getBaseType() == LitTypes.IdentWacc) {
            val value = table.lookupSymbol((expr as IdentNode).id)
            if (value == null) {
                errors.addError(UndefinedVariable(ctx.expr(), (expr as IdentNode).id))
                return
            } else {
                if (value.getBaseType().equals(BaseNode("bool", null).getBaseType())) {
                    return
                }
            }
        }
        if (!expr.getBaseType().equals(BaseNode("bool", null).getBaseType())) {
            errors.addError(IncompatibleTypes(ctx, "BOOL", expr, table))
        }
        expr.semanticCheck(errors, table)
        stat.semanticCheck(errors, childTable)


    }

}
