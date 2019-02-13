package main.kotlin.Utils

import main.kotlin.Nodes.Node
import main.kotlin.Nodes.Statement.ExitStatNode
import main.kotlin.Nodes.Statement.ReturnStatNode
import main.kotlin.Nodes.Statement.StatListNode

enum class LitTypes(s: String) {
    IntWacc("int")
    , BoolWacc("bool")
    , PairWacc("pair")
    , StringWacc("string")
    , CharWacc("char")
    , FuncWacc("func")
    , IdentWacc("ident"),

    NonLitWacc("nonlit"),
    ArrayLit("arrayLit"),
    Null("null")

}

fun getType(s : String) : LitTypes {
    var input = s
    if (s.contains("pair")) {
        input = "pair"
    }
    return when (input) {
        "bool" -> LitTypes.BoolWacc
        "int" -> LitTypes.IntWacc
        "string" -> LitTypes.StringWacc
        "char" -> LitTypes.CharWacc
        "pair" -> LitTypes.PairWacc
        "ident" -> LitTypes.IdentWacc
        "arrayLit" -> LitTypes.ArrayLit
        else -> LitTypes.NonLitWacc

    }
}

    fun hasReturnStat(stats: StatListNode) : Boolean {
        for (stat in stats.listStatNodes) {
            if(stat is ReturnStatNode) {
                return true
            }
        }
        return false
    }

    fun hasExitStat(stats : StatListNode) : Boolean {
        for (stat in stats.listStatNodes) {
            if (stat is ExitStatNode) {
                return true
            }
        }
        return false
    }
