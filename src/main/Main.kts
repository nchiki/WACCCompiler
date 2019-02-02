package wacc_25
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main() {
    val input = ANTLRInputStream(System.in)
    val lexer = BasicLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = TParser(tokens)
    val parseTree = parser.init()
    print(parseTree.toStringTree(parser))
}