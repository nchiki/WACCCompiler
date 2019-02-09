package main.kotlin.Nodes

import main.kotlin.ErrorLogger
import main.kotlin.Nodes.PairType.Pair_Fst
import main.kotlin.Nodes.PairType.Pair_Snd
import main.kotlin.SymbolTable
import kotlin.reflect.KClass

class PairNode(fstNode : Pair_Fst, sndNode: Pair_Snd) : Node{
    override fun getType() : KClass<PairNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun syntaxCheck() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun semanticCheck(errors: ErrorLogger, table: SymbolTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}