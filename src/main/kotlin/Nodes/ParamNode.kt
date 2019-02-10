package Nodes

import Errors.VarAlreadyDeclaredError
import Nodes.Literals.PairLitNode
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.*
import main.kotlin.Nodes.Literals.BoolLitNode
import main.kotlin.Nodes.Statement.ArgListNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes
import src.main.kotlin.Nodes.Literals.IntLitNode
import kotlin.reflect.KClass

class ParamNode(
        val id: String,
        val type: Node, val ctx: BasicParser.ParamContext) : Node {



    fun getType() : LitTypes{
        if(type is IntLitNode) {
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
       }
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        // looks up the id in the symbol table
        val Value = table.lookupSymbol(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (Value != null && !(Value is FunctionNode)) {
            // if there is already a variable with that name -> error
            errors.addError(VarAlreadyDeclaredError(ctx.start.line, ctx.start.charPositionInLine))
        }

        type.semanticCheck(errors, table) // checks semantics on the type (valid type)
    }
}