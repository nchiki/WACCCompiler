package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode


class StringLitNode(str : String, override val ctx: BasicParser.StrLitContext) : ExprNode {

    override fun getType(): LitTypes {
       return LitTypes.StringWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
       //not needed for Literals
    }

}