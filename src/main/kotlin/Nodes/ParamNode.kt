package Nodes

import Errors.DoubleDeclare
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.*
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes

class ParamNode(
        val id: String,
        val type: Node, override val ctx: BasicParser.ParamContext) : Node {

    override fun getType() : LitTypes{
        /*if(type is IntLitNode) {
           return LitTypes.IntWacc
       } else if (type is CharLitNode) {
           return LitTypes.CharWacc
       } else if (type is BoolLitNode) {
           return LitTypes.BoolWacc
       } else if (type is PairLitNode) {
           return LitTypes.PairWacc
       } else if (type is StringLitNode) {
           return LitTypes.StringWacc
       } else {
           return LitTypes.NonLitWacc
       }*/
        var v = type
        while (v is ArrayTypeNode) {
            v = v.type
        }
        val toType = v as BaseNode
        return toType.getType()
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // looks up the id in the symbol table
        val Value = table.lookupSymbol(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (Value != null && (Value !is FunctionNode)) {
            // if there is already a variable with that name -> error
            errors.addError(DoubleDeclare(ctx, id))
        } else {
            table.add(this, id)
        }

    }
}