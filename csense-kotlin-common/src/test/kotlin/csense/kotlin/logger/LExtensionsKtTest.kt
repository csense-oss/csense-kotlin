package csense.kotlin.logger

import csense.kotlin.*
import csense.kotlin.test.assertions.*
import kotlin.test.*

class LExtensionsKtTest {

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