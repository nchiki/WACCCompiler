package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode



class CharLitNode(char : String, override val ctx: BasicParser.CharLitContext) : ExprNode {

    override fun getType(): LitTypes {
        return LitTypes.CharWacc
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not implemented yet
    }
}