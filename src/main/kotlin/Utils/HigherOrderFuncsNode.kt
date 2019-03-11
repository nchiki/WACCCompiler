package main.kotlin.Utils

import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedFunction
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class HigherOrderFuncsNode(val idNode : Node, val highOrder : String , val argsNode : Node, val size : Long, override val ctx: ParserRuleContext?) : Node {

    override val weight: Int
        get() = 1
    override var symbolTable: SymbolTable? = null

    var funcLabel :String? = null
    var beforeSp :Int? = null

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        val id = (idNode as IdentNode).id
        val args = (argsNode as IdentNode).id
        val funNode = table.getFunction(id)

        if(funNode == null) {
            errors.addError(UndefinedFunction(ctx!!, id))
        } else {
            val parameters = funNode.params

            val expectedType = parameters!!.listParamNodes[0].getBaseType()
            val node = table.lookupSymbol(args)
            val actualType = node?.getBaseType()
            if(actualType == null) {
                errors.addError(UndefinedVariable(ctx!!, args))
            } else if (actualType != expectedType) {
                errors.addError(IncompatibleTypes(ctx!!, expectedType.toString(), node, table))
            }

        }
        argsNode.semanticCheck(errors, table)

    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        this.funcLabel = codeGenerator.curLabel
        this.beforeSp = symbolTable!!.sp

        when(highOrder) {
            "map" -> map((idNode as IdentNode).id, argsNode as IdentNode, size, codeGenerator)
        }

        /*codeGenerator.addInstruction(label, BLInstr("f_${this.funId!!}"))
        val after = symbolTable!!.sp
        if (after - before != 0) {
            codeGenerator.addInstruction(label, AddInstr(Register.sp, Register.sp, after - before))
            symbolTable!!.sp -= after - before
        }
        codeGenerator.addInstruction(label, MovInstr(codeGenerator.getLastUsedReg(), Register.r0))*/
    }


    // -----------------------UTILS--------------------------------------------------

    fun getElemArray(index: Long, identifier : IdentNode, codeGenerator: CodeGenerator) {
        resolveToAddress(index, identifier, codeGenerator)

        val elemReg = codeGenerator.getLastUsedReg()

        /* Load byte into memory */
        if (resolvesToByte(identifier)) {

            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            return
        }

        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
    }

    fun resolveToAddress(index : Long, identifier: IdentNode, codeGenerator: CodeGenerator) {
        identifier.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()
        val exprReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(exprReg, index))

        val tempReg = codeGenerator.getFreeRegister()

        /* Skip past array size */
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))

        /* Resolves to byte sized element */
        if(resolvesToByte(identifier)){
            codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, exprReg))
            codeGenerator.freeReg(tempReg)
            codeGenerator.freeReg(exprReg)
            return
        }

        /* Add index and multiply by 4 */
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, "${exprReg.toString()}, LSL #2"))

        codeGenerator.freeReg(tempReg)
        codeGenerator.freeReg(exprReg)

    }



    fun resolvesToByte(identifier: IdentNode): Boolean {
        val expr = this.symbolTable!!.lookupSymbol(identifier.id)!!

        if(expr is ArrayTypeNode){
            if(expr.type is BaseNode && expr.type.getBaseType().equals(LitTypes.StringWacc)){
                return false
            }

            /* Doesn't resolve to base */
            if(expr.getDimensions() < size){
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

    fun generateArgCode(expr : ExprNode, codeGenerator: CodeGenerator) {
        val label = codeGenerator.curLabel
        val value = expr.size
        symbolTable!!.sp += value
        val spValue = symbolTable!!.sp

        val inMemory = if (value == 0) {
            "[sp, #-$spValue]"
        } else {
            "[sp, #-$value]"
        }

        // Check if it is an identifier
        if (expr.getBaseType() == LitTypes.IdentWacc) {
            val type = symbolTable!!.lookupSymbol((expr as IdentNode).id)!!.getBaseType()
            // Check if the base type is a Char or Bool
            if ((type == LitTypes.CharWacc || type == LitTypes.BoolWacc)
                    && symbolTable!!.lookupSymbol((expr).id) !is ArrayTypeNode) {
                codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory, true))
            } else {
                codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
            }
            // Check if it is a Char or Bool
        } else if ((expr.getBaseType() == LitTypes.CharWacc || expr.getBaseType() == LitTypes.BoolWacc) && expr !is ArrayLitNode) {
            codeGenerator.addInstruction(label, StrBInstr(codeGenerator.getLastUsedReg(), inMemory, true))
        } else {
            codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
        }

    }




    fun map(funId : String, args : IdentNode, size : Long, codeGenerator: CodeGenerator) {
        var i : Long = 0

        while(i < size) {
            getElemArray(i, args, codeGenerator)
            val elemReg = codeGenerator.getLastUsedReg()

            val type = symbolTable?.lookupSymbol(args.id)?.getBaseType()!!

            /* Load byte into memory */
            if (resolvesToByte(args)) {
                codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
                return
            }

            generateArgCode(argsNode as ExprNode, codeGenerator)
            codeGenerator.addInstruction(this.funcLabel!!, BLInstr("f_${funId!!}"))
            val after = symbolTable!!.sp
            if (after - this.beforeSp!! != 0) {
                codeGenerator.addInstruction(this.funcLabel!!, AddInstr(Register.sp, Register.sp, after - this.beforeSp!!))
                symbolTable!!.sp -= after - this.beforeSp!!
            }
            codeGenerator.addInstruction(this.funcLabel!!, MovInstr(codeGenerator.getLastUsedReg(), Register.r0))
            codeGenerator.freeReg(codeGenerator.getLastUsedReg())
            i++
        }



    }




}




