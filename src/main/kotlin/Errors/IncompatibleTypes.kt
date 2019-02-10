package main.kotlin.Errors

import Errors.ErrorNode

class IncompatibleTypes(val line: Int, val pos: Int) : ErrorNode {

    override fun printError(): String {
        return "Incompatible types found in line: $line at position: $pos "
    }


}
