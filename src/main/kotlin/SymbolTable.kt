package main.kotlin
import Errors.NotBoolConditionError
import Nodes.BoolLitNode
import main.kotlin.Nodes.Node

class SymbolTable (val parent: SymbolTable?){

    final val keywords = arrayOf("char", "int", "ord") //finish filling out
    private val children = listOf<SymbolTable>()
    private val parentT = parent
    var table = HashMap<String, Node>()

    fun boolExprCheck(expr : Node, errors: ErrorLogger) {
        if (expr !is BoolLitNode) {
            errors.addError(NotBoolConditionError())
        }
    }

    fun isValidKey(key : String) : Boolean {
        return keywords.contains(key)
    }

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
