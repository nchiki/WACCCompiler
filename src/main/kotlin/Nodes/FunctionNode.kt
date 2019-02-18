package main.kotlin.Nodes

import Nodes.ParamListNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.Nodes.Statement.StatListNode
import main.kotlin.SymbolTable
import main.kotlin.Errors.IncorrectReturnTypes
import main.kotlin.Utils.LitTypes
import src.main.kotlin.IfCondNode
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess

class FunctionNode (val id: String, val fun_type: LitTypes, val params: ParamListNode?, val stat: Node,
                    override val ctx: BasicParser.FuncContext) : ExprNode {


    override fun getBaseType() : LitTypes {
        return fun_type
    }

    fun syntaxCheck() {
        exitProcess(100)
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        var statement = stat
        if(stat is StatListNode) {
            for(s in stat.listStatNodes) {
                statement = s
            }
            if(statement !is ReturnStatNode) {
                if(statement is IfCondNode) {
                    val node = statement.ifTrueStat
                    checkReturn(node, errors, table)
                    val ElseNode = statement.elseStat
                    checkReturn(ElseNode, errors, table)
                } else {
                    syntaxCheck()
                    errors.addError(IncorrectReturnTypes(ctx))
                }
            } else {
                statement.setFunctionReturn(fun_type)
            }
        } else {
            if(stat is IfCondNode) {
                val node = stat.ifTrueStat
                checkReturn(node, errors, table)
                val ElseNode = stat.elseStat
                checkReturn(ElseNode, errors, table)
            } else if (stat !is ReturnStatNode) {

                syntaxCheck()
                errors.addError(IncorrectReturnTypes(ctx))

            } else {
                val statRet = stat as ReturnStatNode

                stat.setFunctionReturn(fun_type)
                statRet.setFunctionReturn(fun_type)
            }
        }
        if (params != null) {
            params.semanticCheck(errors, table)
        }
        stat.semanticCheck(errors, table)


    }

    private fun checkReturn(node: Node?, errors : ErrorLogger, table: SymbolTable) {
        if (node is ReturnStatNode) {
            node.setFunctionReturn(fun_type)
            return
        }
        var statement = node
        if (node is StatListNode) {
            for (s in node.listStatNodes) {
                statement = s
            }
            if(statement !is ReturnStatNode) {
                if(statement is IfCondNode) {
                    val node = statement.ifTrueStat
                    checkReturn(node, errors, table)
                    val ElseNode = statement.elseStat
                    checkReturn(ElseNode, errors, table)
                } else {
                    syntaxCheck()
                    errors.addError(IncorrectReturnTypes(ctx))
                }
            } else {
                statement.setFunctionReturn(fun_type)
            }
            return
        }
        syntaxCheck()
        errors.addError(IncorrectReturnTypes(ctx))
    }
}
