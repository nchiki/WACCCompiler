package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BaseNode(val type: String, override val ctx: ParserRuleContext?) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() {
            return when (type) {
                "int" -> 4
                "char" -> 1
                "bool" -> 1
                "string" -> 4
                "pair" -> 4
                else -> 0
            }
        }

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {}

    override fun getBaseType(): LitTypes {

        return when (type) {
            "int" -> LitTypes.IntWacc
            "char" -> LitTypes.CharWacc
            "bool" -> LitTypes.BoolWacc
            "string" -> LitTypes.StringWacc
            "pair" -> LitTypes.PairWacc
            else -> LitTypes.NonLitWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

}
