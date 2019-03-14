package main.kotlin.Nodes.Statement

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.NotInLoop
import main.kotlin.Instructions.BranchInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.ValueTable

class BreakNode(override val ctx: BasicParser.BreakContext) : Node {

    override val weight: Int
        get() = 0

    override var symbolTable: SymbolTable? = null


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(!table.inLoop) {
            errors.addError(NotInLoop(ctx))
        }
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(codeGenerator.endLabel, Condition.AL))
    }
}