package main.kotlin.Nodes

import keywords
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import kotlin.reflect.KClass
import kotlin.system.exitProcess


class BaseNode(val type : String) : Node {
    override fun getType() : KClass<BaseNode>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        if (type != "int" && type != "char" && type != "bool" && type != "string") {
            exitProcess(100)
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}