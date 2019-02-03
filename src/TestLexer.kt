package wacc_25

import antlr.BasicLexer
import antlr.BasicParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream


fun main(args: Array<String>){

        if(args.size == 0) {
            System.setIn(FileInputStream("/Users/blancatebar/Documents/SecondYear/WACC/wacc_examples/valid/basic/exit/exit-1.wacc"))
        }

        val input = CharStreams.fromStream(java.lang.System.`in`)
        // create a lexer that feeds off of input CharStream
        val lexer = BasicLexer(input)
        // create a buffer of tokens pulled from the lexer
        val tokens = CommonTokenStream(lexer)
        // create a parser that feeds off the tokens buffer
        val parser = BasicParser(tokens)
        val tree = parser.prog()
        // begin parsing at init rule
        println(tree.toStringTree(parser))
        // print LISP-style tree
    }



