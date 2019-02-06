package Errors

import org.antlr.v4.runtime.*
import kotlin.system.exitProcess

const val SyntaxExitCode = 100

class SyntaxErrorStrategy : DefaultErrorStrategy() {

    override fun reportMatch(recognizer: Parser?) {

        exitProcess(SyntaxExitCode)
    }

    override fun recover(recognizer: Parser?, e: RecognitionException?) {
        exitProcess(SyntaxExitCode)
    }

}