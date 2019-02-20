package main.kotlin.Utils


interface LiteralDefs {
    fun getString() : String
    fun getLength() : Int
}

class StringLitDef(val str : String) : LiteralDefs{
    override fun getLength() : Int{
        return str.replace("\\", "").replace("\"", "").length
    }

    override fun getString() : String{
        return str
    }
}

class NewLineDef(val str : String) : LiteralDefs{
    override fun getLength() : Int{
        return 1
    }

    override fun getString() : String{
        return str
    }

}

class StringAppendDef(val str : String) : LiteralDefs{
    override fun getLength() : Int{
        return 5
    }

    override fun getString() : String{
        return str
    }

}