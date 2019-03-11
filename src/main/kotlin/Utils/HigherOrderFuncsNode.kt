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

    fun getElemArray(indexReg : Register, identifier : IdentNode, codeGenerator: CodeGenerator) {
        resolveToAddress(indexReg, identifier, codeGenerator)

        val elemReg = codeGenerator.getLastUsedReg()

        /* Load byte into memory */
        if (resolvesToByte(identifier)) {

            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            return
        }

        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
    }

    fun resolveToAddress(exprReg: Register, identifier: IdentNode, codeGenerator: CodeGenerator) {
        identifier.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()

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
        var i: Long = 0
        val indexReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(indexReg, i))
        // evaluate all the arguments of the array in the form of a while loop (iterating through the array)
        val label = codeGenerator.getNewLabel()
        val oldScope = codeGenerator.curScope
        val endLabel = codeGenerator.getNewLabel()
        codeGenerator.endLabel = endLabel
        codeGenerator.addLabel(label, null)

        codeGenerator.curLabel = label

        /* Evaluate index < size */
        val sizeReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(sizeReg, size))
        codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(sizeReg, indexReg))
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(sizeReg, "#1", Condition.GT))
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(sizeReg, "#0", Condition.LE))
        //adds leftReg as last reg used in order to get the result of the operation
        codeGenerator.regsInUse.remove(sizeReg)
        codeGenerator.regsInUse.add(sizeReg)

        val reg = codeGenerator.getLastUsedReg()
        codeGenerator.addInstruction(label, CmpInstr(reg, 1, ""))
        codeGenerator.addInstruction(label, BranchInstr(endLabel, Condition.NE))
        if (reg != Register.r0) {
            codeGenerator.freeReg(reg)
        }

        getElemArray(indexReg, args, codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()

        val type = symbolTable?.lookupSymbol(args.id)?.getBaseType()!!

        /* Load byte into memory */
        if (resolvesToByte(args)) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            return
        }
        //we need to save the register that stores the index
        symbolTable?.declareVariable("index", symbolTable!!.sp, 4) //Save variable location in symbol table
        symbolTable!!.sp += 4
        val spValue = symbolTable!!.sp
        codeGenerator.addInstruction(label, StoreInstr(indexReg, "[sp, #-4]", true))

        generateArgCode(argsNode as ExprNode, codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("f_${funId!!}"))
        val after = symbolTable!!.sp
        if (after - this.beforeSp!! != 0) {
            codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(Register.sp, Register.sp, after - this.beforeSp!!))
            symbolTable!!.sp -= after - this.beforeSp!!
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(codeGenerator.getLastUsedReg(), Register.r0))

        //restore the value of index register
        val offset = symbolTable!!.getValueOffset("index", codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(indexReg, "[sp, #$offset]"))

        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(codeGenerator.getLastUsedReg(), 1))
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(indexReg,indexReg, codeGenerator.getLastUsedReg(), "S"))
        codeGenerator.freeReg(codeGenerator.getLastUsedReg())

        codeGenerator.addLabel(endLabel, oldScope)
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(label, Condition.AL))

        codeGenerator.curLabel = endLabel
        codeGenerator.curScope = oldScope
        codeGenerator.freeReg(indexReg)
        /* Restore stack pointer here */
        symbolTable!!.recoverSp(codeGenerator)
    }

}




