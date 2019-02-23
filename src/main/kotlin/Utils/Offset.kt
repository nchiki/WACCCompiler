package main.kotlin.Utils

import main.kotlin.Utils.Register

class Offset(val reg: Register, val offset: Int) {

    override fun toString(): String {
        return "[$reg, #$offset]"
    }

}