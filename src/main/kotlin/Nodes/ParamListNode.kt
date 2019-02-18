package Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class ParamListNode(// list of parameterNodes
        val listParamNodes: MutableList<ParamNode>, override val ctx: BasicParser.ParamListContext?) : Node {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {



        // iterates through the list of parameters, checking semantics of each of them
        for (param in listParamNodes) {
            param.semanticCheck(errors, table)
        }
    }

}
