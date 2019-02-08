<<<<<<< HEAD
package main.kotlin.Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass


class BoolLitNode : Node {

    override fun getType() : KClass<BoolLitNode> {
=======
package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

class BoolLitNode(bool : Boolean): Node {

    override fun syntaxCheck() {
>>>>>>> c25ca56c5ab060f91a822b53516714785e95aa11
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
<<<<<<< HEAD

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
=======
}
>>>>>>> c25ca56c5ab060f91a822b53516714785e95aa11
