package main.kotlin.Nodes.Statement

import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.LHS_Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class ReadStatNode(val lhs: LHS_Node, val ctx: BasicParser.ReadContext?) : StatementNode() {

    override fun getType() : LitTypes {
        return lhs.getType()
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //nothing to do here
    }
}