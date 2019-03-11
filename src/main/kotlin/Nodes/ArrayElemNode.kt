package src.main.kotlin.Nodes

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.IdentNode
import main.kotlin.SymbolTable
import main.kotlin.Nodes.ArrayTypeNode
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.StringLitNode
import main.kotlin.Utils.*



class ArrayElemNode(val identifier: IdentNode, var exprs: List<ExprNode>, override val ctx: BasicParser.ArrayElemContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    fun resolveToAddress(codeGenerator: CodeGenerator) {
        identifier.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()

        for (i in (0 until exprs.size)) {
            val expr = exprs[i]
            expr.generateCode(codeGenerator)
            val exprReg = codeGenerator.getLastUsedReg()
            val tempReg = codeGenerator.getFreeRegister()
            addArrayCheck(codeGenerator, codeGenerator.curLabel, elemReg, exprReg)

            /* Skip past array size */
            codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))

            /* Resolves to byte sized element */
            if(i == exprs.size - 1 && resolvesToByte()){
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, exprReg))
                codeGenerator.freeReg(tempReg)
                codeGenerator.freeReg(exprReg)
                return
            }

            /* Add index and multiply by 4 */
            codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, "${exprReg.toString()}, LSL #2"))

            if(i < exprs.size - 1){
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
            }

            codeGenerator.freeReg(tempReg)
            codeGenerator.freeReg(exprReg)
        }
    }

    fun resolvesToByte(): Boolean {
        val expr = symbolTable?.lookupSymbol(identifier.id)!!

        if(expr is ArrayTypeNode){
            if(expr.type is BaseNode && expr.type.getBaseType().equals(LitTypes.StringWacc)){
                return false
            }

            /* Doesn't resolve to base */
            if(expr.getDimensions() < exprs.size){
                return false
            }

            var base = expr
            while(base is ArrayTypeNode){
                base = base.type
            }

            return base.getBaseType().equals(LitTypes.BoolWacc) || base.getBaseType().equals(LitTypes.CharWacc) || base.getBaseType().equals(LitTypes.StringWacc)
        }else if(expr is StringLitNode){
            return true
        }
        
        return expr.getBaseType().equals(LitTypes.BoolWacc) || expr.getBaseType().equals(LitTypes.CharWacc) || expr.getBaseType().equals(LitTypes.StringWacc)
    }

    fun addArrayCheck(codeGenerator: CodeGenerator, label : String, exprReg : Register, tempReg : Register) {
        codeGenerator.addInstruction(label, MovInstr(Register.r0, tempReg))
        codeGenerator.addInstruction(label, MovInstr(Register.r1, exprReg))
        codeGenerator.addInstruction(label, BLInstr("p_check_array_bounds"))
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        resolveToAddress(codeGenerator)

        val elemReg = codeGenerator.getLastUsedReg()

        val type = symbolTable?.lookupSymbol(identifier.id)?.getBaseType()!!

        /* Load byte into memory */
        if (resolvesToByte()) {

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

            if (expr is IdentNode) {
                val exprValue = table.lookupSymbol(expr.id)
                if (exprValue == null) {
                    errors.addError(UndefinedVariable(ctx, expr.id))
                    return
                }
                realExpr = exprValue
            }

            if (realExpr.getBaseType() != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes(ctx, "INT", realExpr, table))
            }
        }
    }

}
