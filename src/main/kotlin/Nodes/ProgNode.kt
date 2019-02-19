package main.kotlin.Nodes

import Instructions.PopInstr
import Instructions.PushInstr
import main.kotlin.ErrorLogger
import main.kotlin.Errors.FunctionDoubleDeclare
import main.kotlin.Errors.GlobalReturn
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.Nodes.Statement.StatListNode
import main.kotlin.SymbolTable
import main.kotlin.CodeGenerator
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.Literals.IntLitNode


class ProgNode (var funcDefs: List<FunctionNode>, val stats : Node?, override val ctx: BasicParser.ProgContext) : Node {


    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override fun generateCode(codeGenerator: CodeGenerator) {
        codeGenerator.addLabel("main")
        codeGenerator.curLabel = "main"
        codeGenerator.addInstruction("main", PushInstr())
        for (func in funcDefs) {
            func.generateCode(codeGenerator)
        }
        codeGenerator.addInstruction("main", LoadInstr(Register.r0, 0))
        codeGenerator.addInstruction("main", PopInstr())

        /*codeGeneration.pushToStack(Register.lr)
        codeGeneration.loadPC()
        for (func in funcDefs) {
            func.generateCode(codeGeneration)
        }
        stats!!.generateCode(codeGeneration)

        codeGeneration.loadToReg(0, Register.r0)

*/

        // returned strings or list of instructions from generateCode will be passed to
        // codeGeneration.translateCode(instructions)
    }

    var children : MutableList<SymbolTable> = mutableListOf()

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        table.errors = errors
        for (func in funcDefs) {
            val id = func.id
            if (table.lookupSymbol(id) != null) {
                errors.addError(FunctionDoubleDeclare(func.ctx, id))
            }
        }
        table.addToFunctions(funcDefs)

        for (func in funcDefs) {
            val funcTable = SymbolTable(table)
            children.add(funcTable)
            func.semanticCheck(errors, funcTable)
        }

        val statTable = SymbolTable(table)
        if (stats is StatListNode) {
            for (node in stats.listStatNodes) {
                if (node is ReturnStatNode) {
                    errors.addError(GlobalReturn(node.ctx))
                }
            }
        }
        if (errors.errorList.size == 0) {
            stats!!.semanticCheck(errors, statTable)
        }
    }
}
