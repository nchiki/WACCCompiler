import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class FreeStatNode(val expr : ExprNode, override val ctx: BasicParser.FreeContext) : Node {
    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (expr.getType() != LitTypes.PairWacc) {
            if (expr.getType() == LitTypes.IdentWacc) {
                val IdExpr = expr as IdentNode
                var value = table.lookupSymbol(IdExpr.id)
                while (value is IdentNode) {
                    value = value as IdentNode
                    value = table.lookupSymbol(value.id)
                }
                if (value !is PairNode && value !is PairLitNode && value !is NewPairNode) {
                    errors.addError(IncompatibleTypes(ctx, "PAIR", value!!, table))
                }
            } else {
                errors.addError(IncompatibleTypes(ctx, "PAIR", expr, table))
            }
        }
    }
}