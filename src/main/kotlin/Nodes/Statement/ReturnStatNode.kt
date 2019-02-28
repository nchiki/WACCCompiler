package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.MovInstr
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class ReturnStatNode (val expr : ExprNode, override val ctx: BasicParser.ReturnContext): Node {

    override var symbolTable: SymbolTable? = null

    val size: Int
        get() = expr.size

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        expr.generateCode(codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, codeGenerator.getLastUsedReg(), null))
        if(codeGenerator.getLastUsedReg() != Register.r0) {
            codeGenerator.freeReg(codeGenerator.getLastUsedReg())
        }
        codeGenerator.curScope = "main"
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        expr.semanticCheck(errors, table)

        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        var realExpr = expr
        if (expr is IdentNode){
            val exprVal = table.lookupSymbol(expr.id)
            if(exprVal == null){
                errors.addError(UndefinedVariable(ctx, expr.id))
                return
            }
            realExpr = exprVal
        }

        if(table.currentFunction == null){
            exitProcess(100)
        }

        if(realExpr.getBaseType() != table.currentFunction?.getBaseType()){
            errors.addError(IncompatibleTypes(ctx, table.currentFunction?.getBaseType().toString(), realExpr, table))
        }

        table.currentExecutionPathHasReturn = true
    }
}
