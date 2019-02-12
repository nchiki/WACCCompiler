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

fun getType(s : String) : LitTypes{
    return when (s) {
        "bool" -> LitTypes.BoolWacc
        "int" -> LitTypes.IntWacc
        "string" -> LitTypes.StringWacc
        "char" -> LitTypes.CharWacc
        else -> LitTypes.NonLitWacc
}
}