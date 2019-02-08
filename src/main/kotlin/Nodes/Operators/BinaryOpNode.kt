package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

class BinaryOpNode(left : Node, right: Node, operator: BasicParser.BinaryOperContext) : Node {

    val left = left
    val right = right
    val operator = operator

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
//        if(!typesAreSame) {
//            errors.addError(InvalidOperandTypes())
//        }
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