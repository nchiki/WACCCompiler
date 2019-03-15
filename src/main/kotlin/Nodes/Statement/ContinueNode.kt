package main.kotlin.Nodes.Statement

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.NotInLoop
import main.kotlin.Instructions.BranchInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.ValueTable

class ContinueNode(override val ctx: BasicParser.ContinueContext) : Node {
    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 0

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (!table.inLoop) {
            errors.addError(NotInLoop(ctx))
        }
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(codeGenerator.loopLabel, Condition.AL))
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }
}