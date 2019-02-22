package main.kotlin.Errors

import main.kotlin.Utils.LiteralDefs

object OverflowError : LiteralDefs {
    override fun getString(): String {
        return "\"OverflowError: the result is too small/large to store in a 4-byte signed-integer.\n\""
    }

    override fun getLength(): Int {
        return 82
    }
}

