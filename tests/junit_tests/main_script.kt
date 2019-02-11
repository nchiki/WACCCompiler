
import main.kotlin.ErrorLogger
import main.kotlin.Nodes.ProgNode
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream
import kotlin.system.exitProcess


fun main(location: String) : ProgNode {
    System.setIn(FileInputStream(location))
    val input = CharStreams.fromStream(java.lang.System.`in`)
    // create a lexer that feeds off of input CharStream
    //val listener = WaccErrorListener()
    //Lexical analysis
    val lexer = BasicLexer(input)

    //val visitor = WaccVisitor()
    // create a buffer of tokens pulled from the lexer
    val tokens = CommonTokenStream(lexer)

    //Syntactical analysis
    // create a parser that feeds off the tokens buffer
    val parser = BasicParser(tokens)
    //parser.errorHandler = SyntaxErrorStrategy()
    val tree = parser.prog()
    if (parser.numberOfSyntaxErrors > 0) {
        exitProcess(100)
    }

    // begin parsing at init rule
    println(tree.toStringTree(parser))

    val visitor = WaccVisitor()
    val errorLogger = ErrorLogger()
    val symbolTable = SymbolTable(null)
    val progNode = visitor.visit(tree) as ProgNode
    return progNode

    //progNode.getSyntaxErrors

//    progNode.semanticCheck(errorLogger, symbolTable)
}

