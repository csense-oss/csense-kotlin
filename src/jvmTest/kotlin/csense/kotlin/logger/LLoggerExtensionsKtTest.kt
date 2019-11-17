package csense.kotlin.logger

import csense.kotlin.tests.assertions.assertContainsInOrder
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream


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
        l.usePrintAsLoggers()
        l.isLoggingAllowed(true)
        l.error("etag", "emsg")
        outContent.toString().assertContainsInOrder(listOf("etag", "emsg"), false)
        outContent.reset()
        l.warning("wtag", "wmsg")
        outContent.toString().assertContainsInOrder(listOf("wtag", "wmsg"), false)
        outContent.reset()
        l.debug("dtag", "dmsg")
        outContent.toString().assertContainsInOrder(listOf("dtag", "dmsg"), false)
        outContent.reset()
        l.logProd("prodtag", "prodmsg")
        outContent.toString().assertContainsInOrder(listOf("prodtag", "prodmsg"), false)
    }
}