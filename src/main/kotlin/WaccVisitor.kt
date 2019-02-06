import org.jetbrains.annotations.NotNull
import main.kotlin.Nodes.*

class WaccVisitor : BasicParserBaseVisitor<Node>() {

    override fun visitProg(@NotNull ctx: BasicParser.ProgContext): Node {
        val funcCtx = ctx.func()
        val funcList: MutableList<FunctionNode> = mutableListOf()
        for (func in funcCtx) {
            funcList.add(visitFunc(func))
        }
        return ProgNode(funcList)
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

    override fun visitFunc(@NotNull ctx: BasicParser.FuncContext): Node {
        val params = ctx.paramList().param()
        val paramList = visitParamList(params)
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
