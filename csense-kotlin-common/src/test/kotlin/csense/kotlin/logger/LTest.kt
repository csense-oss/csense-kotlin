@file:Suppress("unused")

package csense.kotlin.logger

import csense.kotlin.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.test.assertions.*
import kotlin.test.*

class LTest {

    object IsLoggingAllowed {
        @Test
        fun disableAll() {
            LLogger().apply {
                isLoggingAllowed(false)
                assertLoggingAllowedStates(
                        assertProd = false,
                        assertError = false,
                        assertWarning = false,
                        assertDebug = false
                )
            }
        }

        @Test
        fun enableAll() {
            LLogger().apply {
                isLoggingAllowed(true)
                assertLoggingAllowedStates(
                        assertProd = true,
                        assertError = true,
                        assertWarning = true,
                        assertDebug = true
                )
            }
        }

        @Test
        fun tryCombination() {
            LLogger().apply {
                isLoggingAllowed(false)
                isProductionLoggingAllowed = true
                assertLoggingAllowedStates(
                        assertProd = true,
                        assertError = false,
                        assertWarning = false,
                        assertDebug = false)

                isWarningLoggingAllowed = true

                isProductionLoggingAllowed = false

                assertLoggingAllowedStates(
                        assertProd = false,
                        assertError = false,
                        assertWarning = true,
                        assertDebug = false)
                //make sure they are not dependent on each other.

            }
        }
    }

    @Test
    fun testDebug() {
        val l = LLogger()
        testLoggingPassThough(
                { l.isDebugLoggingAllowed = it },
                { l.debugLoggers.set(it) },
                l::debug,
                "Debugtag",
                "messageDebug",
                RuntimeException("someDebug")
        )
    }

    @Test
    fun testWarning() {
        val l = LLogger()
        testLoggingPassThough(
                { l.isWarningLoggingAllowed = it },
                { l.warningLoggers.set(it) },
                l::warning,
                "tagwarning\r\n",
                "wmessage",
                RuntimeException("warning ")
        )
    }

    @Test
    fun testError() {
        val l = LLogger()
        testLoggingPassThough(
                { l.isErrorLoggingAllowed = it },
                { l.errorLoggers.set(it) },
                l::error,
                "tagError",
                "EmessageRror",
                RuntimeException("warningtoError")
        )
    }

    @Test
    fun testLogProd() {
        val l = LLogger()
        testLoggingPassThough(
                { l.isProductionLoggingAllowed = it },
                { l.productionLoggers.set(it) },
                l::logProd,
                "prod",
                "very usuable message",
                RuntimeException("prod not allowed on test")
        )
    }

    //test of toString on LoggingLevel
    @Test
    fun testToString() {
        LoggingLevel.Debug.toString().assert("Debug")
        LoggingLevel.Production.toString().assert("Production")
    }
}

private inline fun testLoggingPassThough(
        controlLoggingMethod: FunctionUnit<Boolean>,
        setLoggerMethod: FunctionUnit<LoggingFunctionType<Unit>>,
        loggerMethod: LoggingFunctionType<Unit>,
        tagToUse: String,
        messageToUse: String,
        exceptionToUse: Throwable) {
    var outerTag = ""
    var outerMessage = ""
    var outerThrowable: Throwable? = null
    setLoggerMethod { tag: String,
                      message: String,
                      stackTrace: Throwable? ->
        outerTag = tag
        outerMessage = message
        outerThrowable = stackTrace
    }
    controlLoggingMethod(false)
    loggerMethod(tagToUse, messageToUse, exceptionToUse)
    outerTag.assert("", "not allowed to run logger at this time")
    outerMessage.assert("", "not allowed to run logger at this time")
    outerThrowable.assertNull("not allowed to run logger at this time")

    controlLoggingMethod(true)
    loggerMethod(tagToUse, messageToUse, exceptionToUse)
    outerTag.assert(tagToUse, "supplied tag should get passed")
    outerMessage.assert(messageToUse, "supplied message should get passed")
    outerThrowable.assertNotNullAndEquals(exceptionToUse, "supplied exception should get passed")

}

/**
 * Asserts the state on the L. properties.
 */
private fun LLogger.assertLoggingAllowedStates(assertProd: Boolean,
                                               assertError: Boolean,
                                               assertWarning: Boolean,
                                               assertDebug: Boolean,
                                               optionalMessage: String = "") {
    isProductionLoggingAllowed.assert(assertProd, optionalMessage)
    isErrorLoggingAllowed.assert(assertError, optionalMessage)
    isWarningLoggingAllowed.assert(assertWarning, optionalMessage)
    isDebugLoggingAllowed.assert(assertDebug, optionalMessage)
}
