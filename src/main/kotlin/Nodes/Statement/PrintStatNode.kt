package main.kotlin.Nodes.Statement

import Instructions.PushInstr
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class PrintStatNode(val expr : ExprNode, override val ctx : BasicParser.PrintContext) : Node{

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        expr.generateCode(codeGenerator)
        var label = ""
        if (expr is StringLitNode) {
            label = "p_print_string"
            codeGenerator.addHelper(label)
        }
        if (expr is IntLitNode) {
            label = "p_print_int"
            codeGenerator.addHelper("p_print_int")
        }
        codeGenerator.addToHelper(label, PushInstr())

    }
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        expr.semanticCheck(errors, table)
    }
}
