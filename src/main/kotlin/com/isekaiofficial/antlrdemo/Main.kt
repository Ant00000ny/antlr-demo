package com.isekaiofficial.antlrdemo

import SeaLexer
import SeaParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream


class Main

fun main() {
    val lexer = SeaLexer(CharStreams.fromString("5838 + 44 - 3"))
    val tokens = CommonTokenStream(lexer)
    val parser = SeaParser(tokens)
    val tree = parser.fileStat()

    println(tree)
}
