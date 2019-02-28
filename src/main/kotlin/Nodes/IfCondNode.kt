package src.main.kotlin

import main.kotlin.Instructions.PopInstr
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Instructions.BranchInstr
import main.kotlin.Instructions.CmpInstr
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess


class IfCondNode(// condition (should evaluate to boolean val
        val expr: ExprNode?, // expr = true -> statement
        val ifTrueStat: Node?, // expr = false -> statement

        val elseStat: Node?, override val ctx: BasicParser.IfCondContext) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var symbolTable: SymbolTable? = null

    override fun generateCode(codeGenerator: CodeGenerator) {

        expr!!.generateCode(codeGenerator)

        val elseLabel = codeGenerator.getNewLabel()

        val endLabel = codeGenerator.getNewLabel()
        val oldScope = codeGenerator.curScope

        codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(codeGenerator.getLastUsedReg(), 1, ""))
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(elseLabel, Condition.NE))
        codeGenerator.freeReg(codeGenerator.getLastUsedReg())

        ifTrueStat!!.generateCode(codeGenerator)
        ifTrueStat.symbolTable?.recoverSp(codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(endLabel, Condition.NULL))

        codeGenerator.addLabel(elseLabel, null)

        codeGenerator.curScope = elseLabel
        codeGenerator.curLabel = elseLabel
        elseStat!!.generateCode(codeGenerator)
        elseStat.symbolTable?.recoverSp(codeGenerator)
        
        codeGenerator.addLabel(endLabel, oldScope)

        codeGenerator.curLabel = endLabel
        codeGenerator.curScope = oldScope
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }

        //table.boolExprCheck(expr!!, errors, table, ctx)
        if(expr?.getBaseType() != LitTypes.BoolWacc) {
            errors.addError(IncompatibleTypes(ctx.expr(), "BOOL", expr!!, table))
        }

        val ifChildTable = SymbolTable(table)
        val elseChildTable = SymbolTable(table)

        ifChildTable.currentFunction = table.currentFunction
        elseChildTable.currentFunction = table.currentFunction

        //checks both statements
        expr.semanticCheck(errors, table)
        ifTrueStat?.semanticCheck(errors, ifChildTable)
        elseStat?.semanticCheck(errors, elseChildTable)

        if(ifChildTable.currentExecutionPathHasReturn && elseChildTable.currentExecutionPathHasReturn){
            table.currentExecutionPathHasReturn = true
        }

    }

}
