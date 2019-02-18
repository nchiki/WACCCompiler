package main.kotlin.Nodes.Statement

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class StatListNode(val listStatNodes: MutableList<Node>, override val ctx: BasicParser.StatListContext) : Node{

    override fun translate() {
        for (stat in listStatNodes) {
            stat.translate()
        }

    }

    override var weight = 0
    init {
       for (stat in listStatNodes) {
           weight += stat.weight
       }
    }

    override fun generateCode(codeGeneration: CodeGeneration) {
        for (stat in listStatNodes) {
            stat.generateCode(codeGeneration)
        }

    }

    override fun getType(): LitTypes {
        return LitTypes.NonLitWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
       for (stat in listStatNodes) {
           stat.semanticCheck(errors, table)
       }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
