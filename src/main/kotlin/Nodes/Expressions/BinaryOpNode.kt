package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BinaryOperContext, ctx: BasicParser.BinOperContext) : ExprNode {
    override val type = left.type

    fun getType(): BaseNode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (left.type != right.type) {
            errors.addError(InvalidOperandTypes(operator.start.line, operator.start.charPositionInLine))
        }
        if ((operator.ruleIndex == BasicParser.MULT
                        || operator.ruleIndex == BasicParser.DIV
                        || operator.ruleIndex == BasicParser.MOD
                        || operator.ruleIndex == BasicParser.MINUS
                        || operator.ruleIndex == BasicParser.PLUS)
                && left !is IntLitNode) {
            errors.addError(InvalidOperandTypes(operator.start.line, operator.start.charPositionInLine))
        }

    }
}