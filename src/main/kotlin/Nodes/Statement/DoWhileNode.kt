package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.BranchInstr
import main.kotlin.Instructions.CmpInstr
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess


class DoWhileNode(val stat: Node, val expr : ExprNode, override val ctx: BasicParser.DoWhileContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = expr.weight + 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.getNewLabel()

        val oldScope = codeGenerator.curScope
        val endLabel = codeGenerator.getNewLabel()

        codeGenerator.addLabel(label, null)

        codeGenerator.curLabel = label

        stat.generateCode(codeGenerator)
        /* Evaluate the expression */
        expr.generateCode(codeGenerator)

        val reg = codeGenerator.getLastUsedReg()
        codeGenerator.addInstruction(label, CmpInstr(reg, 1, ""))
        codeGenerator.addInstruction(label, BranchInstr(endLabel, Condition.NE))
        if(reg != Register.r0) {
            codeGenerator.freeReg(reg)
        }
        codeGenerator.addLabel(endLabel, oldScope)
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(label, Condition.AL))

        codeGenerator.curLabel = endLabel
        codeGenerator.curScope = oldScope

        /* Restore stack pointer here */
        symbolTable!!.recoverSp(codeGenerator)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = SymbolTable(table)

        expr.semanticCheck(errors, table)
        stat.semanticCheck(errors, this.symbolTable!!)

        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        if (expr.getBaseType() == LitTypes.IdentWacc) {
            val value = table.lookupSymbol((expr as IdentNode).id)
            if (value == null) {
                errors.addError(UndefinedVariable(ctx.expr(), (expr).id))
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
    }

}