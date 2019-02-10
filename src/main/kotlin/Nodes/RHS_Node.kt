package main.kotlin.Nodes


import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.IncorrectNumParams
import main.kotlin.Nodes.Statement.ArgListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class RHS_Node(val type: RHS_type, val funId: String?, val args: ArgListNode?, val line: Int, val pos: Int,
               val expr: ExprNode, val expr1: Any?) : Node {


    fun getType(): LitTypes {
        if(type == RHS_type.expr) {
            return expr.type as LitTypes
        } else if (type == RHS_type.array_lit) {
            return
        } else if (type == RHS_type.call) {

        } else if(type == RHS_type.newpair) {

        } else if (type == RHS_type.pair_elem) {

        } else {

        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(type == RHS_type.call) {
            val funNode = table.lookupSymbol(funId!!) as FunctionNode
            val parameters = funNode.params
            if(args != null) {
                if (parameters.listParamNodes.count() != args.exprs.count()) {
                    errors.addError(IncorrectNumParams(line, pos))
                } else {
                    for (i in args.exprs.indices) {
                        val actual = args.exprs[i]
                        val expected = parameters.listParamNodes[i]
                        if (actual.type != expected.getType()) {
                            errors.addError(IncompatibleTypes(line, pos))
                        }
                    }
                }
            }
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

enum class RHS_type(s: String) {
    generic("generic"),
    expr("expr"),
    array_lit("array"),
    newpair("newpair"),
    pair_elem("pair"),
    call("call")
}
