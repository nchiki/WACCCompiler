package main.kotlin.Utils

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
        "pair"-> LitTypes.PairWacc
        "ident" -> LitTypes.IdentWacc
        "arrayLit" -> LitTypes.ArrayLit
        else -> LitTypes.NonLitWacc
    }
}
