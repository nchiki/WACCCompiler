package wacc_25

import antlr.BasicLexer
import antlr.BasicParser
import com.google.common.io.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream


fun main(args: Array<String>){

        if(args.size == 0) {
                System.setIn(FileInputStream("/Users/blancatebar/Documents/SecondYear/WACC/wacc_examples/valid/basic/exit/exit-1.wacc"))
        }
        val input = CharStreams.fromStream(java.lang.System.`in`)
        // create a lexer that feeds off of input CharStream
        val listener = WaccErrorListener()
        //Lexical analysis
        val lexer = BasicLexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(listener)


        /*---------------------------------------------------------------*/
        //not sure what to do with a visitor at this point to be honest
        /*---------------------------------------------------------------*/
        val visitor = WaccVisitor()
        // create a buffer of tokens pulled from the lexer
        val tokens = CommonTokenStream(lexer)

        //Syntactical analysis
        // create a parser that feeds off the tokens buffer
        val parser = BasicParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(listener)
        val tree = parser.prog()
        // begin parsing at init rule
        println(tree.toStringTree(parser))
        visitor.visit(tree)
        // print LISP-style tree


    }



