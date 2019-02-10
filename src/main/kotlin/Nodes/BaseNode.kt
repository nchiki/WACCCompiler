package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import kotlin.reflect.KClass
import kotlin.system.exitProcess


class BaseNode(val type : String) : Node {
    override fun getType() : BaseNode{
        return this
    }

    override fun syntaxCheck() {

        if (type != "int" && type != "char" && type != "bool" && type != "string") {
            exitProcess(100)
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed (I think)
    }

}