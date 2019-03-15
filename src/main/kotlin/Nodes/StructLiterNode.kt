package main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.Utils.getTypeSize
import main.kotlin.ValueTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class StructLiterNode(val struct_id : String, val member_id : String, override val ctx: ParserRuleContext?) : ExprNode {

    override fun getBaseType(): LitTypes {
        val node = symbolTable?.lookupLocal(member_id)
        return node?.getBaseType()!!
    }

    override val size: Int
        get() = symbolTable?.lookupLocal(member_id)?.size!!

    override fun optimise(valueTable: ValueTable): Node {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var symbolTable: SymbolTable? = null

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        symbolTable = table
    }

    override fun generateCode(codeGenerator: CodeGenerator) {

        //getting the struct and member node
        val structNode = symbolTable?.lookupLocal(struct_id) as StructNode
        val value = structNode.data.get(member_id)

        val reg = codeGenerator.getFreeRegister()

        //check the size of the declared member and add it to offset of sp
        if (value!!.getBaseType() == LitTypes.BoolWacc || value.getBaseType() == LitTypes.CharWacc
                || value.getBaseType() == LitTypes.IntWacc) {
            var offset = structNode.symbolTable!!.getValueOffset(member_id, codeGenerator)
            val startStruct = symbolTable!!.getValueOffset(struct_id, codeGenerator)
            offset += startStruct
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(reg, "[sp, #$offset]"))
        }
    }
}