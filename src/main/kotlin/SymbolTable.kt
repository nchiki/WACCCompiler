package main.kotlin

import Errors.NotBoolConditionError
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Node

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


    fun boolExprCheck(expr : Node, errors: ErrorLogger, ctx : BasicParser.ExprContext) {
        if (expr !is BoolLitNode) {
            errors.addError(NotBoolConditionError(ctx.start.line, ctx.start.charPositionInLine))
        }
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
