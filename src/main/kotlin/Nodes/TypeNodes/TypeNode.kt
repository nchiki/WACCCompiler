package main.kotlin.Nodes.TypeNodes

import main.kotlin.Nodes.Node
import main.kotlin.Utils.LitTypes


interface TypeNode : Node {

    fun getBaseType() : LitTypes

}