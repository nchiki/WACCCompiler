package main.kotlin

import Errors.UndefinedVariable
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.BinaryOpNode
import main.kotlin.Nodes.Expression.ParenNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node

import main.kotlin.Nodes.UnaryOpNode
import src.main.kotlin.Nodes.ExprNode

class SymbolTable (val parent: SymbolTable?){

    /*final val keywords = listOf("char", "int", "ord", "len", "chr", "pair",
                                "string", "char", "bool", "fst", "snd", "newpair",
                                "if", "then", "else", "fi", "while", "do", "done",
                                "begin", "end", "call", "skip", "read", "free", "return",
                                "exit", "print", "println", "is", "true", "false",
                                "null", "+", "-") probably not needed */
    private val children = listOf<SymbolTable>()
    private val parentT = parent
    var table = HashMap<String, Node>()

    /* Ensures that the expression node resolves to a boolean type */
    fun boolExprCheck(expr : Node, errors: ErrorLogger) {
        var tempExpr: Node = expr

        if(tempExpr is ParenNode){
            tempExpr = evaluateParenNode(tempExpr)
        }

        if(tempExpr is BoolLitNode){
            return
        }

        if(tempExpr is IdentNode){
            val variable = lookupSymbol(tempExpr.id)

            if(variable == null){
                errors.addError(UndefinedVariable(tempExpr.ctx.start.line, tempExpr.ctx.start.charPositionInLine, tempExpr.id))
                return
            }

            boolExprCheck(variable, errors)
            return
        }

        if(tempExpr is UnaryOpNode){
            if(tempExpr.operator.equals(BasicParser.NOT)){
                boolExprCheck(tempExpr.operand, errors)
                return
            }
        }

        if(tempExpr is BinaryOpNode){
            when(tempExpr.operator.ruleIndex){
                BasicParser.GREAT -> return
                BasicParser.GREAT_EQ -> return
                BasicParser.LESS -> return
                BasicParser.LESS_EQ -> return
                BasicParser.EQ -> return
                BasicParser.NOTEQ -> return
                BasicParser.AND -> return
                BasicParser.OR -> return
            }
        }

        errors.addError(IncompatibleTypes(tempExpr.ctx!!.start.line, tempExpr.ctx!!.start.charPositionInLine, "BOOL", expr, this))
    }

    fun evaluateParenNode(node_: Node): Node{
        var node = node_
        while(node is ParenNode){
            node = node.expr
        }
        return node
    }

    /*fun isValidKey(key : String) : Boolean {
        return keywords.contains(key)
    }*/

    fun add(node : Node, id : String) {
        table.put(id, node)
    }

    fun lookupSymbol(identifier: String): Node?{

        if(table.containsKey(identifier)){
            return table[identifier]
        }

        if(parent == null){
            return null
        }

        return parent.lookupSymbol(identifier)
    }

    /* Returns false if declaration failed */
    /*fun declareVariable (identifier: String, node: Node): Boolean{

    }
    */
}
