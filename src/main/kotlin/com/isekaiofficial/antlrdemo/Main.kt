package com.isekaiofficial.antlrdemo

import LogBaseListener
import LogParser
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class LogEntry(
    var level: String? = null,
    var message: String? = null,
    var timestamp: LocalDateTime? = null,
)

class LogListener : LogBaseListener() {
    val entries = mutableListOf<LogEntry>()
    private lateinit var current: LogEntry
    override fun enterEntry(ctx: LogParser.EntryContext) {
        current = LogEntry()
    }

    override fun enterTimestamp(ctx: LogParser.TimestampContext) {
        current.timestamp = LocalDateTime.parse(ctx.text, DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH))
    }

    override fun enterMessage(ctx: LogParser.MessageContext) {
        current.message = ctx.text
    }

    override fun enterLevel(ctx: LogParser.LevelContext) {
        current.level = ctx.text
    }

    override fun exitEntry(ctx: LogParser.EntryContext) {
        entries += current
    }
}
