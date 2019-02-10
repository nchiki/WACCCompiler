package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class UnaryOpNode(val operand: ExprNode, val operator: BasicParser.UnaryOperContext, type: Any) : ExprNode {

    override fun getType(): LitTypes {
       return operand.getType()
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (operator.ruleIndex == BasicParser.NOT && operand !is BoolLitNode
            || operator.ruleIndex == BasicParser.MINUS && operand !is IntLitNode
            || operator.ruleIndex == BasicParser.LEN && operand !is ArrayElemNode
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