package main.kotlin.Nodes

import Errors.FunctionDoubleDeclare
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class ProgNode (var funcDefs: List<FunctionNode>, val stats : Node?, override val ctx: BasicParser.ProgContext) : Node {

    override fun getType() : LitTypes {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var children : MutableList<SymbolTable> = mutableListOf()


    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        for (func in funcDefs) {
            val id = func.id
            if (table.lookupSymbol(id) != null) {
                errors.addError(FunctionDoubleDeclare(ctx.start.line, ctx.start.charPositionInLine, id))
            }
            table.add(func, id)
        }

        for (func in funcDefs) {
            val funcTable = SymbolTable(table)
            children.add(funcTable)
            func.semanticCheck(errors, funcTable)
        }

        val statTable = SymbolTable(table)
        if (stats != null) {
            stats.semanticCheck(errors, statTable)
        }
    }
}

