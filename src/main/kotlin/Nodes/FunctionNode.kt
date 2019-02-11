package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class FunctionNode (val id: String, val fun_type: LitTypes, val params: ParamListNode, val stat: ReturnStatNode,
                    val ctx:BasicParser.FuncContext) : Node {


    override fun getType() : LitTypes {
        return fun_type
    }

    override fun syntaxCheck() {
       /* if (stat.getType() != ReturnStatNode::class && stat.getType() != ExitStatNode::class) {
            exitProcess(100)
        }*/
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        stat.setFunctionReturn(fun_type)
        params.semanticCheck(errors, table)
        stat.semanticCheck(errors, table)
    }
}