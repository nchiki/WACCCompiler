package src.main.kotlin.Nodes

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Nodes.IdentNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class ArrayElemNode(val identifier: IdentNode, var exprs : List<ExprNode>, override val ctx: BasicParser.ArrayElemContext) : ExprNode {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getBaseType(): LitTypes {
        return identifier.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        for (expr in exprs) {
            expr.semanticCheck(errors, table)

            var realExpr = expr

            if(expr is IdentNode){
                val exprValue = table.lookupSymbol(expr.id)
                if(exprValue == null){
                    errors.addError(UndefinedVariable(ctx, expr.id))
                    return
                }
                realExpr = exprValue
            }

            if(!realExpr.getBaseType().equals(LitTypes.IntWacc)){
                errors.addError(IncompatibleTypes(ctx, "INT", realExpr, table))
            }
        }
    }

}
