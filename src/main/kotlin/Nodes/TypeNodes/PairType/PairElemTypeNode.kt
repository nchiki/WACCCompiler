package Nodes.PairType

import BasicParser
import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import main.kotlin.ValueTable
import src.main.kotlin.Nodes.ExprNode


open class PairElemTypeNode(val type: ExprNode?, val pair: String?, override val ctx: BasicParser.PairElemTypeContext) : ExprNode {

    override var symbolTable: SymbolTable? = null

    override val size: Int
        get() {
            return type?.size ?: 4
        }

    override val weight: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun generateCode(codeGenerator: CodeGenerator) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun optimise(valueTable: ValueTable): Node {
        return this
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        this.symbolTable = table
    }

    override fun getBaseType(): LitTypes {
        if (pair == "") {
            return type?.getBaseType()!!
        }
        return LitTypes.PairWacc
    }

}
