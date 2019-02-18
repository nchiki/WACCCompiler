package main.kotlin.Nodes.Statement

import Errors.UndefinedVariable
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import Nodes.StatementNode
import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.Instructions.Instruction
import kotlin.Instructions.MovInstr

class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{
    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

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

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}