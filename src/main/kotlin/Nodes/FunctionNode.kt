package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class FunctionNode (val id: String, val fun_type: LitTypes, val params: ParamListNode?, var stat: Node,
                    override val ctx: BasicParser.FuncContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() {
            when(fun_type) {
                LitTypes.CharWacc -> return 1
                LitTypes.BoolWacc -> return 1
                else -> return 4
            }
        }

    override fun optimise(valueTable: ValueTable): Node {
        stat = stat.optimise(valueTable)
        return this
    }

    override val weight: Int
        get() = TODO("not implemented")

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = "f_$id"
        codeGenerator.switchFunctions(label)
        addFunInstructions(codeGenerator, label)
    }

    fun addFunInstructions(codeGenerator: CodeGenerator, label : String) {
        symbolTable!!.inHighOrderFunction = Pair(true, this)
        codeGenerator.addInstruction(label, PushInstr())
        symbolTable!!.sp += 4
        if (params != null) {
            params.generateCode(codeGenerator)
        }
        symbolTable!!.sp = 0
        stat.generateCode(codeGenerator)

        val difference = symbolTable!!.sp //- afterParams
        if(difference > 0) {
            codeGenerator.addInstruction(label, AddInstr(Register.sp, Register.sp, difference))
        }
        symbolTable!!.inHighOrderFunction = Pair(false, null)
    }

    override fun getBaseType() : LitTypes {
        return fun_type
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
        table.currentFunction = this
        table.currentExecutionPathHasReturn = false

        if (params != null) {
            params.semanticCheck(errors, table)
        }

        stat.semanticCheck(errors, table)

        /* Check if the function doesn't return */
        if(!table.currentExecutionPathHasReturn){
            exitProcess(100)
        }

        table.currentFunction = null
        // exiting function or higher order function, we need this
        // variable set to false even if it was false before as well
        symbolTable!!.inHighOrderFunction = Pair(false, null)
    }

}
