import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class FreeStatNode(val expr : ExprNode, override val ctx: BasicParser.FreeContext) : Node {

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        if(table.currentExecutionPathHasReturn){
            exitProcess(100)
        }

        if (expr.getBaseType() != LitTypes.PairWacc) {
            if (expr.getBaseType() == LitTypes.IdentWacc) {
                val IdExpr = expr as IdentNode
                var value = table.lookupSymbol(IdExpr.id)
                while (value is IdentNode) {
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
