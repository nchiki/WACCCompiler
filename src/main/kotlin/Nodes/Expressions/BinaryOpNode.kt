package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.reflect.KClassifier

class BinaryOpNode(left : Node, right: Node, operator: BasicParser.BinaryOperContext) : ExprNode {
    override fun getType(): KClassifier {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val left = left
    val right = right
    val operator = operator

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (left.getType() != right.getType()) {
            errors.addError(InvalidOperandTypes())
        }
        if ((operator.ruleIndex == BasicParser.MULT
                        || operator.ruleIndex == BasicParser.DIV
                        || operator.ruleIndex == BasicParser.MOD
                        || operator.ruleIndex == BasicParser.MINUS
                        || operator.ruleIndex == BasicParser.PLUS)
                && left !is IntLitNode) {
            errors.addError(InvalidOperandTypes())
        }

    }
}