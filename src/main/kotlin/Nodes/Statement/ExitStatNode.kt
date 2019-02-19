package main.kotlin.Nodes.Statement

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.system.exitProcess

class ExitStatNode(val expr : ExprNode, override val ctx : BasicParser.ExitContext) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {

        if (expr is IntLitNode) {
            codeGeneration.loadToReg(expr.int_val, Register.r4)
        } else if (expr is IdentNode) {
            expr.generateCode(codeGeneration)
            // SUB sp, sp, #4
            // LDR r4, ={expr.int_val}
            // STR r4, [sp]
            // LDR r4, [sp]
        }
        // MOV r0, r4
        // BL exit
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn){
            exitProcess(100)
        }

        table.currentExecutionPathHasReturn = true

        if (expr.getBaseType() != LitTypes.IntWacc) {
            if (expr.getBaseType() == LitTypes.IdentWacc) {
                val idexpr = expr as IdentNode
                val value = table.lookupSymbol(expr.id)
                if (value?.getBaseType() != LitTypes.IntWacc) {


                    errors.addError(IncompatibleTypes(ctx, "INT", value!!, table))
                }
            } else {
                errors.addError(IncompatibleTypes(ctx, "INT", expr, table))
            }
        }
        expr.semanticCheck(errors, table)
    }
}
