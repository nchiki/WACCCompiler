package src

import org.antlr.runtime.*
import org.antlr.runtime.tree.*

fun main(args: Array<String>){
    WACC().run()
}

class WACC {

    fun run() {

        //char stream that reads from standard input
        val input = ANTLRInputStream(java.lang.System.`in`)

        //creates a lexer that feeds off of input charstream
        val lexer = BasicLexer(input)

        //creates a buffer of tokens pulled from the lexer
        val tokens = CommonTokenStream(lexer)

        //Creates a parser that feeds off of the tokens buffer
        val parser = BasicParser(tokens)

        val tree = parser.init()

        System.out.println(tree.toString(parser))

    }

}