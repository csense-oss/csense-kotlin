package csense.kotlin.extensions

import csense.kotlin.logger.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class ExceptionsTest {

    @Test
    fun toPrettyString() {
        val empty = Throwable(null, null)
        empty.toPrettyString().assertStartsWith("No message")


        val customMessage = Exception("some message")
        customMessage.toPrettyString().assertStartsWith("some message")
        val cause1 = Throwable("some inner reason")
        val messageCause = Exception("SomeMessage", cause1)
        val withCauseMessage = messageCause.toPrettyString()
        withCauseMessage.assertStartsWith("SomeMessage")
        withCauseMessage.assertContains("\n\tsome inner reason")


        val inner4 = Throwable("inner4")
        val inner3 = Throwable("inner3", inner4)
        val inner2 = Throwable("inner2", inner3)
        val main = Throwable("main", inner2)

        val longChain = main.toPrettyString()
        longChain.assertStartsWith("main")
        longChain.assertContains("\n\tinner2")
        longChain.assertContains("\n\tinner3")
        longChain.assertContains("\n\tinner4")


    }

    @Test
    fun testTryAndLog() {

        val works = tryAndLog(logger = LLogger()::error) {
            42
        }
        works.assertNotNullAndEquals(42)

        val fails: String? = tryAndLog(logger = LLogger()::error) {
            throw Exception("test")
            @Suppress("UNREACHABLE_CODE")
            "123"
        }
        fails.assertNull()
    }

    @Test
    fun tryAndLog1() {

        val works = tryAndLog("title", "message", { _: String, _: String, _: Throwable? ->
            failTest("should not get called")
        }) {
            42
        }
        works.assertNotNull()

        val exception = Exception("test")
        var didCallLogger = false

        @Suppress("UNREACHABLE_CODE")
        val fails = tryAndLog("title", "message", { tag: String, message: String, throwable: Throwable? ->
            didCallLogger = true
            tag.assert("title")
            message.assert("message")
            throwable.assertNotNull()
            throwable.assertNotNullAndEquals(exception)
        }) {
            throw exception
            "123"
        }
        didCallLogger.assertTrue()
        fails.assertNull()

    }
}