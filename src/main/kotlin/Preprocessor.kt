package main.kotlin

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CodePointCharStream
import org.antlr.v4.runtime.Token
import kotlin.system.exitProcess

fun preprocess(file: CharStream, tokens: MutableList<out Token>): CodePointCharStream {
    val first = tokens[0].startIndex
    var last = 0
    var cur = 0
    while (tokens[cur].text != "begin") {
        last = tokens[cur].stopIndex + 2
        cur += 1
    }

    var string = StringBuilder(file.toString())
    val macros = getMacros(tokens)

    // Substitute mentions of macros with their values
    for (i in tokens.size - 1 downTo 0) {
        if (macros.containsKey(tokens[i].text)) {
            string = StringBuilder(string.removeRange(tokens[i].startIndex, tokens[i].stopIndex + 1).toString())
            string = string.insert(tokens[i].startIndex, macros[tokens[i].text]!!.map { i -> i.text }.joinToString(""))
        }
    }

    // Remove define keywords from file
    if (macros.size > 0) {
        string = StringBuilder(string.removeRange(first, last).toString())
    }
    return CharStreams.fromString(string.toString())
}

private fun getMacros(tokens: MutableList<out Token>): HashMap<String, MutableList<Token>> {
    var cur = 0
    var curMacroIdentifier: String
    val macros = HashMap<String, MutableList<Token>>()

    while (cur < tokens.size && tokens[cur].text != "begin") {
        if (tokens[cur].text == ".define") {
            if (tokens[cur + 2].text == "begin") {
                exitProcess(100)
            }
            curMacroIdentifier = tokens[cur + 1].text
            macros[curMacroIdentifier] = mutableListOf()
            tokens.removeAt(cur + 1)
            tokens.removeAt(cur)
            while (tokens[cur].text != ".define" && tokens[cur].text != "begin") {
                macros[curMacroIdentifier]!!.add(tokens[cur])
                tokens.removeAt(cur)
            }
        } else {
            cur += 1
        }
    }
    return macros
}
