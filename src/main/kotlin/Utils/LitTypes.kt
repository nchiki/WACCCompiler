package main.kotlin.Utils

enum class LitTypes(s: String) {
    IntWacc("int")
    ,
    BoolWacc("bool")
    ,
    PairWacc("pair")
    ,
    StringWacc("string")
    ,
    CharWacc("char")
    ,
    FuncWacc("func")
    ,
    IdentWacc("ident"),

    NonLitWacc("nonlit"),
    ArrayLit("arrayLit"),
    Null("null")
}


    fun getString(type: LitTypes): String {
        return when (type) {
            LitTypes.BoolWacc -> "bool"
            LitTypes.IntWacc -> "int"
            LitTypes.StringWacc -> "string"
            LitTypes.CharWacc -> "char"
            LitTypes.PairWacc -> "pair"
            LitTypes.IdentWacc -> "ident"
            LitTypes.ArrayLit -> "arrayLit"
            else -> "nonlit"
        }
    }

    fun getType(s: String): LitTypes {
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
}

fun getTypeSize(type : LitTypes) : Int {
    return when (type) {
        LitTypes.IntWacc -> 4
        LitTypes.CharWacc -> 1
        LitTypes.BoolWacc -> 1
        else -> 4
    }
}
