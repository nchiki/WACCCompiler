package Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable

class ParamListNode(listParamNodes: MutableList<Node>, ctx: BasicParser.ParamListContext?) : Node {

    override fun getType() : BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val listParamNodes = listParamNodes // list of parameterNodes

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {



        // iterates through the list of parameters, checking semantics of each of them
        for (param in listParamNodes) {
            param.semanticCheck(errors, table)
        }
    }

}
