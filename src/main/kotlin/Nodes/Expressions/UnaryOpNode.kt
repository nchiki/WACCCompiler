package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.reflect.KClassifier

class UnaryOpNode(val operand: Node, val operator: BasicParser.UnaryOperContext) : ExprNode {
    override fun getType(): BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (operator.ruleIndex == BasicParser.NOT && operand !is BoolLitNode
            || operator.ruleIndex == BasicParser.MINUS && operand !is IntLitNode
            || operator.ruleIndex == BasicParser.LEN && operand !is ArrayTypeNode
            || operator.ruleIndex == BasicParser.ORD && operand !is CharLitNode
            || operator.ruleIndex == BasicParser.CHR && operand !is IntLitNode)
        {
            errors.addError(InvalidOperandTypes(operator.start.line, operator.start.charPositionInLine))
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}