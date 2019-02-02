package wacc_25

import antlr.BasicLexer
import antlr.BasicParser
import org.antlr.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main() {
    val input = ANTLRInputStream(System.`in`)
    val lexer = BasicLexer(input)

    val tokens = CommonTokenStream(lexer)

    val parser = BasicParser(tokens)
    val parseTree = parser.prog()
    print(parseTree.toStringTree(parser))
}
