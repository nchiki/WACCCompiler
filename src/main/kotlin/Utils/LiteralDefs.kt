package main.kotlin.Utils


interface LiteralDefs {
    fun getString() : String
    fun getLength() : Int
}

//type of a literal string to be printed in data section
class StringLitDef(val str : String) : LiteralDefs{
    override fun getLength() : Int{
        return str.replace("\\", "").replace("\"", "").length
    }
    override fun getString() : String{
        return str
    }
}

class PairDef : LiteralDefs {
    override fun getString(): String {
        return "\"%p\\0\""
    }

    override fun getLength(): Int {
        return 3
    }

}

object NullReferDef : LiteralDefs {
    override fun getString(): String {
        return "\"NullReferenceError: dereference a null reference\\n\\0\""
    }

    override fun getLength(): Int {
        return 50
    }

}


object OverflowDef : LiteralDefs {
    override fun getString(): String {
        return "\"OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n\""
    }

    override fun getLength(): Int {
        return 82
    }
}

object DivZeroDef : LiteralDefs {
    override fun getString(): String {
        return "\"DivideByZeroError: divide or modulo by zero\\n\\0\""
    }

    override fun getLength(): Int {
        return 45
    }

}

class FalseDef : LiteralDefs {
    override fun getString(): String {
        return "\"false\\0\""
    }

    override fun getLength(): Int {
        return 6
    }
}

class TrueDef : LiteralDefs {
    override fun getString(): String {
        return "\"true\\0\""
    }

    override fun getLength(): Int {
        return 5
    }
}

class NewLineDef : LiteralDefs {
    override fun getString(): String {
        return "\"\\0\""
    }

    override fun getLength(): Int {
        return 1
    }
}

class ReadIntApp : LiteralDefs {
    override fun getString(): String {
        return "\"%d\\0\""
    }

    override fun getLength(): Int {
        return 3
    }
}

class ReadCharApp : LiteralDefs {
    override fun getString(): String {
        return "\"%c\\0\""
    }

    override fun getLength(): Int {
        return 4
    }
}

class IntAppendDef : LiteralDefs {
    override fun getString(): String {
        return "\"%d\\0\""
    }
    override fun getLength(): Int {
        return 3
    }
}

//classifier of string to be printed in data section
class StringAppendDef : LiteralDefs{
    override fun getLength() : Int{
        return 5
    }
    override fun getString() : String{
        return "\"%.*s\\0\""
    }
}
