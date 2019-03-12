package main.kotlin.Nodes

import main.kotlin.Instructions.PushInstr
import main.kotlin.ErrorLogger
import main.kotlin.Errors.FunctionDoubleDeclare
import main.kotlin.Errors.GlobalReturn
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.Nodes.Statement.StatListNode
import main.kotlin.SymbolTable
import main.kotlin.CodeGenerator
import main.kotlin.Instructions.LoadInstr
import main.kotlin.Instructions.PopInstr
import main.kotlin.Utils.Register


class ProgNode (var funcDefs: List<FunctionNode>, val stats : Node?, override val ctx: BasicParser.ProgContext) : Node {

    override var symbolTable: SymbolTable? = null
    var statTable : SymbolTable? = null

    override val weight: Int
        get() =0


    //triggers generate code for everything inside global main
    override fun generateCode(codeGenerator: CodeGenerator) {
        codeGenerator.addInstruction("main", PushInstr())
        stats!!.generateCode(codeGenerator)
        this.statTable!!.recoverSp(codeGenerator)

        // Generate each function code
        for (func in funcDefs) {
            func.generateCode(codeGenerator)
        }

        /* Get the last label for this scope */
        val mainLabels = codeGenerator.scopedLabels.get("main")
        val lastLabel = mainLabels!![mainLabels.size - 1]

        codeGenerator.addInstruction(lastLabel, LoadInstr(Register.r0, 0, null))
        codeGenerator.addInstruction(lastLabel, PopInstr())
    }

    var children : MutableList<SymbolTable> = mutableListOf()


    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
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

        this.statTable = SymbolTable(table)
        if (stats is StatListNode) {
            for (node in stats.listStatNodes) {
                if (node is ReturnStatNode) {
                    errors.addError(GlobalReturn(node.ctx))
                }
            }
        }
        if (errors.errorList.size == 0) {
            stats!!.semanticCheck(errors, statTable!!)
        }
    }


    override fun optimise(valueTable: ValueTable): Node {
        for (func in funcDefs){
            func.optimise(valueTable)
        }

        stats as StatListNode

        for(i in (0 until stats.listStatNodes.size)){
            stats.listStatNodes[i] = stats.listStatNodes[i].optimise(valueTable)
        }

        return this
    }
}
