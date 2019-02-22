package main.kotlin

import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expression.ParenNode
import main.kotlin.Errors.GenericError
import src.main.kotlin.Nodes.ExprNode


class SymbolTable (val parent: SymbolTable?){

    var table = HashMap<String, ExprNode>()

    val addressMap = HashMap<String, Int>()

    var functions = HashMap<String, FunctionNode>()
    var errors = ErrorLogger()

    var currentFunction: FunctionNode? = null
    var currentExecutionPathHasReturn = false

    fun addToFunctions(funcs : List<FunctionNode>) {
        for (func in funcs) {
            if (functions.containsKey(func.id)) {
                errors.addError(GenericError("Function already declared"))
            }
            functions.put(func.id, func)
        }
    }

    /* Declares variable at the address */
    fun declareVariable(identifier: String, size: Int, address: Int) {
        if(!table.containsKey(identifier)){
            parent!!.declareVariable(identifier, size, address)
            return
        }
        addressMap[identifier] = address
    }

    /* Returns the address of the value,
     if the (address <= sp) then value is in stack, otherwise it's in the heap
     */
    fun getValueAddress(identifier: String) : Int {
        if(!table.containsKey(identifier)){
            return parent!!.getValueAddress(identifier)
        }

        return addressMap[identifier]!!
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

    fun add(node : ExprNode, id : String) {
        table.put(id, node)
    }

    fun lookupLocal(identifier:String) : ExprNode?{
        if(table.containsKey(identifier)){
            return table[identifier]
        }
        return null
    }

    fun lookupSymbol(identifier: String): ExprNode? {

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
