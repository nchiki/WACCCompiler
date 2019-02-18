package Nodes.Literals

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.BaseNode
import main.kotlin.Nodes.Node
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ExprNode
import kotlin.reflect.KClassifier

class PairLitNode(override val ctx : BasicParser.PairLitContext): ExprNode {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

    }

    override fun getType(): LitTypes {
        return LitTypes.PairWacc
    }

}