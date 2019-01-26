package csense.kotlin.extensions

import csense.kotlin.extensions.collections.list.*
import csense.kotlin.logger.*
import csense.kotlin.test.assertions.*
import org.junit.*

class LExtensionsKtTest {

    @Test
    fun testLogDebug() {
        val counter = WrappedInt()
        L.debugLoggers.set(createSingleLoggerAssertion("LExtensionsKtTest", "test", 1, counter))
        logClassDebug("test")
        counter.value.assertNotNullAndEquals(1)
    }

    @Test
    fun testLogError() {
        val counter = WrappedInt()
        L.errorLoggers.set(createSingleLoggerAssertion("LExtensionsKtTest", "abc", 3, counter))
        logClassError("abc", null)
        logClassError("abc", null)
        logClassError("abc", null)
        counter.value.assertNotNullAndEquals(3)

    }

    @Test
    fun testLogWarning() {
        val counter = WrappedInt()
        L.warningLoggers.set(createSingleLoggerAssertion("LExtensionsKtTest", "1234", 2, counter))
        logClassWarning("1234", null)
        logClassWarning("1234", null)
        counter.value.assertNotNullAndEquals(2)

    }

    @Test
    fun testLogProduction() {
        val counter = WrappedInt()
        L.productionLoggers.set(createSingleLoggerAssertion("LExtensionsKtTest", "message", 2, counter))
        logClassProduction("message", null)
        logClassProduction("message", null)
        counter.value.assertNotNullAndEquals(2)
    }


    fun createSingleLoggerAssertion(expectedTag: String, expectedMessage: String, maxCounter: Int, counter: WrappedInt): LoggingFunctionType<Unit> {
        return { tag: String, message: String, ex: Throwable? ->
            counter.value += 1
            expectedTag.assert(tag)
            expectedMessage.assert(message)
            maxCounter.assertLargerOrEqualTo(counter.value)
        }

    }
}


class WrappedInt(var value: Int = 0)