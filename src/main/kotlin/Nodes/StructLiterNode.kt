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

    fun resolveToAddress(codeGenerator: CodeGenerator) {

        val elemReg = codeGenerator.getLastUsedReg()
        val structNode = symbolTable!!.lookupSymbol(struct_id)

        if (structNode is StructNode) {
            for (i in (0 until structNode.exprs.size)) {
                val expr = structNode.exprs[i]
                expr.generateCode(codeGenerator)
                val exprReg = codeGenerator.getLastUsedReg()
                val tempReg = codeGenerator.getFreeRegister()

                /* Skip past array size */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))

                /* Resolves to byte sized element */
                if(i == structNode.exprs.size - 1 && resolvesToByte()){
                    codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, exprReg))
                    codeGenerator.freeReg(tempReg)
                    codeGenerator.freeReg(exprReg)
                    return
                }

                /* Add index and multiply by 4 */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, "${exprReg.toString()}, LSL #2"))

                if(i < structNode.exprs.size - 1){
                    codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
                }

                codeGenerator.freeReg(tempReg)
                codeGenerator.freeReg(exprReg)
            }
        }
    }

    fun resolvesToByte(): Boolean {
        val expr = symbolTable?.lookupLocal(member_id)!!

        return expr.getBaseType().equals(LitTypes.BoolWacc) || expr.getBaseType().equals(LitTypes.CharWacc)
                || expr.getBaseType().equals(LitTypes.StringWacc)
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        resolveToAddress(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()
        /* Load byte into memory */
        if (resolvesToByte()) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            return
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
    }
}