package main.kotlin.Nodes


import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.IncorrectNumParams
import main.kotlin.Nodes.Expressions.BinaryOpNode
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Statement.ArgListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode

class RHS_Node(val type: RHS_type, val funId: String?, val args: ArgListNode?, val line: Int, val pos: Int,
               val expr: ExprNode?, val newPairNode: NewPairNode?, val PairLit: PairElemNode?, val ArrayLit: ArrayLitNode?, override val ctx: BasicParser.AssignRHSContext) : ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator : CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBaseType(): LitTypes {
        when(type){
            RHS_type.expr -> return expr!!.getBaseType()
            RHS_type.array_lit -> return ArrayLit!!.getBaseType()
            RHS_type.call -> return LitTypes.FuncWacc
            RHS_type.newpair -> return LitTypes.PairWacc
            RHS_type.pair_elem -> return PairLit!!.getBaseType()
        }
        return LitTypes.NonLitWacc
    }

    fun returnIdentType(table: SymbolTable): LitTypes?{
        if(type == RHS_type.expr) {
            if (expr!!.getBaseType() == LitTypes.IdentWacc) {
                if(expr is ArrayElemNode) {
                    return table.lookupSymbol(expr.identifier.id)?.getBaseType()
                }
                if (expr is BinaryOpNode) {

                }
                val exprId = expr as IdentNode
                val value = expr.getValueType(table)?.getBaseType()
                return value
            } else {
                return expr.getBaseType()
            }
        } else if (type == RHS_type.pair_elem) {
            val pairVal = PairLit?.expr
            if (pairVal?.getBaseType() == LitTypes.IdentWacc) {
                val exprId = pairVal as IdentNode
                val value = exprId.getValueType(table)
                if (value is PairNode) {
                    return (value.returnElemNode(PairLit!!.elem))
                } else {
                    return pairVal?.getBaseType()
                }

            }
        } else if(type == RHS_type.call) {
            val value = table.getFunction(funId!!)!!.getBaseType()

            return value
        }
        return null
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if (type == RHS_type.call) {
            val funNode = table.getFunction(funId!!)
            val parameters = funNode!!.params
            if (args != null) {
                if (parameters!!.listParamNodes.count() != args.exprs.count()) {
                    errors.addError(IncorrectNumParams(ctx, parameters.listParamNodes.count(), args.exprs.count()))
                } else {
                    for (i in 0..args.exprs.size - 1) {
                        val actual = args.exprs[i]
                        val expected = parameters.listParamNodes[i]
                        if (actual.getBaseType() == LitTypes.IdentWacc) {
                            val actIdent = actual as IdentNode
                            val actType = table.lookupSymbol(actual.id)
                            if (expected.getBaseType() != actType!!.getBaseType()) {
                                errors.addError(IncompatibleTypes(ctx, expected.getBaseType().toString(), actual, table))
                            }
                        } else if (actual.getBaseType() != expected.getBaseType()) {
                            errors.addError(IncompatibleTypes(ctx, expected.getBaseType().toString(), actual, table))
                        }
                    }
                }
            } else {
                errors.addError(IncorrectNumParams(ctx, parameters!!.listParamNodes.count(), 0))
            }


        } else if (type == RHS_type.expr) {
            expr!!.semanticCheck(errors, table)
        } else if (type == RHS_type.array_lit) {
            ArrayLit!!.semanticCheck(errors, table)
        } else if (type == RHS_type.pair_elem) {
            PairLit!!.semanticCheck(errors, table)
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
