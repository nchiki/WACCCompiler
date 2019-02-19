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


class IfCondNode(// condition (should evaluate to boolean val
        val expr: ExprNode?, // expr = true -> statement
        val ifTrueStat: Node?, // expr = false -> statement

        val elseStat: Node?, override val ctx: BasicParser.IfCondContext) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        expr!!.generateCode(codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(codeGenerator.regsNotInUse.get(0), "#0"))
        codeGenerator.addLabel("L0")
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr("L0", Condition.EQ))
        codeGenerator.addLabel("L1")
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr("L1", Condition.NULL))
        codeGenerator.regsNotInUse.remove(codeGenerator.regsNotInUse.get(0))
        codeGenerator.curLabel = "LO"
        ifTrueStat!!.generateCode(codeGenerator)
        codeGenerator.addInstruction("L0", LoadInstr(Register.r0, 0))
        codeGenerator.addInstruction("L0", PopInstr())
        codeGenerator.curLabel = "L1"
        elseStat!!.generateCode(codeGenerator)
        codeGenerator.addInstruction("L1", LoadInstr(Register.r0, 0))
        codeGenerator.addInstruction("L1", PopInstr())

    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        // check whether the expr evaluates to boolean value

        //table.boolExprCheck(expr!!, errors, table, ctx)
        if(expr?.getBaseType() != LitTypes.BoolWacc) {
            errors.addError(IncompatibleTypes(ctx.expr(), "BOOL", expr!!, table))
        }
        //checks both statements
        ifTrueStat?.semanticCheck(errors, table)
        elseStat?.semanticCheck(errors, table)

    }

}
