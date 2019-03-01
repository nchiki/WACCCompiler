package src.main.kotlin.Nodes

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.IdentNode
import main.kotlin.SymbolTable
import main.kotlin.Instructions.MultInstr
import main.kotlin.Utils.*


class ArrayElemNode(val identifier: IdentNode, var exprs : List<ExprNode>, override val ctx: BasicParser.ArrayElemContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    fun resolveToAddress(codeGenerator: CodeGenerator){

        val type = symbolTable?.lookupSymbol(identifier.id)?.getBaseType()!!

        identifier.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()

        for(i in (0 until exprs.size)){
            val expr = exprs[i]
            expr.generateCode(codeGenerator)
            val exprReg = codeGenerator.getLastUsedReg()
            val tempReg = codeGenerator.getFreeRegister()
            addArrayCheck(codeGenerator, codeGenerator.curLabel, elemReg, exprReg)
            if(type.equals(LitTypes.BoolWacc) || type.equals(LitTypes.CharWacc) || type.equals(LitTypes.StringWacc)){

                /* Skip past array size */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))

                /* Byte access */
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, exprReg))

                if(i < exprs.size - 1) {
                    codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
                }
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

    fun addArrayCheck(codeGenerator: CodeGenerator, label : String, exprReg : Register, tempReg : Register) {
        codeGenerator.addInstruction(label, MovInstr(Register.r0, tempReg))
        codeGenerator.addInstruction(label, MovInstr(Register.r1, exprReg))
        codeGenerator.addInstruction(label, BLInstr("p_check_array_bounds"))
        codeGenerator.addHelper("p_check_array_bounds")
        codeGenerator.addError(ArrayBoundNegativeDef)
        codeGenerator.addError(ArrayBoundsLargeDef)
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        resolveToAddress(codeGenerator)

        val elemReg = codeGenerator.getLastUsedReg()

        val type = symbolTable?.lookupSymbol(identifier.id)?.getBaseType()!!

        /* Load byte into memory */
        if(type.equals(LitTypes.BoolWacc) || type.equals(LitTypes.CharWacc)){

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
