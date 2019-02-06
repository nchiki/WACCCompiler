package src

import antlr.BasicParser
import org.antlr.v4.runtime.*
import java.awt.Color
import java.util.*
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JLabel

class WaccErrorListener : BaseErrorListener() {
    override fun syntaxError(recognizer: Recognizer<*, *>, offendingSymbol: Any?,
                             line: Int, charPositionInLine: Int, msg: String?,
                             e: RecognitionException?) {

        System.err.println("line $line:$charPositionInLine $msg")
        underlineError(recognizer, offendingSymbol as Token,
        line, charPositionInLine)
        //val stack = (recognizer as Parser).ruleInvocationStack
        //Collections.reverse(stack)


        //System.err.println("rule stack: $stack")
        //System.err.println("line " + line + ":" + charPositionInLine + " at " +
                //offendingSymbol + ": " + msg)

        /*val buf = StringBuilder()
        buf.append("rule stack: "+stack+" ");
        buf.append("line "+line+":"+charPositionInLine+" at "+ offendingSymbol +
                ": " + msg)
        val dialog = JDialog()
        val contentPane = dialog.contentPane
        contentPane.add(JLabel(buf.toString()))
        contentPane.setBackground(Color.white)
        dialog.setTitle("Syntax error")
        dialog.pack()
        dialog.setLocationRelativeTo(null)
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        dialog.setVisible(true)*/
    }

    fun underlineError(recognizer: Recognizer<*, *>,
                       offendingToken: Token, line: Int,
                       charPositionInLine: Int) {
        val tokens = recognizer.inputStream as CommonTokenStream
        val input = tokens.getTokenSource().getInputStream().toString()
        val lines = input.split("\n".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val errorLine = lines[line - 1]
        System.err.println(errorLine)
        for (i in 0 until charPositionInLine) System.err.print(" ")
        val start = offendingToken.getStartIndex()
        val stop = offendingToken.getStopIndex()
        if (start >= 0 && stop >= 0) {
            for (i in start..stop) System.err.print("^")
        }
        System.err.println()
    }
}