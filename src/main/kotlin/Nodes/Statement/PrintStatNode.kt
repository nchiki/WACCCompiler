package main.kotlin.Nodes.Statement

<<<<<<< HEAD
=======
import Errors.UndefinedVariable
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import Nodes.StatementNode
>>>>>>> dd55e2a419962aed1c03054ee7f3e948c237c866
import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
<<<<<<< HEAD
=======
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.Instructions.Instruction
import kotlin.Instructions.MovInstr
>>>>>>> dd55e2a419962aed1c03054ee7f3e948c237c866

class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{
    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
<<<<<<< HEAD
=======

    override fun generateCode(codeGeneration: CodeGeneration) {
        var instrs = arrayListOf<Instruction>()
        instrs.add(MovInstr())
        if (expr.getType() == LitTypes.CharWacc || expr.getType() == LitTypes.StringWacc) {
            //add case for Bool
        } else if (expr.getType() == LitTypes.BoolWacc) {
            //add all these cases
        } else if (expr.getType() == LitTypes.IntWacc) {
            //add all these case
        }
    }

    override fun translate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType() : LitTypes {
        return expr.getType()
    }
>>>>>>> dd55e2a419962aed1c03054ee7f3e948c237c866

    override fun generateCode(codeGeneration: CodeGeneration) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}