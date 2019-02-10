package main.kotlin.Nodes

import Nodes.ParamListNode
import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Statement.ExitStatNode
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.SymbolTable
import kotlin.reflect.KClass
import kotlin.system.exitProcess

class FunctionNode (val id : String, val type : String, val params : ParamListNode, val stat : StatementNode) : Node {


    override fun getType() : BaseNode {
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