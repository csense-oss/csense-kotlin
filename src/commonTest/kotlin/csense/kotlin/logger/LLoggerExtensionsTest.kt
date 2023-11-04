//package csense.kotlin.logger
//
//import csense.kotlin.logger.LLoggerExtensions.createPrintLoggerFor
//import csense.kotlin.logger.LLoggerExtensions.createPrintLoggerForAnsiCodes
//import csense.kotlin.logger.LLoggerExtensions.formatMessage
//import csense.kotlin.tests.assertions.*
//import kotlin.test.*
//
//@Suppress("unused")
//class LLoggerExtensionsTest {
//
//    class FormatMessage {
//        @Test
//        fun formatContainsAll() {
//            val fullMessage: String = formatMessage(
//                LoggingLevel.Production, "tag", "message", RuntimeException()
//            )
//            fullMessage.assertContainsInOrder("Production", "[tag]", "message", "RuntimeException")
//        }
//
//        @Test
//        fun handlesMissingException() {
//            val fullMessage: String = formatMessage(
//                LoggingLevel.Warning, "tag", "message", exception = null
//            )
//            fullMessage.assertContainsInOrder("Warning", "[tag]", "message")
//            fullMessage.assertContainsNot("null", message = "in case the exception gets printed directly etc.")
//        }
//    }
//
//    class CreatePrintLoggerFor {
//        @Test
//        fun respectsAllParameters() {
//            assertCallsPrintWith(LoggingLevel.Production, "prod1", "prod message 2", RuntimeException())
//            assertCallsPrintWith(LoggingLevel.Production, "prod2", "prod message 2", exception = null)
//
//            assertCallsPrintWith(LoggingLevel.Error, "error1", "error message 1", RuntimeException())
//            assertCallsPrintWith(LoggingLevel.Error, "error2", "error message 2", exception = null)
//
//            assertCallsPrintWith(LoggingLevel.Warning, "warn1", "warning message 1", RuntimeException())
//            assertCallsPrintWith(LoggingLevel.Warning, "warn2", "warning message 2", exception = null)
//
//            assertCallsPrintWith(LoggingLevel.Debug, "debug1", "debug message1", RuntimeException())
//            assertCallsPrintWith(LoggingLevel.Debug, "debug2", "debug message2", exception = null)
//        }
//
//        private fun assertCallsPrintWith(
//            level: LoggingLevel,
//            tag: String,
//            message: String,
//            exception: Throwable?
//        ): Unit = assertCalled { shouldBeCalled: () -> Unit ->
//            val logger = createPrintLoggerFor(
//                formatter = createAssertingFormatter(
//                    level = level,
//                    tag = tag,
//                    message = message,
//                    exception = exception
//                ),
//                level = level,
//                printFunc = {
//                    assertPrintFunction(
//                        printMessage = it,
//                        level = level,
//                        tag = tag,
//                        message = message,
//                        exception = exception
//                    )
//                    shouldBeCalled()
//                })
//            logger(tag, message, exception)
//        }
//
//
//    }
//
//    class CreatePrintLoggerForAnsiCodes {
//
//        @Test
//        fun level() = assertCalled {shouldBeCalled: () -> Unit ->
//            val tag = "tag"
//            val message = "message"
//            val exception = IllegalArgumentException()
//            val logger = createPrintLoggerForAnsiCodes(
//                formatter = createAssertingFormatter(
//                    level = LoggingLevel.Production,
//                    tag = tag,
//                    message = message,
//                    exception = exception
//                ),
//                level = LoggingLevel.Production,
//                startCode = "startCode",
//                endCode = "endCode",
//                printFunc = { printMessage ->
//                    printMessage.assertContainsInOrder(
//                        "startCode",
//                        LoggingLevel.Production.stringValue,
//                        "[$tag]",
//                        ourFormatterMark,
//                        message,
//                        exception::class.simpleName!!,
//                        "endCode"
//                    )
//                    shouldBeCalled()
//                }
//            )
//            logger(tag, message, exception)
//        }
//
//
//    }
//
//    companion object {
//        fun createAssertingFormatter(
//            level: LoggingLevel,
//            tag: String,
//            message: String,
//            exception: Throwable?
//        ): FunctionLoggerFormatter {
//            return { formatterLevel: LoggingLevel, formatterTag: String, formatterMessage: String, formatterException: Throwable? ->
//                formatterLevel.assert(level)
//                formatterTag.assert(tag)
//                formatterMessage.assert(message)
//                if (exception == null) {
//                    formatterException.assertNull()
//                } else {
//                    formatterException.assert(exception)
//                }
//                "$level - [$tag] $ourFormatterMark $message ${exception?.stackTraceToString() ?: ""}"
//            }
//        }
//
//        fun assertPrintFunction(
//            printMessage: String,
//            level: LoggingLevel,
//            tag: String,
//            message: String,
//            exception: Throwable?
//        ) {
//            if (exception != null) {
//                printMessage.assertContainsInOrder(
//                    level.toString(),
//                    "[$tag]",
//                    ourFormatterMark,
//                    message,
//                    exception::class.simpleName!!
//                )
//            } else {
//                printMessage.assertContainsInOrder(level.toString(), "[$tag]", ourFormatterMark, message)
//                printMessage.assertContainsNot("null")
//            }
//        }
//
//        private const val ourFormatterMark = "-*-"
//    }
//}