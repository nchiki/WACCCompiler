package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Instructions.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class FunctionNode (val id: String, val fun_type: LitTypes, val params: ParamListNode?, val stat: Node,
                    override val ctx: BasicParser.FuncContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() = 4

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        val label = "f_$id"
        codeGenerator.switchFunctions(label)
        addFunInstructions(codeGenerator, label)
    }

    fun addFunInstructions(codeGenerator: CodeGenerator, label : String) {
        codeGenerator.addInstruction(label, PushInstr())
        if (params != null) {
            params.generateCode(codeGenerator)
        }
        val afterParams = symbolTable!!.sp
        symbolTable!!.sp += 4
        stat.generateCode(codeGenerator)
        symbolTable!!.sp -= 4
        val difference = symbolTable!!.sp - afterParams
        if(difference > 0) {
            codeGenerator.addInstruction(label, AddInstr(Register.sp, Register.sp, difference))
        }
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
    }

}
