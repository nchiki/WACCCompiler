package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Instructions.SubInstr
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.system.exitProcess
import main.kotlin.Instructions.BLInstr
class ExitStatNode(val expr : ExprNode, override val ctx : BasicParser.ExitContext) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        expr.generateCode(codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r4, Register.r0, null))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("exit"))
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
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
