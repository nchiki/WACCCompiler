package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class FunctionNode (val id: String, val type: LitTypes, val params: ParamListNode, val stat: ReturnStatNode,
                    override val ctx:BasicParser.FuncContext) : Node {


    fun getType() : BaseNode {
        TODO()
    }

    override fun syntaxCheck() {
       /* if (stat.getType() != ReturnStatNode::class && stat.getType() != ExitStatNode::class) {
            exitProcess(100)
        }*/
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        stat.setFunctionReturn(type)
        params.semanticCheck(errors, table)
        stat.semanticCheck(errors, table)
    }
}