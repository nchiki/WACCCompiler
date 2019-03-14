package main.kotlin

import main.kotlin.Nodes.*
import main.kotlin.Nodes.Expression.ParenNode
import main.kotlin.Errors.GenericError
import main.kotlin.Instructions.AddInstr
import main.kotlin.Utils.Register
import src.main.kotlin.Nodes.ExprNode


class SymbolTable (val parent: SymbolTable?){

    var table = HashMap<String, ExprNode>()

    val addressMap = HashMap<String, Int>()

    var highOrderFunctions = ArrayList<String>()
    var functions = HashMap<String, FunctionNode>()
    var functionsToHO = HashMap<String, String>() // maps high order functions to its function parameter
    var errors = ErrorLogger()

    var currentFunction: FunctionNode? = null
    var currentExecutionPathHasReturn = false
    var sp = 0

    var inHighOrderFunction : Pair<Boolean, FunctionNode?> = Pair(false, null)

    // useful for break and continue semantic check
    var inLoop = false

    fun addToFunctions(funcs : List<FunctionNode>) {
        for (func in funcs) {
            if (functions.containsKey(func.id)) {
                errors.addError(GenericError("Function already declared"))
            }
            functions.put(func.id, func)
        }
    }

    /* Declares variable at the address */
    fun declareVariable(identifier: String, offset: Int, size: Int) {
        if(!table.containsKey(identifier) && parent != null){
            parent!!.declareVariable(identifier, offset, size)
            return
        }
        addressMap[identifier] = offset + size
    }

    /* Returns the address of the value,
     if the (address <= sp) then value is in stack, otherwise it's in the heap
     */
    fun getValueOffset(identifier: String, codeGenerator: CodeGenerator) : Int {
        if(!addressMap.containsKey(identifier)){
            return sp + parent!!.getValueOffset(identifier, codeGenerator)
        }
        return sp - addressMap[identifier]!!
    }

    fun getFunction(funcId : String) : FunctionNode?{
        var tab = this
        if (functions.isEmpty()) {
            while(tab.functions.isEmpty()) {
                if(tab.parent != null) {
                    tab = tab.parent!!
                } else {
                    break
                }
            }
            if (parent == null) {
                return null
            }
        }
        return tab.functions.get(funcId)
    }

    fun isHigherOrderFunction(funId : String) : Boolean{
        var tab = this
        if (highOrderFunctions.isEmpty()) {
            while(tab.highOrderFunctions.isEmpty()) {
                tab = tab.parent!!
            }
            if (parent == null) {
                return false
            }
        }
        return tab.highOrderFunctions.contains(funId)
    }

    fun evaluateParenNode(node_: Node): Node{
        var node = node_
        while(node is ParenNode){
            node = node.expr
        }
        return node
    }


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

    fun recoverSp(codeGenerator: CodeGenerator) {
        //checks if we have loaded any variable to memory in current scope so
        // sp has decreased, and adds the offset to the sp
        if(sp > 0) {

            var value = sp
            sp -= value
            while(value > 1024) {
                codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(Register.sp, Register.sp, 1024))
                value -= 1024
            }
            codeGenerator.addInstruction(codeGenerator.curLabel, AddInstr(Register.sp, Register.sp, value))
        }
    }

    fun addMatchFunctions(highOrder : String, functionParam : String) {
        functionsToHO.put(highOrder, functionParam)
        var tab = parent
        while(tab != null) {
            tab.functionsToHO.put(highOrder, functionParam)
            tab = tab.parent
        }
    }

    fun lookForFunctionParam(highOrder : String) : String? {

        if(functionsToHO.containsKey(highOrder)){
            return functionsToHO[highOrder]
        }

        if(parent == null){
            return null
        }

        return parent.lookForFunctionParam(highOrder)
    }
}
