package main.kotlin.Nodes.Expressions

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class OctalLit(val sequence : String, override val ctx: ParserRuleContext?) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = 1

    fun convertToInt() : Int{
        var octal = sequence.substring(1).toInt()
        var result = 0
        var i = 0.0

        while (octal != 0) {
            result = result + octal % 10 * Math.pow(8.0, i).toInt()
            i++
            octal = octal / 10
        }
        return result
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        //add instructions to main
        val reg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, convertToInt()))
    }

    override fun getBaseType(): LitTypes {
        return LitTypes.IntWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }
}