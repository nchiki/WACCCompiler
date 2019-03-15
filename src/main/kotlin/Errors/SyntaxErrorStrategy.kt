package main.kotlin.Errors

import org.antlr.v4.runtime.DefaultErrorStrategy
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Token
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