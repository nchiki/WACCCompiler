package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.SymbolTable

class FunctionNode (val id: String, val type: String, val params: ParamListNode, val stat: ReturnStatNode,
                    val ctx:BasicParser.FuncContext) : Node {


    fun getType() : BaseNode {
        TODO()
    }

    override fun syntaxCheck() {
       /* if (stat.getType() != ReturnStatNode::class && stat.getType() != ExitStatNode::class) {
            exitProcess(100)
        }*/
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        params.semanticCheck(errors, table)
        stat.semanticCheck(errors, table)
    }
}