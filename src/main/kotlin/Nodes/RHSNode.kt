package main.kotlin.Nodes

import BasicParser
import Nodes.PairType.PairNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.IncorrectNumParams
import main.kotlin.Errors.UndefinedFunction
import main.kotlin.Instructions.AddInstr
import main.kotlin.Instructions.BLInstr
import main.kotlin.Instructions.MovInstr
import main.kotlin.Instructions.StoreInstr
import main.kotlin.Nodes.Literals.NewPairNode
import main.kotlin.Nodes.Statement.ArgListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.HigherOrderFuncsNode
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class RHSNode(val type: RHS_type, val funId: String?, val args: ArgListNode?, val line: Int, val pos: Int,
              var expr: ExprNode?, val newPairNode: NewPairNode?, val PairLit: PairElemNode?, val ArrayLit: ArrayLitNode?, override val ctx: BasicParser.AssignRHSContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = 0

    var highOrderFunction : HigherOrderFuncsNode? = null

    override fun generateCode(codeGenerator: CodeGenerator) {
        when (type) {
            RHS_type.newpair -> newPairNode!!.generateCode(codeGenerator)
            RHS_type.expr -> expr!!.generateCode(codeGenerator)
            RHS_type.array_lit -> ArrayLit!!.generateCode(codeGenerator)
            RHS_type.pair_elem -> PairLit!!.generateCode(codeGenerator)
            RHS_type.call -> callGenerateCode(codeGenerator)
            else -> return
        }
    }


    // Add the instructions for a function call
    private fun callGenerateCode(codeGenerator: CodeGenerator) {
        val label = codeGenerator.curLabel
        if(symbolTable!!.isHigherOrderFunction(funId!!)) {
            generateHighCallCode(codeGenerator, label)
        } else {
            val label = codeGenerator.curLabel
            val before = symbolTable!!.sp
            args?.generateCode(codeGenerator)

            codeGenerator.addInstruction(label, BLInstr("f_${this.funId}"))
            val after = symbolTable!!.sp
            if (after - before != 0) {
                codeGenerator.addInstruction(label, AddInstr(Register.sp, Register.sp, after - before))
                symbolTable!!.sp -= after - before
            }
            codeGenerator.addInstruction(label, MovInstr(codeGenerator.getLastUsedReg(), Register.r0))
        }
    }

    private fun generateHighCallCode(codeGenerator: CodeGenerator, label: String) {
        val before = symbolTable!!.sp

        //store size
        val sizeNode = IntLitNode(highOrderFunction!!.size, null)
        val sizeOfSize = sizeNode.size
        symbolTable!!.sp += sizeOfSize

        var sizeInMemory = "[sp, #-${symbolTable!!.sp}]"
        if (sizeOfSize != 0) {
            sizeInMemory =  "[sp, #-$sizeOfSize]"
        }

        sizeNode.generateCode(codeGenerator)
        codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), sizeInMemory, true))

        //store array
        val argsValue = (highOrderFunction!!.argsNode as IdentNode).size
        highOrderFunction!!.argsNode.generateCode(codeGenerator)
        symbolTable!!.sp += argsValue
        val spValue = symbolTable!!.sp

        var inMemory = "[sp, #-$spValue]"
        if (argsValue != 0) {
           inMemory =  "[sp, #-$argsValue]"
        }


        codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))

        val value = highOrderFunction!!.sNode.size
        symbolTable!!.sp += value

        inMemory = "[sp, #-${symbolTable!!.sp}]"
        if (argsValue != 0) {
           inMemory = "[sp, #-$value]"
        }

        //store name of the function to be called
        highOrderFunction!!.sNode.generateCode(codeGenerator)
        codeGenerator.addInstruction(label, StoreInstr(codeGenerator.getLastUsedReg(), inMemory, true))
        val after = symbolTable!!.sp
        this.highOrderFunction!!.generateCode(codeGenerator)
        codeGenerator.addInstruction(label, AddInstr(Register.sp, Register.sp, after - before))
        symbolTable!!.sp -= after - before
        codeGenerator.addInstruction(label, MovInstr(codeGenerator.getLastUsedReg(), Register.r0))
    }

    override fun getBaseType(): LitTypes {

        return when (type) {
            RHS_type.expr -> expr!!.getBaseType()
            RHS_type.array_lit -> ArrayLit!!.getBaseType()
            RHS_type.call -> LitTypes.FuncWacc
            RHS_type.newpair -> LitTypes.PairWacc
            RHS_type.pair_elem -> PairLit!!.getBaseType()
            else -> LitTypes.NonLitWacc
        }
    }

    fun returnIdentType(table: SymbolTable): LitTypes? {
        when (type) {
            RHS_type.expr -> {
                return if (expr!!.getBaseType() == LitTypes.IdentWacc) {
                    if (expr is ArrayElemNode) {
                        table.lookupSymbol((expr as ArrayElemNode).identifier.id)?.getBaseType()
                    } else {
                        (expr as IdentNode).getValueType(table)?.getBaseType()
                    }
                } else {
                    expr!!.getBaseType()
                }
            }
            RHS_type.pair_elem -> {
                val pairVal = PairLit?.expr
                val value = (pairVal as IdentNode).getValueType(table)
                return if (pairVal.getBaseType() == LitTypes.IdentWacc && value is PairNode) {
                    value.returnElemNode(PairLit!!.elem)
                } else {
                    pairVal.getBaseType()
                }
            }
            RHS_type.call -> { if(table.isHigherOrderFunction(funId!!)) {
                return table.getFunction((args!!.exprs[0] as IdentNode).id)!!.getBaseType()
                }
                return table.getFunction(funId)?.getBaseType()}
            else -> return null
        }
    }

    override fun optimise(valueTable: ValueTable): RHSNode {
        if(type.equals(RHS_type.expr)){
            expr = expr!!.optimise(valueTable) as ExprNode
        }

        return this
    }


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        if (type == RHS_type.call && table.isHigherOrderFunction(funId!!)) {
            this.highOrderFunction = HigherOrderFuncsNode(args!!.exprs[0], funId, args.exprs[1], (args.exprs[2] as IntLitNode).int_val, ctx)
            this.highOrderFunction!!.semanticCheck(errors, table)
        } else {
            if (type == RHS_type.call) {

                val funNode = table.getFunction(funId!!)
                if (funNode == null) {
                    errors.addError(UndefinedFunction(ctx, funId))
                } else {
                    val parameters = funNode.params
                    if (args != null) {
                        if (parameters!!.listParamNodes.count() != args.exprs.count()) {
                            errors.addError(IncorrectNumParams(ctx, parameters.listParamNodes.count(), args.exprs.count()))
                        } else {
                            for (i in 0 until args.exprs.size) {
                                var actual = args.exprs[i]
                                val expected = parameters.listParamNodes[i]
                                if (actual.getBaseType() == LitTypes.IdentWacc) {
                                    if(actual is ArrayElemNode) {
                                        actual = actual.identifier
                                    }
                                    val actType = table.lookupSymbol((actual as IdentNode).id)
                                    if (expected.getBaseType() != actType!!.getBaseType()) {
                                        errors.addError(IncompatibleTypes(ctx, expected.getBaseType().toString(), actual, table))
                                    }
                                } else if (actual.getBaseType() != expected.getBaseType()) {
                                    errors.addError(IncompatibleTypes(ctx, expected.getBaseType().toString(), actual, table))
                                }
                            }
                        }
                    } else {
                        errors.addError(IncorrectNumParams(ctx, parameters!!.listParamNodes.count(), 0))
                    }
                }


        } else if (type == RHS_type.expr) {
            expr!!.semanticCheck(errors, table)
        } else if (type == RHS_type.array_lit) {
            ArrayLit!!.semanticCheck(errors, table)
        } else if (type == RHS_type.pair_elem) {
            PairLit!!.semanticCheck(errors, table)
        } else if (type == RHS_type.newpair) {
            newPairNode!!.semanticCheck(errors, table)
        }
        args?.semanticCheck(errors, table)
    }
    }

    fun getSizeOfOffset(): Int {

        if (expr != null && expr!!.getBaseType() == LitTypes.PairWacc) {
            return 4
        }

        return when (type) {
            RHS_type.expr -> expr!!.size
            RHS_type.newpair -> newPairNode!!.size
            RHS_type.pair_elem -> PairLit!!.size
            RHS_type.call -> { if(symbolTable!!.isHigherOrderFunction(funId!!)) {
                return symbolTable!!.getFunction((args!!.exprs[0] as IdentNode).id)!!.size
            }
                symbolTable!!.getFunction(funId)!!.size
            }
            else -> 4
        }
    }
}

enum class RHS_type(s: String) {
    generic("generic"),
    expr("expr"),
    array_lit("array"),
    newpair("newpair"),
    pair_elem("pair"),
    call("call")
}
