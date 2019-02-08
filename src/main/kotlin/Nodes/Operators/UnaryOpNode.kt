package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

class UnaryOpNode(operand: Node, operator: BasicParser.UnaryOperContext) : Node {

    val operand = operand
    val operator = operator

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (operator.ruleIndex == BasicParser.NOT && operand !is BoolLitNode
            || operator.ruleIndex == BasicParser.MINUS && operand !is IntLitNode
            || operator.ruleIndex == BasicParser.LEN && operand !is ArrayTypeNode
            || operator.ruleIndex == BasicParser.ORD && operand !is CharLitNode
            || operator.ruleIndex == BasicParser.CHR && operand !is IntLitNode)
        {
            errors.addError(InvalidOperandTypes())
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}