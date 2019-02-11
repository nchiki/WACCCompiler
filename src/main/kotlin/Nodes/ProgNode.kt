package main.kotlin.Nodes

import Errors.MemberAlreadyDefinedError
import Nodes.StatementNode
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import kotlin.reflect.KClass

class ProgNode (var funcDefs: List<FunctionNode>, val stats : Node, val ctx : BasicParser.ProgContext) : Node {

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
                errors.addError(MemberAlreadyDefinedError(id, ctx.start.line, ctx.start.charPositionInLine))
            }
            table.add(func, id)
        }

        for (func in funcDefs) {
            val funcTable = SymbolTable(table)
            children.add(funcTable)
            func.semanticCheck(errors, funcTable)
        }

        val statTable = SymbolTable(table)
        stats.semanticCheck(errors, statTable)
    }
}

