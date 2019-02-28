package src.main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.IdentNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.Condition
import main.kotlin.Utils.LitTypes
import main.kotlin.Instructions.MultInstr
import main.kotlin.Utils.Register


class ArrayElemNode(val identifier: IdentNode, var exprs : List<ExprNode>, override val ctx: BasicParser.ArrayElemContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    fun resolveToAddress(codeGenerator: CodeGenerator){
        identifier.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()
        for(i in (0 until exprs.size)){
            val expr = exprs[i]
            expr.generateCode(codeGenerator)
            val exprReg = codeGenerator.getLastUsedReg()
            val tempReg = codeGenerator.getFreeRegister()
            if(identifier.size == 1){
                /* Skip past array size */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))

                /* Byte access */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, exprReg))

                if(i < exprs.size - 1) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
                }
                //codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            }else{
                /* Skip past array size */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))

                /* Add index and multiply it by 4 (4 bytes per index) */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, "${exprReg.toString()}, LSL #2"))

                if(i < exprs.size - 1) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
                }
            }

            codeGenerator.freeReg(tempReg)
            codeGenerator.freeReg(exprReg)
            //codeGenerator.freeReg(elemReg)
        }
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        resolveToAddress(codeGenerator)

        val elemReg = codeGenerator.getLastUsedReg()

        /* Load byte into memory */
        if(identifier.size == 1){
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            return
        }

        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))

    }

    override fun getBaseType(): LitTypes {
        return identifier.getBaseType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        identifier.semanticCheck(errors, table)
        for (expr in exprs) {
            expr.semanticCheck(errors, table)

            var realExpr = expr

            if(expr is IdentNode){
                val exprValue = table.lookupSymbol(expr.id)
                if(exprValue == null){
                    errors.addError(UndefinedVariable(ctx, expr.id))
                    return
                }
                realExpr = exprValue
            }

            if(!realExpr.getBaseType().equals(LitTypes.IntWacc)){
                errors.addError(IncompatibleTypes(ctx, "INT", realExpr, table))
            }
        }
    }

}
