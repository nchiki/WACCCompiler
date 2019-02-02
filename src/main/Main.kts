package wacc_25
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main() {
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