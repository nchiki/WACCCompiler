package main.kotlin

import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expression.ParenNode
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Errors.GenericError
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Nodes.Expressions.BinaryOpNode
import src.main.kotlin.Nodes.ExprNode


class SymbolTable (val parent: SymbolTable?){

    private val children = listOf<SymbolTable>()
    private val parentT = parent
    var table = HashMap<String, Node>()
    var functions = HashMap<String, FunctionNode>()
    var errors = ErrorLogger()

    fun addToFunctions(funcs : List<FunctionNode>) {
        for (func in funcs) {
            if (functions.containsKey(func.id)) {
                errors.addError(GenericError("Function already declared"))
            }
            functions.put(func.id, func)
        }
    }

    fun printFunctions() {
        for (func in functions) {
            println(func.key)
        }
    }

    fun getFunction(funcId : String) : FunctionNode?{
        var tab = this
        if (functions.isEmpty()) {
            while(tab.functions.isEmpty()) {
                tab = tab.parent!!
            }
            if (parent == null) {
                return null
            }
        }
        return tab.functions.get(funcId)
    }

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
                errors.addError(UndefinedVariable(tempExpr.ctx!!, tempExpr.id))
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

        errors.addError(IncompatibleTypes(tempExpr.ctx!!, "BOOL", expr, this))
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

    fun lookupLocal(identifier:String) : Node?{
        if(table.containsKey(identifier)){
            return table[identifier]
        }
        return null
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
