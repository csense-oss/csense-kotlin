package csense.kotlin.logger

import csense.kotlin.tests.assertions.*
import org.junit.*
import java.io.*


class LLoggerExtensionsKtTest {
    private val outContent = ByteArrayOutputStream()
    private val errContent = ByteArrayOutputStream()
    private val originalOut = System.out
    private val originalErr = System.err

    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @After
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @Test
    fun usePrintAsLoggers() {
        val l = LLogger()
        l.isLoggingAllowed(true)

        l.usePrintAsLoggers()

        l.logProd("prodtag", "prodmsg")
        outContent.toString().assertContainsInOrder(listOf("prodtag", "prodmsg"), false)

        l.error("etag", "emsg")
        outContent.toString().assertContainsInOrder(listOf("etag", "emsg"), false)
        outContent.reset()
        l.warning("wtag", "wmsg")
        outContent.toString().assertContainsInOrder(listOf("wtag", "wmsg"), false)
        outContent.reset()
        l.debug("dtag", "dmsg")
        outContent.toString().assertContainsInOrder(listOf("dtag", "dmsg"), false)
        outContent.reset()
    }
    @Test
    fun usePrintAsLoggersWithAnsiColor() {
        val l = LLogger()
        l.isLoggingAllowed(true)

        l.usePrintAsLoggersWithAnsiColor()

        l.logProd("prodtag", "prodmsg")
        outContent.toString().assertContainsInOrder(listOf("\u001B[36m","prodtag", "prodmsg"), false)

        l.error("etag", "emsg")
        outContent.toString().assertContainsInOrder(listOf("etag", "emsg"), false)
        outContent.reset()
        l.warning("wtag", "wmsg")
        outContent.toString().assertContainsInOrder(listOf("wtag", "wmsg"), false)
        outContent.reset()
        l.debug("dtag", "dmsg")
        outContent.toString().assertContainsInOrder(listOf("dtag", "dmsg"), false)
        outContent.reset()
    }
}