package src.main.kotlin

import BasicParser
import Nodes.StatementNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Instructions.BranchInstr
import main.kotlin.Instructions.CmpInstr
import main.kotlin.Nodes.Expression.ParenNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class IfCondNode(// condition (should evaluate to boolean val
        var expr: ExprNode?, // expr = true -> statement
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

        // Check expression and add branch instruction
        codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(codeGenerator.getLastUsedReg(), 1, ""))
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(elseLabel, Condition.NE))
        codeGenerator.freeReg(codeGenerator.getLastUsedReg())

        //generate code for then-case
        ifTrueStat!!.generateCode(codeGenerator)
        ifTrueStat.symbolTable?.recoverSp(codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(endLabel))

        codeGenerator.addLabel(elseLabel, null)

        //generate code for else-case
        codeGenerator.curScope = elseLabel
        codeGenerator.curLabel = elseLabel
        elseStat!!.generateCode(codeGenerator)
        elseStat.symbolTable?.recoverSp(codeGenerator)

        codeGenerator.addLabel(endLabel, oldScope)

        codeGenerator.curLabel = endLabel
        codeGenerator.curScope = oldScope
    }

    override fun optimise(valueTable: ValueTable): Node {
        expr = expr!!.optimise(valueTable) as ExprNode

        valueTable.markAllAsDynamic()
        val ifTrueValueTable = ValueTable(valueTable)
        val elseValueTable = ValueTable(valueTable)

        /* Remove else statement deadcode if the statement is always going to evaluate to true */
        if(expr is BoolLitNode){
            /* If statement always evaluates to if(true) -> put ifTrueStat into a stat-block */
            if((expr as BoolLitNode).bool_val.toBoolean()){
                var trueStatement: Node = StatementNode(ifTrueStat!!, null)
                trueStatement.symbolTable = ifTrueStat.symbolTable
                trueStatement = trueStatement.optimise(ifTrueValueTable)
                return trueStatement
            }
            var elseStatement: Node = StatementNode(elseStat!!, null)
            elseStatement.symbolTable = elseStat.symbolTable
            elseStatement = elseStatement.optimise(elseValueTable)
            return elseStatement
        }

        ifTrueStat!!.optimise(ifTrueValueTable)
        elseStat!!.optimise(elseValueTable)

        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
            exitProcess(100)
        }

        var actExpr = expr
        while (actExpr is ParenNode) {
            actExpr = actExpr.expr
        }
        if (actExpr?.getBaseType() == LitTypes.IdentWacc) {
            val actType = table.lookupSymbol((actExpr as IdentNode).id)
            if (LitTypes.BoolWacc != actType!!.getBaseType()) {
                errors.addError(IncompatibleTypes(ctx.expr(), "BOOL", actExpr!!, table))
            }
        } else if (actExpr?.getBaseType() != LitTypes.BoolWacc) {
            errors.addError(IncompatibleTypes(ctx.expr(), "BOOL", actExpr!!, table))
        }

        val ifChildTable = SymbolTable(table)
        ifChildTable.inLoop = table.inLoop
        val elseChildTable = SymbolTable(table)
        elseChildTable.inLoop = table.inLoop

        ifChildTable.currentFunction = table.currentFunction
        elseChildTable.currentFunction = table.currentFunction

        //checks both statements
        expr!!.semanticCheck(errors, table)
        ifTrueStat?.semanticCheck(errors, ifChildTable)
        elseStat?.semanticCheck(errors, elseChildTable)

        if (ifChildTable.currentExecutionPathHasReturn && elseChildTable.currentExecutionPathHasReturn) {
            table.currentExecutionPathHasReturn = true
        }

    }

}
