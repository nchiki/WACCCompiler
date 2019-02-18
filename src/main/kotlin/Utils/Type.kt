package main.kotlin.Utils

interface Type {

    fun getBaseType(): LitTypes

    override fun equals(other: Any?): Boolean

}
