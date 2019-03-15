package main.kotlin

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CodePointCharStream
import org.antlr.v4.runtime.Token

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
    for (i in tokens.size - 1 downTo 0) {
        if (macros.containsKey(tokens[i].text)) {
            string = StringBuilder(string.removeRange(tokens[i].startIndex, tokens[i].stopIndex + 1).toString())
            string = string.insert(tokens[i].startIndex, macros[tokens[i].text]!!.text!!)
        }
    }
    if (macros.size > 0) {
        string = StringBuilder(string.removeRange(first, last).toString())
    }

    return CharStreams.fromString(string.toString())
}

private fun getMacros(tokens: MutableList<out Token>): HashMap<String, out Token> {
    var cur = 0
    val macros = HashMap<String, Token>()
    while (cur < tokens.size && tokens[cur].text != "begin") {
        if (tokens[cur].text == ".define") {
            macros[tokens[cur + 1].text] = tokens[cur + 2]
            tokens.removeAt(cur + 2)
            tokens.removeAt(cur + 1)
            tokens.removeAt(cur)
            cur += 3
        } else {
            cur += 1
        }
    }
    return macros
}
