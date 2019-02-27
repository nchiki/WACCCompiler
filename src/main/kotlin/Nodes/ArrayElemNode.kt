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


class ArrayElemNode(val identifier: IdentNode, var exprs : List<ExprNode>, override val ctx: BasicParser.ArrayElemContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        identifier.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()

        for(expr in exprs){
            expr.generateCode(codeGenerator)
            val exprReg = codeGenerator.getLastUsedReg()
            val tempReg = codeGenerator.getFreeRegister()
            if(expr.size == 1){
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, exprReg))
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            }else{
                codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(tempReg, "#${expr.size}"))
                codeGenerator.addInstruction(codeGenerator.curLabel, MultInstr(exprReg, tempReg))
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, exprReg))
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
            }

            codeGenerator.freeReg(tempReg)
            codeGenerator.freeReg(exprReg)
        }

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
