package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode
import kotlin.system.exitProcess


class BaseNode(val type : String, override val ctx: ParserRuleContext?) : TypeNode, ExprNode {

    override fun getBaseType() : LitTypes {
        when(type){
            "int"    -> return LitTypes.IntWacc
            "char"   -> return LitTypes.CharWacc
            "bool"   -> return LitTypes.BoolWacc
            "string" -> return LitTypes.StringWacc
            "pair"   -> return LitTypes.PairWacc
        }
        return LitTypes.NonLitWacc
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //not needed
    }

}