//import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main() {
    val input = null
    val lexer = BasicLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = BasicParser(tokens)
    val parseTree = parser.prog()
    print(parseTree.toStringTree(parser))
}