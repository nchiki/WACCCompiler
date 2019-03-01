package src.main.kotlin.Nodes

import main.kotlin.Nodes.Node
import main.kotlin.Utils.LitTypes

interface ExprNode : Node {
    fun getBaseType(): LitTypes
    val size: Int
}
