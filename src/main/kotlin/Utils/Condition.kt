package main.kotlin.Utils

enum class Condition(s: String) {
    EQ("EQ"), NE("NE"), CS("CS"), CC("CC"), MI("MI"), PL("PL"),
    VS("VS"), VC("VC"), HI("HI"), LS("LS"), GE("GE"), LT("LT"),
    GT("GT"), LE("LE"), AL("AL"), NULL("")
}