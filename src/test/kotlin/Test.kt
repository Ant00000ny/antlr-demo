import com.isekaiofficial.antlrdemo.LogListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertTrue


class Test {
    @Test
    fun test() {
        val logLine = "2018-May-05 14:20:24 ERROR Bad thing happened\n"

        val lexer = LogLexer(CharStreams.fromString(logLine))
        val tokens = CommonTokenStream(lexer)
        val parser = LogParser(tokens)
        val walker = ParseTreeWalker()
        val listener = LogListener()
        walker.walk(listener, parser.log())
        val entry = listener.entries[0]

        assertTrue { entry.level == "ERROR" }
        assertTrue { entry.message == ("Bad thing happened") }
        assertTrue { entry.timestamp == (LocalDateTime.of(2018, 5, 5, 14, 20, 24)) }

        println(entry)
    }
}
