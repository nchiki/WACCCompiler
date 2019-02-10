package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.NonExistingVar
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ArrayElemNode
import kotlin.reflect.KClassifier

class LHS_Node(var type: Node, val id: String, val line: Int, val pos : Int ) : Node {

    fun getType(): KClassifier {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        val value = table.lookupSymbol(id)
        if( value == null || value is FunctionNode) {
            errors.addError(NonExistingVar(line, pos))
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
            errors.addError(IncompatibleTypes(line, pos))
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
