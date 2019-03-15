package main.kotlin

import main.kotlin.Utils.BoolConstant
import main.kotlin.Utils.ConstantValue
import main.kotlin.Utils.IntConstant

class ValueTable(val parent: ValueTable?) {

    val table = HashMap<String, ConstantValue>()

    fun getValue(identifier: String): ConstantValue? {
        if (table.containsKey(identifier)) {
            return table.get(identifier)
        }

        if (parent == null) {
            return null
        }

        return parent.getValue(identifier)
    }

    fun addIntValue(identifier: String, value: Long) {
        table.put(identifier, IntConstant(value))
    }

    fun addBoolValue(identifier: String, value: Boolean) {
        table.put(identifier, BoolConstant(value))
    }

    fun setIntValue(identifier: String, value: Long) {
        if (table.containsKey(identifier)) {
            val newConstant = IntConstant(value)
            newConstant.dynamic = false
            table.replace(identifier, newConstant)
            return
        }

        if (parent != null) {
            parent.setIntValue(identifier, value)
        }
    }

    fun setBoolValue(identifier: String, value: Boolean) {
        if (table.containsKey(identifier)) {
            val newConstant = BoolConstant(value)
            newConstant.dynamic = false
            table.replace(identifier, newConstant)
            return
        }

        if (parent != null) {
            parent.setBoolValue(identifier, value)
        }
    }

    fun markAllAsDynamic() {
        for ((_, value) in table) {
            value.dynamic = true
        }

        if (parent != null) {
            parent.markAllAsDynamic()
        }
    }

    fun setDynamic(identifier: String, dynamic: Boolean) {
        val value = getValue(identifier)

        if (value == null) {
            return
        }

        value.dynamic = dynamic
    }

}