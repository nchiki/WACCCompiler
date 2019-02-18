package main.kotlin.Nodes.Statement

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class StatListNode(val listStatNodes: MutableList<Node>, override val ctx: BasicParser.StatListContext) : Node{

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        for (stat in listStatNodes) {
            stat.semanticCheck(errors, table)
        }
    }
}
