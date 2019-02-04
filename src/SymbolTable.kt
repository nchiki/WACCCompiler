import sun.reflect.generics.scope.Scope
import java.util.*

class SymbolTable {

    private val stack = Stack<List<Symbol>>()

}

class Symbol {

    

}

class ScopeTable (val parent: ScopeTable?){

    val table = emptyMap<String, Symbol>()

    fun lookupSymbol(identifier: String): Symbol?{

        if(table.containsKey(identifier)){
            return table[identifier]
        }

        if(parent == null){
            return null
        }

        return parent.lookupSymbol(identifier)
    }

    /* Returns false if declaration failed */
    fun declareVariable (identifier: String, symbol: Symbol): Boolean{

    }

}

enum class VariableTypes {
    BOOL,
    INTEGER,
    STRING,
    CHARACTER,
    ARRAY
}