package main.kotlin.Nodes


import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.IncorrectNumParams
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Statement.ArgListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode

class RHS_Node(val type: RHS_type, val funId: String?, val args: ArgListNode?, val line: Int, val pos: Int,
               val expr: ExprNode?, val newPairNode: NewPairNode?, val PairLit: PairElemNode?, val ArrayLit: ArrayLitNode?, override val ctx: BasicParser.AssignRHSContext) : Node {

    override fun getType(): LitTypes {
        when(type){
            RHS_type.expr -> return expr!!.getType()
            RHS_type.array_lit -> return ArrayLit!!.getType()
            RHS_type.call -> return LitTypes.FuncWacc
            RHS_type.newpair -> return LitTypes.PairWacc
            RHS_type.pair_elem -> return PairLit!!.getType()
        }
        return LitTypes.NonLitWacc
    }

    fun returnIdentType(table: SymbolTable) :LitTypes?{
        if(type == RHS_type.expr) {
            if (expr!!.getType() == LitTypes.IdentWacc) {
                if(expr is ArrayElemNode) {
                    return table.lookupSymbol(expr.identifier)?.getType()
                }
            val exprId = expr as IdentNode
            val value = exprId.getValueType(table)?.getType()
            return value
            } else {
                return expr.getType()
            }
        }  else if (type == RHS_type.pair_elem) {
            val pairVal = PairLit?.expr
            if (pairVal?.getType() == LitTypes.IdentWacc) {
                val exprId = pairVal as IdentNode
                val value = exprId.getValueType(table)
                if (value is PairNode) {
                    return(value.returnElemNode(PairLit!!.elem))
                } else {
                    return pairVal?.getType()
                }

            }
        } else if(type == RHS_type.call) {
            val value = table.getFunction(funId!!)!!.getType()

            return value
        }
        return null
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(type == RHS_type.call) {
            val funNode = table.getFunction(funId!!)
            val parameters = funNode!!.params
            if (args != null) {
                if (parameters!!.listParamNodes.count() != args.exprs.count()) {
                    errors.addError(IncorrectNumParams(ctx, parameters.listParamNodes.count(), args.exprs.count()))
                } else {
                    for (i in 0..args.exprs.size - 1) {
                        val actual = args.exprs[i]
                        val expected = parameters.listParamNodes[i]
                        if (actual.getType() == LitTypes.IdentWacc) {
                            val actIdent = actual as IdentNode
                            val actType = table.lookupSymbol(actual.id)
                            if (expected.getType() != actType!!.getType()) {
                                errors.addError(IncompatibleTypes(ctx, expected.getType().toString(), actual, table))
                            }
                        } else if (actual.getType() != expected.getType()) {
                            errors.addError(IncompatibleTypes(ctx, expected.getType().toString(), actual, table))
                        }
                    }
                }
            } else {
                errors.addError(IncorrectNumParams(ctx, parameters!!.listParamNodes.count(), 0))
            }


        } else if(type == RHS_type.expr) {
            expr!!.semanticCheck(errors, table)
        } else if (type == RHS_type.array_lit) {
            ArrayLit!!.semanticCheck(errors, table)
        } else if (type == RHS_type.pair_elem) {
            PairLit!!.semanticCheck(errors,table)
        }

    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*fun addToTable(table: SymbolTable, id:String) {
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

    }*/

}

enum class RHS_type(s: String) {
    generic("generic"),
    expr("expr"),
    array_lit("array"),
    newpair("newpair"),
    pair_elem("pair"),
    call("call")
}
