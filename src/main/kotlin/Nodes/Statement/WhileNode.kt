package main.kotlin.Nodes.Statement

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.BranchInstr
import main.kotlin.Instructions.CmpInstr
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class WhileNode(var expr: ExprNode, var stat: Node, override val ctx: BasicParser.WhileContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = expr.weight

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.getNewLabel()
        codeGenerator.loopLabel = label

        val oldScope = codeGenerator.curScope
        val endLabel = codeGenerator.getNewLabel()
        codeGenerator.endLabel = endLabel
        codeGenerator.addLabel(label, null)

        codeGenerator.curLabel = label

        /* Evaluate the expression */
        expr.generateCode(codeGenerator)

        val reg = codeGenerator.getLastUsedReg()
        codeGenerator.addInstruction(label, CmpInstr(reg, 1, ""))
        codeGenerator.addInstruction(label, BranchInstr(endLabel, Condition.NE))
        if (reg != Register.r0) {
            codeGenerator.freeReg(reg)
        }

        stat.generateCode(codeGenerator)

        codeGenerator.addLabel(endLabel, oldScope)
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(label, Condition.AL))

        codeGenerator.curLabel = endLabel
        codeGenerator.curScope = oldScope

        /* Restore stack pointer here */
        symbolTable!!.recoverSp(codeGenerator)
        codeGenerator.endLabel = ""
    }

    override fun optimise(valueTable: ValueTable): Node {
        expr = expr.optimise(valueTable) as ExprNode

        /* If the expression always evaluates to false we skip the while */
        if (expr is BoolLitNode) {
            if (!(expr as BoolLitNode).bool_val.toBoolean()) {
                return SkipNode(null)
            }
        }

        valueTable.markAllAsDynamic()
        val childValueTable = ValueTable(valueTable)

        stat = stat.optimise(childValueTable)

        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        this.symbolTable = SymbolTable(table)
        this.symbolTable!!.inHighOrderFunction = table.inHighOrderFunction
        this.symbolTable!!.inLoop = true
        expr.semanticCheck(errors, table)
        stat.semanticCheck(errors, this.symbolTable!!)

        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
            exitProcess(100)
        }

        if (expr.getBaseType() == LitTypes.IdentWacc) {
            val value = table.lookupSymbol((expr as IdentNode).id)
            if (value == null) {
                if (symbolTable!!.getFunction((expr as IdentNode).id) != null && symbolTable!!.inHighOrderFunction.first) {
                    return
                }
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
        this.symbolTable!!.inLoop = false
    }

}
