package main.kotlin.Nodes.Expression

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ParenNode(val expr: Node, override val ctx: BasicParser.ParenContext): ExprNode {

    override fun getType(): LitTypes {
        return expr.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
<<<<<<< HEAD
=======

    override fun syntaxCheck() {
        //not needed for parentheses
    }
>>>>>>> a1bf963b58d818fc65cae007b5efc1aaf2a27cb8
}