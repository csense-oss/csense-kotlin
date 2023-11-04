package csense.kotlin.logger

import csense.kotlin.logger.models.*
import csense.kotlin.logger.operators.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class LoggerTests {

    class TryAndLogTag {


        @Test
        fun nonThrowingShouldReturnValue() {
            val result = tryAndLog(logger = { _, _, _, _, _ ->

            }) { 42 }
            result.assert(42)
        }


        @Test
        fun throwingShouldReturnNull() {
            val result = tryAndLog<Int>(logger = { _, _, _, _, _ ->

            }) { throw RuntimeException("my bad") }
            result.assertNull("got an exception...")
        }

        @Test
        fun shouldCallLoggerWhenFails() = assertCalled { shouldBeCalled: () -> Unit ->

            val loggerTag = "tag"
            val loggerMessage = "message"
            val loggerPlaceholders: Array<String> = arrayOf("myPlaceHolder")
            val throwingException = Exception("test")
            val loggerSensitivity = LogSensitivity.Insensitive

            val failed = tryAndLog<String>(
                tag = loggerTag,
                message = loggerMessage,
                placeholders = loggerPlaceholders,
                sensitivity = loggerSensitivity,
                logger = { tag, message, placeholders, exception, sensitivity ->
                    tag.assert(loggerTag)
                    message.assert(loggerMessage)
                    placeholders.assert(loggerPlaceholders)
                    exception.assert(throwingException)
                    sensitivity.assert(loggerSensitivity)
                    shouldBeCalled()
                }
            ) {
                throw throwingException
            }
            failed.assertNull()
        }

        @Test
        fun shouldIgnoreLoggerWhenSucceeds() {
            val works = tryAndLog(logger = { _, _, _, _, _ ->
                failTest("should not get called")
            }) {
                42
            }
            works.assert(42)
        }

        @Test
        fun tryAndLog1() {

            val works = tryAndLog("title", "message", logger = { _, _, _, _, _ ->
                failTest("should not get called")
            }) {
                42
            }
            works.assertNotNull()

            val exception = Exception("test")
            var didCallLogger = false

            val fails: String? = tryAndLog<String>(
                tag = "title",
                message = "message",
                placeholders = arrayOf("myPlaceHolder"),
                sensitivity = LogSensitivity.Sensitive,
                logger = { tag, message, _, loggerException, _ ->
                    didCallLogger = true
                    tag.assert("title")
                    message.assert("message")
                    loggerException.assertNotNull()
                }) {
                throw exception
            }
            didCallLogger.assertTrue()
            fails.assertNull()

        }
    }
}

