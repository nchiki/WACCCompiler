package main.kotlin.Nodes


import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.IncorrectNumParams
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Statement.ArgListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class RHS_Node(val type: RHS_type, val funId: String?, val args: ArgListNode?, val line: Int, val pos: Int,
               val expr: ExprNode?, val newPairNode: NewPairNode?, val PairLit: PairElemNode?, val ArrayLit: ArrayLitNode?) : Node {


    override fun getType(): LitTypes {
        if(type == RHS_type.expr) {
            return expr!!.getType()
        } else if (type == RHS_type.array_lit) {
            return ArrayLit!!.getType()
        } else if (type == RHS_type.call) {

            // NEEDS IMPLEMENTATION
            return LitTypes.NonLitWacc
        } else if(type == RHS_type.newpair) {
            return LitTypes.PairWacc
        } else if (type == RHS_type.pair_elem) {
            return PairLit!!.getType()
        } else {
            return LitTypes.NonLitWacc
        }
    }

    fun returnIdentType(table: SymbolTable) :LitTypes?{
        if(type == RHS_type.expr && expr!!.getType() == LitTypes.IdentWacc) {
            val exprId = expr as IdentNode
            val value = exprId.getValueType(table)?.getType()
            return value
        }  else if (type == RHS_type.pair_elem) {
            val pairVal = PairLit?.expr
            if(pairVal?.getType() == LitTypes.IdentWacc) {
                val exprId = pairVal as IdentNode
                val value = exprId.getValueType(table)?.getType()
                return value
            } else {
                return pairVal?.getType()
            }
        }
        return null
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
//        println(0)
        if(type == RHS_type.call) {
//            println(1)
            val funNode = table.lookupSymbol(funId!!) as FunctionNode
            val parameters = funNode.params
            if(args != null) {
//                println(2)
                if (parameters.listParamNodes.count() != args.exprs.count()) {
//                    println(3)
                    errors.addError(IncorrectNumParams(line, pos, parameters.listParamNodes.count(), args.exprs.count()))
                } else {
                    for (i in args.exprs.indices) {
                        val actual = args.exprs[i]
                        val expected = parameters.listParamNodes[i]
                        if (actual.getType() != expected.getType()) {
                            errors.addError(IncompatibleTypes(line, pos, expected.getType().toString(), actual, table))
                        }
                    }
                }
            }

        }

    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addToTable(table: SymbolTable, id:String) {
        if(type == RHS_type.call) {
            val funNode = table.lookupSymbol(funId!!) as FunctionNode?
            if(funNode != null) {
                val value = funNode.stat
                table.add(value, id)
            }
        } else if(type == RHS_type.expr) {

            table.add(expr!!, id)
        } else if (type == RHS_type.newpair) {
            table.add(newPairNode!!, id)
        } else if(type == RHS_type.pair_elem) {

            table.add(PairLit!!, id)
        } else if(type == RHS_type.array_lit) {
            table.add(ArrayLit!!, id)
        }

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
