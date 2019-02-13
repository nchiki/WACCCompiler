package main.kotlin.Nodes

import Errors.InvalidOperandTypes
import Errors.UndefinedVariable
import main.kotlin.ErrorLogger
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import org.antlr.v4.runtime.ParserRuleContext
import src.main.kotlin.Nodes.ExprNode

class BinaryOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BinaryOperContext, override val ctx: ParserRuleContext) : ExprNode {

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

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {

        left.semanticCheck(errors, table)
        right.semanticCheck(errors, table)
        var leftType = left.getType()
        if (left is IdentNode) {
            val leftValue = table.lookupSymbol(left.id)
            if (leftValue == null) {
                errors.addError(UndefinedVariable(ctx, left.id))
                return
            }
            leftType = leftValue.getType()
        }
        var rightType = right.getType()
        if (right is IdentNode) {
            val rightValue = table.lookupSymbol(right.id)
            if (rightValue == null) {
                errors.addError(UndefinedVariable(ctx, right.id))
                return
            }
            rightType = rightValue.getType()
        }

        if (getType() == LitTypes.IntWacc) {
            if (leftType != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes((ctx as BasicParser.BinOperContext).expr(0), "INT", left, table))
            }
            if (rightType != LitTypes.IntWacc) {
                errors.addError(IncompatibleTypes((ctx as BasicParser.BinOperContext).expr(1), "INT", right, table))
            }
        } else if (getType() == LitTypes.BoolWacc) {
            if (leftType != LitTypes.BoolWacc) {
                errors.addError(IncompatibleTypes((ctx as BasicParser.BinOperContext).expr(0), "BOOL", left, table))
            }
            if (rightType != LitTypes.BoolWacc) {
                errors.addError(IncompatibleTypes((ctx as BasicParser.BinOperContext).expr(1), "BOOL", right, table))
            }
        }


//        if ((operator.MULT() != null
//                        || operator.DIV() != null
//                        || operator.MOD() != null
//                        || operator.MINUS() != null
//                        || operator.PLUS() != null)) {
//            if ((leftType != LitTypes.IntWacc || rightType != LitTypes.IntWacc)) {
//                errors.addError(InvalidOperandTypes(ctx))
//            }
//            if ((left is IdentNode && table.lookupSymbol(left.id) is ArrayLitNode) || (right is IdentNode && table.lookupSymbol(right.id) is ArrayLitNode)) {
//                errors.addError(InvalidOperandTypes(ctx))
//            }
//        }
//        else if ((operator.LESS() != null
//                        || operator.LESS_EQ() != null
//                        || operator.GREAT() != null
//                        || operator.GREAT_EQ() != null) &&
//                (leftType != LitTypes.CharWacc
//                        || leftType != LitTypes.IntWacc || rightType != LitTypes.CharWacc || rightType != LitTypes.IntWacc)) {
//            if (left is IdentNode || right is IdentNode) {
//                if (left is IdentNode) {
//                    val value = table.lookupSymbol(left.id)
//                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
//                                    && value.getType() != LitTypes.IntWacc)) {
//                        errors.addError(InvalidOperandTypes(ctx))
//                    }
//
//                }
//                if (right is IdentNode) {
//                    val value = table.lookupSymbol(right.id)
//                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
//                                    && value.getType() != LitTypes.IntWacc)) {
//                        errors.addError(InvalidOperandTypes(ctx))
//                    }
//                }
//            } else {
//                errors.addError(InvalidOperandTypes(ctx))
//            }
//
//        }

    }

}

class BoolOpNode(val left: ExprNode, val right: ExprNode, val operator: BasicParser.BoolOpContext, override val ctx: ParserRuleContext) : ExprNode {

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        if(left.getType() != LitTypes.BoolWacc || right.getType() != LitTypes.BoolWacc) {
            if (left is IdentNode || right is IdentNode) {
                if (left is IdentNode) {
                    val value = table.lookupSymbol(left.id)
                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                    && value.getType() != LitTypes.BoolWacc)) {
                        errors.addError(InvalidOperandTypes(ctx, "BOOL", value!!.getType().name))
                    }

                }
                if (right is IdentNode) {
                    val value = table.lookupSymbol(right.id)
                    if (value == null || value is ArrayTypeNode || (value.getType() != LitTypes.CharWacc
                                    && value.getType() != LitTypes.BoolWacc)) {
                        errors.addError(InvalidOperandTypes(ctx, "BOOL", value!!.getType().name))
                    }
                }
            } else {
                errors.addError(InvalidOperandTypes(ctx, "BOOL", left.getType().name))
            }
        }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getType(): LitTypes {
        return LitTypes.BoolWacc
    }

}