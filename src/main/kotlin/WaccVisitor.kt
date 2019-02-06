import org.jetbrains.annotations.NotNull
import main.kotlin.Nodes.*

class WaccVisitor : BasicParserBaseVisitor<Node>() {

    override fun visitProg(ctx: BasicParser.ProgContext?): Node {
        //create symbol tables and initialize stuff etc.
        /*val globalTable = ScopeTable(null)
        val symbol_table = SymbolTable()
        symbol_table.addTable(globalTable)*/
        return ProgNode()
    }

    //IdentNode needs to be constructed with Identifier (constructor of IdentNode not done yet)
    override fun visitId(ctx: BasicParser.IdContext): Node {
        val id = ctx.IDENT().text
        /* uncomment when Symbol Table is done */
        // if (memory.containsKey(id)) {
            //return memory.get(id)
        //} else {
            //return 0;
        //}
        return IdentNode()
    }

    override fun visitFunc(ctx: BasicParser.FuncContext?): Node {
        TODO()
    }


    //IntNode needs to be constructed with val of int
    override fun visitIntLit(@NotNull ctx: BasicParser.IntLitContext): Node {
        val int_val = Integer.valueOf(ctx.INT_LIT().text)
        return IntLitNode()
    }

    override fun visitAssign(@NotNull ctx: BasicParser.AssignContext): Node {
        val id = visit(ctx.assignLHS())
        val value = visit(ctx.assignRHS())
        /* uncomment when symbol table is implemented */
        //memory.put(id, value);
        return AssignNode()
    }

    override fun visitArrayType(@NotNull ctx: BasicParser.ArrayTypeContext): Node {
        val type = visit(ctx.type())
        return ArrayTypeNode()
    }

    override fun visitPair_type(@NotNull ctx: BasicParser.Pair_typeContext): Node {
        val fst = visit(ctx.pairElemType(0))
        val snd = visit(ctx.pairElemType(1))
        return PairNode(fst, snd)
    }
}