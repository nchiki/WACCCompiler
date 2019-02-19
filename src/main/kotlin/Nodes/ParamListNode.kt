package Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class ParamListNode(// list of parameterNodes
        val listParamNodes: MutableList<ParamNode>, override val ctx: BasicParser.ParamListContext?) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        // iterates through the list of parameters, checking semantics of each of them
        for (param in listParamNodes) {
            param.semanticCheck(errors, table)
        }
    }

}
