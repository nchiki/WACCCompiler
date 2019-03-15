package main.kotlin.Nodes.Literals

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.MovInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode

class BoolLitNode(val bool_val: String, override val ctx: BasicParser.BoolLitContext?) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 1

    override val weight: Int
        get() = 1

    override fun generateCode(codeGenerator: CodeGenerator) {
        //add instructions to main
        val reg = codeGenerator.getFreeRegister()
        //add instructions to label
        if (!bool_val.toBoolean()) {
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg, "#0"))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(reg, "#1"))
        }
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.BoolWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

}
