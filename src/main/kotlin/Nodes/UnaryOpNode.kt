package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable

class UnaryOpNode(operand: Node, operator: BasicParser.UnaryOperContext) : Node {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if (operator == BasicParser.NOT && !(operand is BinaryOpNode)) {
            errors.addError(InvalidOperandTypes())
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}