package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.Nodes.Statement.StatListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.Errors.IncorrectReturnTypes
import kotlin.system.exitProcess

class FunctionNode (val id: String, val fun_type: LitTypes, val params: ParamListNode?, val stat: Node,
                    override val ctx: BasicParser.FuncContext) : Node {


    override fun getType() : LitTypes {
        return fun_type
    }

    override fun syntaxCheck() {
       /* if (stat.getType() != ReturnStatNode::class && stat.getType() != ExitStatNode::class) {
            exitProcess(100)
        }*/
        exitProcess(100)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        var statement = stat
        if(stat is StatListNode) {
            for(s in stat.listStatNodes) {
                statement = s
            }
            if(statement !is ReturnStatNode) {
                syntaxCheck()
                errors.addError(IncorrectReturnTypes(ctx.start.line, ctx.start.charPositionInLine))
            } else {
                statement.setFunctionReturn(fun_type)
            }
        } else {
            if (stat !is ReturnStatNode) {
                syntaxCheck()
                errors.addError(IncorrectReturnTypes(ctx.start.line, ctx.start.charPositionInLine))
            }
            val statRet = stat as ReturnStatNode

            stat.setFunctionReturn(fun_type)
            statRet.setFunctionReturn(fun_type)
        }
        if (params != null) {
            params.semanticCheck(errors, table)
        }
        stat.semanticCheck(errors, table)


    }
}