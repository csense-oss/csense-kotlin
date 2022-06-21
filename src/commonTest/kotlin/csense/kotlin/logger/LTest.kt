//@file:Suppress("unused")
//
//package csense.kotlin.logger
//
//import csense.kotlin.*
//import csense.kotlin.extensions.collections.*
//import csense.kotlin.tests.assertions.*
//import kotlin.test.*
//
//class LLoggerTest {
//
//    class IsLoggingAllowed {
//        @Test
//        fun disableAll() {
//            LLogger().apply {
//                isLoggingAllowed(false)
//                assertLoggingAllowedStates(
//                    assertProd = false,
//                    assertError = false,
//                    assertWarning = false,
//                    assertDebug = false
//                )
//            }
//        }
//
//        @Test
//        fun enableAll() {
//            LLogger().apply {
//                isLoggingAllowed(true)
//                assertLoggingAllowedStates(
//                    assertProd = true,
//                    assertError = true,
//                    assertWarning = true,
//                    assertDebug = true
//                )
//            }
//        }
//
//        @Test
//        fun tryCombination() {
//            LLogger().apply {
//                isLoggingAllowed(false)
//                isProductionLoggingAllowed = true
//                assertLoggingAllowedStates(
//                    assertProd = true,
//                    assertError = false,
//                    assertWarning = false,
//                    assertDebug = false
//                )
//
//                isWarningLoggingAllowed = true
//
//                isProductionLoggingAllowed = false
//
//                assertLoggingAllowedStates(
//                    assertProd = false,
//                    assertError = false,
//                    assertWarning = true,
//                    assertDebug = false
//                )
//            }
//        }
//    }
//
//    @Test
//    fun testDebugTag() {
//        val l = LLogger()
//        testLoggingPassThough(
//            { l.isDebugLoggingAllowed = it },
//            { l.debugLoggers.set(it) },
//            l::debug,
//            "Debugtag",
//            "messageDebug",
//            RuntimeException("someDebug")
//        )
//    }
//
//    @Test
//    fun testWarningTag() {
//        val l = LLogger()
//        testLoggingPassThough(
//            { l.isWarningLoggingAllowed = it },
//            { l.warningLoggers.set(it) },
//            l::warning,
//            "tagwarning\r\n",
//            "wmessage",
//            RuntimeException("warning ")
//        )
//    }
//
//    @Test
//    fun testErrorTag() {
//        val l = LLogger()
//        testLoggingPassThough(
//            { l.isErrorLoggingAllowed = it },
//            { l.errorLoggers.set(it) },
//            l::error,
//            "tagError",
//            "EmessageRror",
//            RuntimeException("warningtoError")
//        )
//    }
//
//    @Test
//    fun testLogProdTag() {
//        val l = LLogger()
//        testLoggingPassThough(
//            { l.isProductionLoggingAllowed = it },
//            { l.productionLoggers.set(it) },
//            l::logProd,
//            "prod",
//            "very usuable message",
//            RuntimeException("prod not allowed on test")
//        )
//    }
//
//    @Test
//    fun testToString() {
//        LoggingLevel.Debug.toString().assert("Debug")
//        LoggingLevel.Production.toString().assert("Production")
//        LoggingLevel.Warning.toString().assert("Warning")
//        LoggingLevel.Error.toString().assert("Error")
//    }
//
//    @Test
//    fun warningLazyTag() {
//        val l = LLogger()
//        testLazyLoggingPassingThough(
//            { l.isWarningLoggingAllowed = it },
//            l.warningLoggers,
//            l::warningLazy
//        )
//    }
//
//    @Test
//    fun debugLazyTag() {
//        val l = LLogger()
//        testLazyLoggingPassingThough(
//            { l.isDebugLoggingAllowed = it },
//            l.debugLoggers,
//            l::debugLazy
//        )
//    }
//
//    @Test
//    fun logProdLazyTag() {
//        val l = LLogger()
//        testLazyLoggingPassingThough(
//            { l.isProductionLoggingAllowed = it },
//            l.productionLoggers,
//            l::logProdLazy
//        )
//    }
//
//    @Test
//    fun errorLazyTag() {
//        val l = LLogger()
//        testLazyLoggingPassingThough(
//            { l.isErrorLoggingAllowed = it },
//            l.errorLoggers,
//            l::errorLazy
//        )
//    }
//
//    class IterableTInvokeEachWithLoggingLazyTest {
//
//        @Test
//        fun empty() {
//            var failedCounter = 0
//            val loggers = mutableListOf<LoggingFunctionType<*>>()
//            loggers.invokeEachWithLoggingLazy(
//                "tag",
//                { failedCounter += 1; "" }, // failTest("test") fails in js
//                null
//            )
//            failedCounter.assert(0, "should really be 0.")
//        }
//
//        @Test
//        fun single() {
//            var messageComputeTimes = 0
//            var counter1 = 0
//            val loggers: MutableList<LoggingFunctionType<*>> = mutableListOf(
//                { _, _, _ ->
//                    counter1 += 1
//                })
//            loggers.invokeEachWithLoggingLazy(
//                "tag",
//                { messageComputeTimes += 1;"" },
//                null
//            )
//
//            messageComputeTimes.assert(1)
//            counter1.assert(1)
//        }
//
//        @Test
//        fun multiple() {
//            var messageComputeTimes = 0
//            var counter1 = 0
//            var counter2 = 0
//
//            val loggers: MutableList<LoggingFunctionType<*>> = mutableListOf(
//                { _, _, _ ->
//                    counter1 += 1
//                },
//                { _, _, _ ->
//                    counter2 += 1
//                }
//            )
//            loggers.invokeEachWithLoggingLazy(
//                "tag",
//                { messageComputeTimes += 1;"" },
//                null
//            )
//            messageComputeTimes.assert(1)
//            counter1.assert(1)
//            counter2.assert(1)
//        }
//
//
//    }
//
//}
//
//private fun testLazyLoggingPassingThough(
//    setAllowed: (Boolean) -> Unit,
//    loggers: MutableList<LoggingFunctionType<Any>>,
//    log: (tag: String, function: () -> String) -> Unit
//) {
//    setAllowed(false)
//    loggers.clear()
//    log("tag") { failTest() }
//    setAllowed(true)
//    log("tag") { failTest() }
//    var logCount = 0
//    loggers.add { _, _, _ -> logCount += 1; 42 }
//    log("tag") { "message" }
//    logCount.assert(1)
//}
//
//private fun testLoggingPassThough(
//    controlLoggingMethod: FunctionUnit<Boolean>,
//    setLoggerMethod: FunctionUnit<LoggingFunctionType<Unit>>,
//    loggerMethod: LoggingFunctionType<Unit>,
//    tagToUse: String,
//    messageToUse: String,
//    exceptionToUse: Throwable
//) {
//    var outerTag = ""
//    var outerMessage = ""
//    var outerThrowable: Throwable? = null
//    setLoggerMethod { tag: String,
//                      message: String,
//                      stackTrace: Throwable? ->
//        outerTag = tag
//        outerMessage = message
//        outerThrowable = stackTrace
//    }
//    controlLoggingMethod(false)
//    loggerMethod(tagToUse, messageToUse, exceptionToUse)
//    outerTag.assert("", "not allowed to run logger at this time")
//    outerMessage.assert("", "not allowed to run logger at this time")
//    outerThrowable.assertNull("not allowed to run logger at this time")
//
//    controlLoggingMethod(true)
//    loggerMethod(tagToUse, messageToUse, exceptionToUse)
//    outerTag.assert(tagToUse, "supplied tag should get passed")
//    outerMessage.assert(messageToUse, "supplied message should get passed")
//    outerThrowable.assert(exceptionToUse, "supplied exception should get passed")
//
//}
//
///**
// * Asserts the state on the L. properties.
// */
//private fun LLogger.assertLoggingAllowedStates(
//    assertProd: Boolean,
//    assertError: Boolean,
//    assertWarning: Boolean,
//    assertDebug: Boolean,
//    optionalMessage: String = ""
//) {
//    isProductionLoggingAllowed.assert(assertProd, optionalMessage)
//    isErrorLoggingAllowed.assert(assertError, optionalMessage)
//    isWarningLoggingAllowed.assert(assertWarning, optionalMessage)
//    isDebugLoggingAllowed.assert(assertDebug, optionalMessage)
//}
//
//class LoggingLevelTest {
//
//    @Test
//    fun toStringTest() {
//        LoggingLevel.Debug.toString().assert(LoggingLevel.Debug.stringValue)
//        LoggingLevel.Production.toString().assert(LoggingLevel.Production.stringValue)
//        LoggingLevel.Warning.toString().assert(LoggingLevel.Warning.stringValue)
//        LoggingLevel.Error.toString().assert(LoggingLevel.Error.stringValue)
//    }
//}
