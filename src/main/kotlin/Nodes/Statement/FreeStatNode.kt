import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class FreeStatNode(val expr: ExprNode, override val ctx: BasicParser.FreeContext) : Node {

    override var symbolTable: SymbolTable? = null

    override val weight: Int
        get() = 0

    override fun generateCode(codeGenerator: CodeGenerator) {
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, codeGenerator.regsNotInUse.peek()))
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("p_free_pair"))
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (table.currentExecutionPathHasReturn && table.currentFunction != null) {
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
