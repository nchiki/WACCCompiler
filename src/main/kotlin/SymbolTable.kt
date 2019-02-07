package main.kotlin
import Errors.NotBoolConditionError
import Nodes.BoolLitNode
import main.kotlin.Nodes.Node

class SymbolTable{

    private var table = ScopeTable(null)

    /*                   UTILS                           */

    fun boolExprCheck(expr : Node, errors: ErrorLogger) {
        if (expr !is BoolLitNode) {
            errors.addError(NotBoolConditionError())
        }
    }

    /* |||||||||||||||||||||||||||||||||||||||||||||||||| */

    fun getTable(): ScopeTable {
        return table
    }

    fun newScope (){
        var newTable = ScopeTable(table)
        table = newTable;
    }

    fun leaveScope () {
        if(table.parent != null){
            table = table.parent!!
        }else{
            throw Exception("Unable to leave global scope!")
        }
    }

    fun lookupSymbol(identifier: String) : Node?{
        return table.lookupSymbol(identifier)
    }

}

class ScopeTable (val parent: ScopeTable?){

    final val keywords = arrayOf("char", "int", "ord", "len", "bool") //finish filling out
    private val children = listOf<SymbolTable>()
    private val parentT = parent
    val table = emptyMap<String, Node>()


    fun isValidKey(key : String) : Boolean {
        return keywords.contains(key)
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
