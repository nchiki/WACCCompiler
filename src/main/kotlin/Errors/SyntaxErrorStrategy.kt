package Errors

import org.antlr.v4.runtime.*
import kotlin.system.exitProcess

const val SyntaxExitCode = 100

class SyntaxErrorStrategy : DefaultErrorStrategy() {

    override fun reportError(recognizer: Parser?, e: RecognitionException?) {
        exitProcess(SyntaxExitCode)
    }


}