package main.kotlin.Nodes

import Nodes.DeclNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class StructNode(val id: String, var exprs: List<Node>, override val ctx: ParserRuleContext?) : ExprNode {

    val data = HashMap<String, ExprNode>() //data section to be printed before main


    override fun getBaseType(): LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var symbolTable: SymbolTable? = null

    var parentTable: SymbolTable? = null

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val childTable = SymbolTable(table)
        this.parentTable = table
        table.add(this, id)
        this.symbolTable = childTable
        symbolTable!!.add(this, id)
        for (expr in exprs) {
            expr as DeclNode
            val value = expr.rhs.expr
            data.put(expr.id, value!!)
            expr.semanticCheck(errors, childTable)
        }
    }

    override fun generateCode(codeGenerator: CodeGenerator) {

        //generate code for each declared member
        for (expr in exprs) {
            expr.generateCode(codeGenerator)
        }

        //add struct id and its offset and sp to the parent symboltable
        parentTable!!.sp += symbolTable!!.sp
        parentTable!!.declareVariable(id, parentTable!!.sp, 0)
    }

}