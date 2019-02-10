package main.kotlin.Nodes.Statement

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

<<<<<<< HEAD:src/main/kotlin/Nodes/StatementNode.kt
class StatementNode : Node {
    override fun getType() : BaseNode {
=======
class StatementNode(val stat: Node): Node {

    override fun getType() : KClass<StatementNode> {
>>>>>>> 6477bf17318b9dad3399fb2a5c134b0e34319263:src/main/kotlin/Nodes/Statement/StatementNode.kt
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}