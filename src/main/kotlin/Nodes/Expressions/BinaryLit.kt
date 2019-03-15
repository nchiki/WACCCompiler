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
import src.main.kotlin.Nodes.Literals.IntLitNode

class BinaryLit(val sequence: String, override val ctx: ParserRuleContext) : ExprNode {

    override fun optimise(valueTable: ValueTable): Node {
        return IntLitNode(convertToInt().toLong(), null)
    }

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = 1

    fun convertToInt(): Int {
        var result = 0
        for (letter in sequence.substring(3).reversed()) {
            result *= 2
            if (letter.equals('1')) {
                result += 1
            }
        }
        return result
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