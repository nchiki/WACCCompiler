//import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main() {
    val input = null
    val lexer = BasicLexer(input)

    //creates a buffer of tokens pulled from the lexer
    val tokens = CommonTokenStream(lexer)

    val parser = BasicParser(tokens)
    val parseTree = parser.prog()
    print(parseTree.toStringTree(parser))
}
