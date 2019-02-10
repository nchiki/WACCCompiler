package main.kotlin.Utils

enum class LitTypes(s: String) {
    IntWacc("int")
    , BoolWacc("bool")
    , PairWacc("bool")
    , StringWacc("string")
    , CharWacc("char")
    , FuncWacc("bool")
    , IdentWacc("bool"),
    NonLitWacc("bool");
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