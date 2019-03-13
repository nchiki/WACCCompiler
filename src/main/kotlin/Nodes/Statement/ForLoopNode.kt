package main.kotlin.Nodes.Statement

import Nodes.DeclNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.WrongNode
import main.kotlin.Instructions.BranchInstr
import main.kotlin.Instructions.CmpInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class ForLoopNode(val cond: ForCondNode, val stat: Node, override val ctx: BasicParser.ForLoopContext): Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = cond.weight + 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.getNewLabel()
        codeGenerator.loopLabel = label

        val oldScope = codeGenerator.curScope
        val endLabel = codeGenerator.getNewLabel()
        codeGenerator.endLabel = endLabel

        /*codeGenerator.addLabel(label, null)

        codeGenerator.curLabel = label*/

        /* Evaluate the expression */
        //evaluates the condition fields
        cond.generateCode(codeGenerator)
        codeGenerator.addLabel(label, null)
        codeGenerator.curLabel = label
        cond.second.generateCode(codeGenerator)
        val reg = codeGenerator.getLastUsedReg()
        codeGenerator.addInstruction(label, CmpInstr(reg, 1, ""))
        codeGenerator.addInstruction(label, BranchInstr(endLabel, Condition.NE))
        if(reg != Register.r0) {
            codeGenerator.freeReg(reg)
        }
        cond.third.generateCode(codeGenerator)



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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = SymbolTable(table)
        this.symbolTable!!.inLoop = true
        cond.semanticCheck(errors, table)
        stat.semanticCheck(errors, this.symbolTable!!)

        if(table.currentExecutionPathHasReturn && table.currentFunction != null){
            exitProcess(100)
        }
        this.symbolTable!!.inLoop = false

    }

}

class ForCondNode(val first : Node, val second : ExprNode, val third: Node, override val ctx: ParserRuleContext) : Node {

    override var symbolTable: SymbolTable? = null
    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val weight: Int
        get() = first.weight + second.weight + third.weight

    override fun generateCode(codeGenerator: CodeGenerator) {
        // declares or assigns the variable only once
        first.generateCode(codeGenerator)

    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        //we would like to handle more cases such as:
        // - first could be empty if the variable has been already declared
        // - third can be empty if the variable is altered in the body of the for loop
        // - infinite loop?

        //checking the three different expressions of a loop cond
        if(first !is DeclNode && first !is AssignNode) {
            errors.addError(WrongNode(ctx, "Declaration or Assignment", first, table))
        } else {
            // semantic check of either AssignNode or DeclNode
            first.semanticCheck(errors, table)
        }

        if(second.getBaseType() != LitTypes.BoolWacc) {
            //expecting a boolean expression
            errors.addError(IncompatibleTypes(ctx,"BOOL", second, table))
        } else {
            second.semanticCheck(errors, table)
        }

        third.semanticCheck(errors, table)

    }


}
