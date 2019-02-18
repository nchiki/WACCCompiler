package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ArrayElemNode
import src.main.kotlin.Nodes.ExprNode
import src.main.kotlin.Nodes.Literals.IntLitNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BinaryOperContext, override val ctx: ParserRuleContext) : ExprNode {

    //differs between a Boolean expression or calculation of two operands
    override fun getType(): LitTypes {
        if(operator.MULT() != null
                || operator.DIV() != null
                || operator.MOD() != null
                || operator.MINUS() != null
                || operator.PLUS() != null) {
            return LitTypes.IntWacc
        } else {
            return LitTypes.BoolWacc
        }
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        //check the semantics of both operands
            left.semanticCheck(errors, table)
            right.semanticCheck(errors, table)

        //get lines and positions for errors
            val line = operator.start.line
            val pos = operator.start.charPositionInLine

        //check that left operand is valid
            var leftType = left.getType()
            if (left is IdentNode) {
                val leftValue = table.lookupSymbol(left.id)
                if (leftValue == null) {
                    errors.addError(UndefinedVariable(ctx, left.id))
                    return
                }
                leftType = leftValue.getType()
            }

        //check that right operand is valid
            var rightType = right.getType()
            if (right is IdentNode) {
                val rightValue = table.lookupSymbol(right.id)
                if (rightValue == null) {
                    errors.addError(UndefinedVariable(ctx, right.id))
                    return
                }
                rightType = rightValue.getType()
            }
            if (getType() == LitTypes.IntWacc && !leftType.equals(LitTypes.IntWacc)) {
                errors.addError(IncompatibleTypes(ctx, "INT", left, table))
            } else if (getType() == LitTypes.IntWacc && !rightType.equals(LitTypes.IntWacc)) {
                errors.addError(IncompatibleTypes(ctx, "INT", right, table))
            } else if (!leftType.equals(rightType)) {
                var l = left as Node?
                while (leftType == LitTypes.IdentWacc) {
                    l = table.lookupSymbol((l as IdentNode).id)
                    if (l != null) {
                        leftType = l.getType()
                    } else {
                        break
                    }
                }
                var r = right as Node?
                while (rightType == LitTypes.IdentWacc) {
                    r = table.lookupSymbol((r as IdentNode).id)
                    if (r != null) {
                        rightType = r.getType()
                    } else {
                        break
                    }
                }
                if (leftType != rightType) {
                    errors.addError(IncompatibleTypes(ctx, leftType.toString(), right, table))
                }
            }

        //check if operators are valid and compatible with types of operands
            if ((operator.MULT() != null
                            || operator.DIV() != null
                            || operator.MOD() != null
                            || operator.MINUS() != null
                            || operator.PLUS() != null)) {
                if ((leftType != LitTypes.IntWacc || rightType != LitTypes.IntWacc)) {
                    errors.addError(InvalidOperandTypes(ctx))
                }
                if ((left is IdentNode && table.lookupSymbol(left.id) is ArrayLitNode)
                        || (right is IdentNode && table.lookupSymbol(right.id) is ArrayLitNode)) {
                    errors.addError(InvalidOperandTypes(ctx))
                }
            }
            else if ((operator.LESS() != null
                            || operator.LESS_EQ() != null
                            || operator.GREAT() != null
                            || operator.GREAT_EQ() != null) &&
                    (leftType != LitTypes.CharWacc
                            || leftType != LitTypes.IntWacc || rightType != LitTypes.CharWacc
                            || rightType != LitTypes.IntWacc)) {

                if (left is IdentNode || right is IdentNode) {
                    if (left is IdentNode) {
                        val value = table.lookupSymbol(left.id)
                        if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                        && value.getType() != LitTypes.IntWacc)) {
                            errors.addError(InvalidOperandTypes(ctx))
                        }

                    }

                    if (right is IdentNode) {
                        val value = table.lookupSymbol(right.id)
                        if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                        && value.getType() != LitTypes.IntWacc)) {
                            errors.addError(InvalidOperandTypes(ctx))
                        }
                    }
                }

                //else operands have to be invalid
                else {
                    errors.addError(InvalidOperandTypes(ctx))
                }
            }
    }
}

class BoolOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BoolOpContext,
                 override val ctx: ParserRuleContext) : ExprNode {
    override fun syntaxCheck() {
        //not needed for BoolOpNode
    }


    //checks whether
    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(left.getType() != LitTypes.BoolWacc || right.getType() != LitTypes.BoolWacc) {

            //checks that both identifiers are bools
            if (left is IdentNode || right is IdentNode) {
                if (left is IdentNode) {
                    val value = table.lookupSymbol(left.id)
                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                    && value.getType() != LitTypes.BoolWacc)) {
                        errors.addError(InvalidOperandTypes(ctx))
                    }
                }

                if (right is IdentNode) {
                    val value = table.lookupSymbol(right.id)
                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                    && value.getType() != LitTypes.BoolWacc)) {
                        errors.addError(InvalidOperandTypes(ctx))
                    }
                }
            }

            //if operands could not be identified, throw Invalid Operand Error
            else {
                errors.addError(InvalidOperandTypes(ctx))
            }
        }
    }


    //returns type of Node
    override fun getType(): LitTypes {
        return LitTypes.BoolWacc
    }

}