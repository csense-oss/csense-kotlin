package csense.kotlin

import csense.kotlin.logger.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class GeneralTest {
    @Test
    fun unexpected() {
        assertThrows<UnexpectedException> {
            unexpected("message")
        }
    }

    @Test
    fun unexpectedWithLoggingMessage() = assertCalled { shouldBeCalled ->

        val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
            tag.assert("Unexpected")
            message.assert("our message")
            ex.assertNotNull()
            ex.assertIs<UnexpectedException>()
            shouldBeCalled()
        }
        assertThrows<UnexpectedException> {
            unexpectedWithLogging(message = "our message", loggerMethod)
        }
    }

    @Test
    fun unexpectedWithLoggingTag() = assertCalled { shouldBeCalled ->

        val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
            tag.assert("Our Tag")
            message.assert("our message")
            ex.assertNotNull()
            ex.assertIs<UnexpectedException>()
            shouldBeCalled()
        }
        assertThrows<UnexpectedException> {
            unexpectedWithLogging(tag = "Our Tag", message = "our message", loggerMethod)
        }
    }
}