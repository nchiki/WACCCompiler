import Errors.SyntaxErrorStrategy
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream

fun main(args: Array<String>) {
        if(args.size == 0) {
            //System.setIn(FileInputStream("../wacc_examples/invalid/syntaxErr/pairs/badLookup01.wacc"))
        } else {
            System.setIn(FileInputStream(args[0]))
        }
        val input = CharStreams.fromStream(java.lang.System.`in`)
        // create a lexer that feeds off of input CharStream
        val listener = WaccErrorListener()
        //Lexical analysis
        val lexer = BasicLexer(input)
        //lexer.removeErrorListeners()
        //lexer.addErrorListener(listener)


        /*---------------------------------------------------------------*/
        //not sure what to do with a visitor at this point to be honest
        /*---------------------------------------------------------------*/
        //val visitor = WaccVisitor()
        // create a buffer of tokens pulled from the lexer
        val tokens = CommonTokenStream(lexer)

        //Syntactical analysis
        // create a parser that feeds off the tokens buffer
        val parser = BasicParser(tokens)
        parser.errorHandler = SyntaxErrorStrategy()
        //parser.removeErrorListeners()
        //parser.addErrorListener(WaccErrorListener())
        val tree = parser.prog()
        // begin parsing at init rule
        println(tree.toStringTree(parser))
        //visitor.visit(tree)
        // print LISP-style tree

        val visitor = WaccVisitor()
        val errorLogger = ErrorLogger()
        val symbolTable = SymbolTable(null)
        val progNode = visitor.visit(tree)

        //progNode.getSyntaxErrors

        progNode.semanticCheck(errorLogger, symbolTable)



    }
