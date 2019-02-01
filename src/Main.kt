package src

import org.antlr.runtime.*

fun main() {
    val input = ANTLRInputStream(System.in)
    val lexer = BasicLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = TParser(tokens)
    parser.r()
}