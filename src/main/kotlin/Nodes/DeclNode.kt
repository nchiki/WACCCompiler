package Nodes

import Nodes.PairType.PairNode
import main.kotlin.ErrorLogger
import main.kotlin.Errors.DoubleDeclare
import main.kotlin.Errors.IncompatibleTypes
import main.kotlin.Errors.UndefinedVariable
import main.kotlin.Nodes.*
import main.kotlin.Nodes.TypeNodes.TypeNode
import main.kotlin.SymbolTable
import main.kotlin.Utils.LitTypes


class DeclNode(// var name
        val id: String, // type of var
        val type: TypeNode, // assigned rhs
        val rhs: RHS_Node, override val ctx : BasicParser.DeclContext) : Node {

    override fun getType() : LitTypes {
        return type.getType()
    }


    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        
        // looks up the id in the symbol table
        val value = table.lookupLocal(id)

        //if it's not there or there is a function with the same name, don't add an error
        if (value != null) {
            // if there is already a variable with that name -> error
            errors.addError(DoubleDeclare(ctx, id, value.ctx!!.start.line))
        }

        addToTable(table, id)
        rhs.semanticCheck(errors, table)

        /* RHS is a pair assignment*/
        if (rhs.type == RHS_type.pair_elem) {
            val nodeT = checkType(table, (rhs.PairLit!!.expr as IdentNode).id,rhs.PairLit)

            if(nodeT != type.getType()) {
                errors.addError(IncompatibleTypes(ctx, type.getType().toString(), rhs.PairLit.expr, table))
            }
            return
        }

        /* Easy declaration */
        if(type.getType().equals(rhs.getType())){
            return
        }

        /* RHS Could be an empty array */
        if(type is ArrayTypeNode){
            if(rhs.getType().equals(LitTypes.ArrayLit)){
                return
            }
        }

        if (rhs.type == RHS_type.array_lit ) {
            val list = rhs.ArrayLit!!.exprList
            if (list.count() > 0) {
                if(list[0].getType() == type.getType()) {
                    return
                }
                if(list[0].getType() == LitTypes.IdentWacc) {
                    val typeId = table.lookupSymbol((list[0] as IdentNode).id)?.getType()
                    if(typeId == type.getType()) {
                        return
                    }
                }

            } else {
                return
            }

        }

        /* RHS can only be an identifier to something now */
        val realType = rhs.returnIdentType(table)

        if(realType == null){
            errors.addError(UndefinedVariable(rhs.ctx, id))
            return
        }

        /* Type Match */
        if(type.getType().equals(realType)){
            return
        }

        /* Types don't match */
        errors.addError(IncompatibleTypes(ctx, type.getType().toString(), rhs, table))
    }

    fun checkType(table:SymbolTable, id:String, node :Node) :LitTypes {

        if (node is PairElemNode) {
            val elem = node.elem
            val node = (table.lookupSymbol((node.expr as IdentNode).id))
            if(node is PairNode) {
                val node = node.returnElemNode(elem)
                if (node != LitTypes.IdentWacc) {
                    return node
                }
            } else if(node is IdentNode){
                var n = node
                while (n !is PairNode) {

                    n = (table.lookupSymbol((n as IdentNode).id))
                }
                return n.returnElemNode(elem)
            }
        } else if (node.getType() == LitTypes.IdentWacc) {

            val elem = node as IdentNode
            val node = (table.lookupSymbol(elem.id))
            if (node is PairElemNode) {
                return checkType(table, (node.expr as IdentNode).id, node)
            } else if (node is IdentNode) {
                return checkType(table, (node).id, node)
            }
        }
        return node.getType()

    }

    fun addToTable(table: SymbolTable, id:String) {
        table.add(type, id)
    }

}


