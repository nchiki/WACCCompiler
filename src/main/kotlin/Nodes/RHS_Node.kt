package kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.FunctionNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.Errors.IncorrectNumParams
import kotlin.reflect.KClassifier

class RHS_Node(type: RHS_type, funId: String?, params: Node?) : Node {

    val funId = funId
    val params = params as ArgListNode


    val type = type


    override fun getType(): KClassifier {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(type == RHS_type.call) {
            val funNode = table.lookupSymbol(funId!!) as FunctionNode
            val parameters = funNode.params
            if(parameters.listParamNodes.count() != params.listParamNodes.count()) {
                errors.addError(IncorrectNumParams())
            } else {
                for (i in params.listParamNodes.indices) {
                    val actual = params.listArgNodes[i]
                    val expected = parameters.listParamNodes[i]
                    if (actual.type != expected.type) {
                        errors.addError(IncompatibleTypes())
                    }
                }
            }
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

enum class RHS_type {
    generic,
    expr,
    array_lit,
    newpair,
    pair_elem,
    call
}
