package src

import Nodes.Node

class SymbolTable (val parent: SymbolTable?){

    final val keywords = arrayOf("char", "int", "ord", "len") //finish filling out
    private val children = listOf<SymbolTable>()
    private val parentT = parent
    val table = emptyMap<String, Node>()

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
