import Nodes.IdentNode
import org.jetbrains.annotations.NotNull
import Nodes.Node
import com.sun.source.tree.Scope
import java.util.*

import Nodes.Node

    private var table = ScopeTable(null)

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

    fun lookupSymbol(identifier: IdentNode){
        table.lookupSymbol(identifier)
    }

}

class ScopeTable (val parent: ScopeTable?){

    final val keywords = arrayOf("char", "int", "ord", "len") //finish filling out
    private val children = listOf<SymbolTable>()
    private val parentT = parent
    val table = emptyMap<String, Node>()

    fun lookupSymbol(identifier: IdentNode): Node?{

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
