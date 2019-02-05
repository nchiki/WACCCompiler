import org.jetbrains.annotations.NotNull
import Nodes.Node
import java.util.*

class SymbolTable {

    private val stack = Stack<ScopeTable>()

    fun addTable(@NotNull table : ScopeTable){
        stack.push(table)
    }

}

class ScopeTable (val parent: ScopeTable?){

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

enum class VariableTypes {
    BOOL,
    INTEGER,
    STRING,
    CHARACTER,
    ARRAY
}