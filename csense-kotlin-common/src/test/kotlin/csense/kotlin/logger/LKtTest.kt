package csense.kotlin.logger

import csense.kotlin.*
import csense.kotlin.extensions.collections.*
import csense.kotlin.test.assertions.*
import kotlin.test.*


class LKtTest {
    /**
     * Tests the logging controls (turning  them on off )
     */
    @Test
    fun testLoggingSwitches() {

        //manually turn all on.
        L.apply {
            isProductionLoggingAllowed = true
            isDebugLoggingAllowed = true
            isErrorLoggingAllowed = true
            isWarningLoggingAllowed = true
        }
        assertLoggingAllowedStates(
                assertProd = true,
                assertError = true,
                assertWarning = true,
                assertDebug = true)

        //make sure all are turned on

        //turn all off.
        L.isLoggingAllowed(false)
        //make sure all are turned off.
        assertLoggingAllowedStates(
                assertProd = false,
                assertError = false,
                assertWarning = false,
                assertDebug = false)


        L.isLoggingAllowed(true)
        assertLoggingAllowedStates(
                assertProd = true,
                assertError = true,
                assertWarning = true,
                assertDebug = true)
        //make sure all are turned on.

        L.isLoggingAllowed(false)
        assertLoggingAllowedStates(
                assertProd = false,
                assertError = false,
                assertWarning = false,
                assertDebug = false)
        L.isProductionLoggingAllowed = true
        assertLoggingAllowedStates(
                assertProd = true,
                assertError = false,
                assertWarning = false,
                assertDebug = false)

        L.isWarningLoggingAllowed = true

        L.isProductionLoggingAllowed = false

        assertLoggingAllowedStates(
                assertProd = false,
                assertError = false,
                assertWarning = true,
                assertDebug = false)
        //make sure they are not dependent on each other.


    }

    /**
     * Asserts the state on the L. properties.
     */
    private fun assertLoggingAllowedStates(assertProd: Boolean,
                                           assertError: Boolean,
                                           assertWarning: Boolean,
                                           assertDebug: Boolean,
                                           optionalMessage: String = "") {
        L.apply {
            isProductionLoggingAllowed.assert(assertProd, optionalMessage)
            isErrorLoggingAllowed.assert(assertError, optionalMessage)
            isWarningLoggingAllowed.assert(assertWarning, optionalMessage)
            isDebugLoggingAllowed.assert(assertDebug, optionalMessage)
        }
    }

    /**
     * testing the state is one important thing,
     * but testing whenever the state is obeyed is another thing
     */
    @Test
    fun testLoggingContentControl() {
        L.isLoggingAllowed(false)
        testLoggingPassThough(
                { L.isDebugLoggingAllowed = it },
                { L.debugLoggers.set(it) },
                L::debug,
                "Debugtag",
                "messageDebug",
                RuntimeException("someDebug")
        )
        testLoggingPassThough(
                { L.isWarningLoggingAllowed = it },
                { L.warningLoggers.set(it) },
                L::warning,
                "tagwarning\r\n",
                "wmessage",
                RuntimeException("warning ")
        )
        testLoggingPassThough(
                { L.isErrorLoggingAllowed = it },
                { L.errorLoggers.set(it) },
                L::error,
                "tagError",
                "EmessageRror",
                RuntimeException("warningtoError")
        )
        testLoggingPassThough(
                { L.isProductionLoggingAllowed = it },
                { L.productionLoggers.set(it) },
                L::logProd,
                "prod",
                "very usuable message",
                RuntimeException("prod not allowed on test")
        )
    }

    @Ignore
    @Test
    fun testViewLogging() {
        TODO("MISSING")
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


}