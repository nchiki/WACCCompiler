package main.kotlin.Utils

import BasicParser.LESS
import BasicParser._decisionToDFA
import Nodes.DeclNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedFunction
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Instructions.*
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expressions.BoolOpNode
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.math.exp

class HigherOrderFuncsNode(val idNode : Node, val highOrder : String , val argsNode : Node, val size : Long, override val ctx: ParserRuleContext?) : Node {

    override val weight: Int
        get() = 1
    override var symbolTable: SymbolTable? = null

    var funcLabel :String? = null
    var funcScope : String? = null
    var beforeSp :Int? = null
    val sNode = StringLitNode("\"$highOrder\"", null)

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = SymbolTable(table)

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
        sNode.semanticCheck(errors, table)
    }

    override fun generateCode(codeGenerator: CodeGenerator) {
        this.funcLabel = codeGenerator.curLabel
        this.funcScope = codeGenerator.curScope
        this.beforeSp = symbolTable!!.sp

        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("f_${highOrder}"))
        when(highOrder) {
            "map" -> { val label = "f_map"
                codeGenerator.switchFunctions(label)
                map((idNode as IdentNode).id, argsNode as IdentNode, size, codeGenerator)
            }
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

    fun getElemArray(indexIdentNode : IdentNode, identifier : IdentNode, codeGenerator: CodeGenerator) {
        resolveToAddress(indexIdentNode, identifier, codeGenerator)

        val elemReg = codeGenerator.getLastUsedReg()

        /* Load byte into memory */
        if (resolvesToByte(identifier)) {

            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            return
        }

        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(elemReg, "[$elemReg]", Condition.AL))
    }

    fun resolveToAddress(indexIdentNode: IdentNode, identifier: IdentNode, codeGenerator: CodeGenerator) {
        identifier.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()

        val tempReg = codeGenerator.getFreeRegister()

        /* Skip past array size */
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))

        indexIdentNode.generateCode(codeGenerator)
        val exprReg = codeGenerator.getLastUsedReg()
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


    fun addReturn(expr : Node, codeGenerator: CodeGenerator) {
        expr.generateCode(codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(Register.r0, codeGenerator.getLastUsedReg(), null))

        codeGenerator.freeReg(codeGenerator.getLastUsedReg())

        symbolTable?.recoverSp(codeGenerator)

        // Pop last two items from stack
        codeGenerator.addInstruction(codeGenerator.curLabel, PopInstr())
        codeGenerator.addInstruction(codeGenerator.curLabel, PopInstr())
        codeGenerator.curLabel = funcLabel!!
        codeGenerator.curScope = funcScope!!
    }




    // MAP : Applies function (funId) to every element of array (args).

    fun map(funId : String, args : IdentNode, size : Long, codeGenerator: CodeGenerator) {
        //println(symbolTable!!.sp)
        symbolTable!!.sp += 4
        codeGenerator.addInstruction(codeGenerator.curLabel, PushInstr())
        val stringLit = StringLitNode(funId, null)
        var offset = stringLit.size //gets size of the data type
        symbolTable?.declareVariable(funId, 0,-symbolTable!!.sp) //Save variable location in symbol table
        symbolTable!!.sp += offset // add offset to stack pointer

        offset = args.size //gets size of the data type
        symbolTable?.declareVariable(args.id, 0,-symbolTable!!.sp) //Save variable location in symbol table
        symbolTable!!.sp += offset // add offset to stack pointer

        val sizeNode = IntLitNode(size, null)
        offset = sizeNode.size //gets size of the data type
        symbolTable?.declareVariable("size", 0,-symbolTable!!.sp) //Save variable location in symbol table
        symbolTable!!.sp += offset // add offset to stack pointer

        symbolTable!!.sp = 0

        
        // creates the array to be returned
        val array = createArray(size, codeGenerator)
        val arrayRHS = RHSNode(RHS_type.array_lit, null, null, 0,0,
                null, null, null, array, BasicParser.AssignRHSContext())
        val declArrayNode = DeclNode("map_return", BaseNode("int", null), arrayRHS, null)

        declArrayNode.semanticCheck(ErrorLogger(), symbolTable!!)
        declArrayNode.generateCode(codeGenerator)


        codeGenerator.freeReg(codeGenerator.getLastUsedReg())


        //store current index (starting from 0)
        val intLitNode = IntLitNode(0, null)
        val indexRHS = RHSNode(RHS_type.expr, null, null, 0,0,
                intLitNode, null, null, null, BasicParser.AssignRHSContext())
        val declIndexNode = DeclNode("p", BaseNode("int", null), indexRHS, null)

        declIndexNode.semanticCheck(ErrorLogger(), symbolTable!!)
        declIndexNode.generateCode(codeGenerator)

        // evaluate all the arguments of the array in the form of a while loop (iterating through the array)
        val label = codeGenerator.getNewLabel()
        val oldScope = codeGenerator.curScope
        val endLabel = codeGenerator.getNewLabel()
        codeGenerator.endLabel = endLabel
        codeGenerator.addLabel(label, null)

        codeGenerator.curLabel = label

        /* Evaluate index < size */
        val indexIdentNode = IdentNode("p", null)
        indexIdentNode.semanticCheck(ErrorLogger(), symbolTable!!)
        indexIdentNode.generateCode(codeGenerator)
        var indexReg = codeGenerator.getLastUsedReg()
        val sizeReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(sizeReg, size))
        codeGenerator.addInstruction(codeGenerator.curLabel, CmpInstr(sizeReg, indexReg))
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(sizeReg, "#1", Condition.GT))
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(sizeReg, "#0", Condition.LE))
        //adds leftReg as last reg used in order to get the result of the operation
        codeGenerator.regsInUse.remove(sizeReg)
        codeGenerator.regsInUse.add(sizeReg)
        codeGenerator.freeReg(indexReg)

        //make sure that reg is == sizeReg
        val reg = codeGenerator.getLastUsedReg()
        codeGenerator.addInstruction(label, CmpInstr(reg, 1, ""))
        codeGenerator.addInstruction(label, BranchInstr(endLabel, Condition.NE))
        if (reg != Register.r0) {
            codeGenerator.freeReg(reg)
        }
        codeGenerator.freeReg(sizeReg)


        // gets element of the array passed as argument
        getElemArray(indexIdentNode, args, codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()

        val type = symbolTable?.lookupSymbol(args.id)?.getBaseType()!!

        /* Load byte into memory */
        if (resolvesToByte(args)) {
            codeGenerator.addInstruction(codeGenerator.curLabel, LoadBInstr(elemReg, "[$elemReg]"))
            return
        }
        val before = symbolTable!!.sp

        // generate arg code for the function called by map
        generateArgCode(argsNode as ExprNode, codeGenerator)
        codeGenerator.addInstruction(codeGenerator.curLabel, BLInstr("f_${funId}"))

        val after = symbolTable!!.sp
        if (after - this.beforeSp!! != 0) {
            codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(Register.sp, Register.sp, after - before!!))
            symbolTable!!.sp -= after - before!!
        }
        codeGenerator.addInstruction(codeGenerator.curLabel, MovInstr(codeGenerator.getLastUsedReg(), Register.r0))

        // assign value to elem of result array
        assignToElem(args, symbolTable!!.getFunction(funId)!!.getBaseType(), indexIdentNode, codeGenerator)

        // adds one to the index
        val addReg = codeGenerator.getFreeRegister()
        codeGenerator.addInstruction(codeGenerator.curLabel, LoadInstr(addReg, 1))
        indexIdentNode.generateCode(codeGenerator)
        indexReg = codeGenerator.getLastUsedReg()
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(indexReg,indexReg, addReg, "S"))
        val indexOff = symbolTable!!.getValueOffset(indexIdentNode.id, codeGenerator)
        if(indexOff != 0) {
            codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(indexReg, "[sp, #$indexOff]"))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(indexReg, "[sp]"))
        }
        codeGenerator.freeReg(indexReg)
        codeGenerator.freeReg(addReg)

        codeGenerator.addLabel(endLabel, oldScope)
        codeGenerator.addInstruction(codeGenerator.curLabel, BranchInstr(label, Condition.AL))

        codeGenerator.curLabel = endLabel
        codeGenerator.curScope = oldScope
        codeGenerator.freeReg(indexReg)

        /* Restore stack pointer here */
        symbolTable!!.recoverSp(codeGenerator)


        // add return of the function
        val arrayIdentNode = IdentNode("map_return", null)
        arrayIdentNode.semanticCheck(ErrorLogger(), symbolTable!!)

        addReturn(arrayIdentNode, codeGenerator)

    }

    private fun assignToElem(arrayNode: IdentNode, type : LitTypes, indexIdentNode: IdentNode, codeGenerator: CodeGenerator) {
        // need location of "arrayElem" instead of identifier
        // need to load the value of the index into an expr or find a way of resolving the address using the value in index<reg
        arrayNode.generateCode(codeGenerator)
        val elemReg = codeGenerator.getLastUsedReg()
        codeGenerator.regsInUse.add(elemReg)
        codeGenerator.regsNotInUse.remove(elemReg)
        indexIdentNode.generateCode(codeGenerator)
        val indexReg = codeGenerator.getLastUsedReg()

        //skip past array size
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, 4))
        /* Resolve the address of the array element and put it into a register */
        codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(elemReg, elemReg, "${indexReg}, LSL #2"))

        /* Store result value into the address of the array element */
        if((/*nodeType !is ArrayTypeNode && nodeType.resolvesToByte()) ||*/ type.equals(LitTypes.CharWacc) ||type.equals(LitTypes.BoolWacc) || type.equals(LitTypes.StringWacc))) {
            codeGenerator.addInstruction(codeGenerator.curLabel, StrBInstr(Register.r0, "[$elemReg]"))
        } else {
            codeGenerator.addInstruction(codeGenerator.curLabel, StoreInstr(Register.r0, "[$elemReg]"))
        }

        codeGenerator.freeReg(elemReg)
        codeGenerator.freeReg(indexReg)


    }

    private fun createArray(size: Long, codeGenerator: CodeGenerator): ArrayLitNode {
        val exprList  =  ArrayList<ExprNode>()
        for (i in 0 until size) {
            exprList.add(IntLitNode(0, null))
        }

        /*val label = codeGenerator.curLabel
        val offset = 4 //gets size of the data type
        symbolTable?.declareVariable("b", symbolTable!!.sp, offset) //Save variable location in symbol table

        symbolTable!!.sp += offset // add offset to stack pointer*/

        return ArrayLitNode(exprList, null)

    }

}




