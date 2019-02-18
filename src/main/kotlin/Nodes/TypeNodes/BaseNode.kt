package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess


class BaseNode(val type : String, override val ctx: ParserRuleContext?) : TypeNode, ExprNode {

    override fun getType() : LitTypes {
        if (type == "int") {
            return LitTypes.IntWacc
        } else if (type == "char") {
            return LitTypes.CharWacc
        } else if (type == "bool") {
            return LitTypes.BoolWacc
        } else if (type == "string") {
            return LitTypes.StringWacc
        }else if (type == "pair") {
            return LitTypes.PairWacc
        } else {

            return LitTypes.NonLitWacc
        }
    }

    fun syntaxCheck() {

        if (type != "int" && type != "char" && type != "bool" && type != "string") {
            exitProcess(100)
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed (I think)
    }

}