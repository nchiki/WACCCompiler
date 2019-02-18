package main.kotlin.Nodes.Statement

import main.kotlin.CodeGeneration
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class SkipNode(override val ctx: BasicParser.SkipContext): Node{

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGeneration: CodeGeneration) {}

    override fun translate() {}

    override fun getType() : LitTypes {
        return LitTypes.NonLitWacc
    }

    override fun syntaxCheck() {}

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {}
}