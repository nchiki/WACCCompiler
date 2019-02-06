package Errors

import org.antlr.v4.runtime.DefaultErrorStrategy
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.RecognitionException
import kotlin.system.exitProcess

const val SyntaxExitCode = 100

class SyntaxErrorStrategy : DefaultErrorStrategy() {

    override fun recover(recognizer: Parser?, e: RecognitionException?) {
        exitProcess(SyntaxExitCode)
    }

}