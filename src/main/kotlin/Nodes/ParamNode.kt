package Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.DoubleDeclare
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode

class ParamNode(
        val id: String,
        val type: ExprNode, override val ctx: BasicParser.ParamContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = type.size

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val offset = type.size //gets size of the data type
        symbolTable!!.sp += offset // add offset to stack pointer
        symbolTable?.declareVariable(id, -symbolTable!!.sp, offset) //Save variable location in symbol table


    }

    override fun getBaseType() : LitTypes{
        var v = type
        while (v is ArrayTypeNode) {
            v = v.type
        }
        val toType = v
        return toType.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        // looks up the id in the symbol table
        val value = table.lookupSymbol(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (value != null && (value !is FunctionNode)) {
            // if there is already a variable with that name -> error
            errors.addError(DoubleDeclare(ctx, id, value.ctx!!.start.line))
        } else {
            table.add(this, id)
        }

    }
}
