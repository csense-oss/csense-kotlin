package csense.kotlin.extensions.logger

import csense.kotlin.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.extensions.collections.list.*
import csense.kotlin.logger.*
import csense.kotlin.logger.logClassDebug
import csense.kotlin.test.assertions.*
import kotlin.test.*

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
        return { tag: String, message: String, _: Throwable? ->
            counter.value += 1
            expectedTag.assert(tag)
            expectedMessage.assert(message)
            maxCounter.assertLargerOrEqualTo(counter.value)
        }
    }


    @Test
    fun testLDebug() {
        L.isDebugLoggingAllowed = true

        assertCounterAndContentOfLog(L.debugLoggers, {
            L.debug(this::class, "test")
        },
                "LExtensionsKtTest",
                "test",
                null
        )

    }


    @Test
    fun testLWarning() {

        L.isWarningLoggingAllowed = true

        assertCounterAndContentOfLog(L.warningLoggers, {
            L.warning(this::class, "test2")
        },
                "LExtensionsKtTest",
                "test2",
                null
        )

    }


    @Test
    fun testLError() {

        L.isErrorLoggingAllowed = true

        assertCounterAndContentOfLog(L.errorLoggers, {
            L.error(this::class, "test3")
        },
                "LExtensionsKtTest",
                "test3",
                null
        )
    }

    @Test
    fun testLProd() {
        L.isProductionLoggingAllowed = true
        val runtimeException = RuntimeException()
        assertCounterAndContentOfLog(L.productionLoggers, {
            L.logProd(this::class, "log", runtimeException)
        },
                "LExtensionsKtTest",
                "log",
                runtimeException
        )
    }

    private fun assertCounterAndContentOfLog(
            loggers: MutableList<LoggingFunctionType<Any>>,
            logAction: EmptyFunction,
            expectedTag: String,
            expectedMessage: String,
            expectedThrowable: Throwable?
    ) {
        var counter = 0
        loggers.clear()
        loggers.add { tag: String, message: String, throwable: Throwable? ->
            tag.assert(expectedTag)
            message.assert(expectedMessage)
            (expectedThrowable == throwable).assertTrue()
            counter += 1
            Unit
        }
        logAction()
        counter.assert(1)
    }
}


class WrappedInt(var value: Int = 0)
