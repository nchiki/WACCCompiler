package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext

class StructNode(val id : String, var exprs: List<Node>, override val ctx: ParserRuleContext?) : Node{

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var symbolTable: SymbolTable? = null


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val childTable = SymbolTable(table)
        this.symbolTable = childTable
        for (expr in exprs) {
            expr.semanticCheck(errors, childTable)
        }
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}