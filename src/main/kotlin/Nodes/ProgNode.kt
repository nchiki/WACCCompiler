package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.FunctionDoubleDeclare
import main.kotlin.Errors.GlobalReturn
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.Nodes.Statement.StatListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class ProgNode (var funcDefs: List<FunctionNode>, val stats : Node?, override val ctx: BasicParser.ProgContext) : Node {

    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

