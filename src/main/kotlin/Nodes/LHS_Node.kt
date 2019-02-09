package kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Nodes.FunctionNode
import main.kotlin.Nodes.IdentNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ArrayElemNode
import kotlin.Errors.NonExistingVar
import kotlin.reflect.KClassifier

class LHS_Node(type : Node, id :String) : Node {
    var type = type
    val id = id

    override fun getType(): KClassifier {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val value = table.lookupSymbol(id)
        if( value == null || value is FunctionNode) {
            errors.addError(NonExistingVar())
        }
        checkType(value!!, errors, table)

    }

    fun checkType(typeNode : Node, errors: ErrorLogger, table: SymbolTable) {

        if( typeNode is IdentNode) {
            this.type = table.lookupSymbol(typeNode.id)!!

        } else if (typeNode is ArrayElemNode) {
            this.type = typeNode.type
        /*} else if (typeNode is PairSnd || typeNode is PairFst){
            this.type = typeNode.type
*/
        } else {
            errors.addError(IncompatibleTypes())
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
