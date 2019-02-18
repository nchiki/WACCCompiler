package main.kotlin.Nodes.Statement

import Errors.UndefinedVariable
import Nodes.Literals.PairLitNode
import Nodes.PairType.PairNode
import Nodes.StatementNode
import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode

import src.main.kotlin.Nodes.Literals.IntLitNode
import main.kotlin.Instructions.Instruction
import main.kotlin.Instructions.MovInstr
import main.kotlin.Utils.LitTypes


class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{
    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override fun generateCode(codeGeneration: CodeGeneration) {
        var instrs = arrayListOf<Instruction>()
        instrs.add(MovInstr())
        if (expr.getBaseType() == LitTypes.CharWacc || expr.getBaseType() == LitTypes.StringWacc) {
            //add case for Bool
        } else if (expr.getBaseType() == LitTypes.BoolWacc) {
            //add all these cases
        } else if (expr.getBaseType() == LitTypes.IntWacc) {
            //add all these case
        }
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}