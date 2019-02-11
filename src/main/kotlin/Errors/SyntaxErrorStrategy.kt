package main.kotlin.Errors

import org.antlr.v4.runtime.*
import kotlin.system.exitProcess

const val SyntaxExitCode = 100

class SyntaxErrorStrategy : DefaultErrorStrategy() {

    override fun recover(recognizer: Parser?, e: RecognitionException?) {
        exitProcess(SyntaxExitCode)
    }

    override fun recoverInline(recognizer: Parser?): Token {
        exitProcess(SyntaxExitCode)
    }

    override fun sync(recognizer: Parser?) {

    }

}