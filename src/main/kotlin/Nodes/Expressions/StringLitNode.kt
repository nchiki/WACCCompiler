package main.kotlin.Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.StringLitDef
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode


class StringLitNode(val str: String, override val ctx: BasicParser.StrLitContext?) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = 1

    override fun generateCode(codeGenerator: CodeGenerator) {

        //entries for data
        val msg = "msg_${codeGenerator.data.size}"
        codeGenerator.data[msg] = StringLitDef(str)

        //instructions for main
        val reg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, msg))
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.StringWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

}
