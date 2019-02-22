package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode


class BaseNode(val type : String, override val ctx: ParserRuleContext?) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {

    }

    override fun getBaseType() : LitTypes {
        when(type){
            "int"    -> return LitTypes.IntWacc
            "char"   -> return LitTypes.CharWacc
            "bool"   -> return LitTypes.BoolWacc
            "string" -> return LitTypes.StringWacc
            "pair"   -> return LitTypes.PairWacc
        }
        return LitTypes.NonLitWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

}
