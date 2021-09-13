package csense.kotlin

import csense.kotlin.logger.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class GeneralTest {

    class Unexpected {
        @Test
        fun noReason() = assertThrows<UnexpectedException>(
            action = {
                unexpected("message")
            },
            validateThrows = {
                it.cause.assertNull()
                it.message.assertNotNullAndEquals("message")
            }
        )

        @Test
        fun reason() {
            val relatedCause = Exception("myReason")
            assertThrows<UnexpectedException>(
                action = {
                    unexpected("message2", relatedCause)
                },
                validateThrows = {
                    it.message.assertNotNullAndEquals("message2")
                    it.cause.assertNotNullAndEquals(relatedCause)
                }
            )
        }

    }

    class UnexpectedWithLoggingMessage {
        @Test
        fun loggerNoCause() = assertCalled { shouldBeCalled ->
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Unexpected")
                message.assert("our message")
                ex.assertNotNull()
                ex.assertIs<UnexpectedException>()
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(action = {
                unexpectedWithLogging(
                    message = "our message",
                    logger = loggerMethod
                )
            }, validateThrows = {
                it.message.assertNotNullAndEquals("our message")
                it.cause.assertNull()
            })
        }

        @Test
        fun loggerWithCause() = assertCalled { shouldBeCalled ->
            val relatedCause = IllegalArgumentException("wee")
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Unexpected")
                message.assert("our message")
                ex.assertNotNull()
                ex.assertIs<UnexpectedException>()
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(action = {
                unexpectedWithLogging(
                    message = "our message",
                    logger = loggerMethod,
                    relatedCause = relatedCause
                )
            }, validateThrows = {
                it.message.assertNotNullAndEquals("our message")
                it.cause.assertNotNullAndEquals(relatedCause)
            })
        }
    }


    class UnexpectedWithLoggingTag {
        @Test
        fun loggerNoCause() = assertCalled { shouldBeCalled ->
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Tag")
                message.assert("our message")
                ex.assertNotNull()
                ex.assertIs<UnexpectedException>()
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(action = {
                unexpectedWithLogging(
                    tag = "Tag",
                    message = "our message",
                    logger = loggerMethod
                )
            }, validateThrows = {
                it.message.assertNotNullAndEquals("our message")
                it.cause.assertNull()
            })
        }

        @Test
        fun loggerWithCause() = assertCalled { shouldBeCalled ->
            val relatedCause = IllegalArgumentException("wee")
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Our tag")
                message.assert("our message")
                ex.assertNotNull()
                ex.assertIs<UnexpectedException>()
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(action = {
                unexpectedWithLogging(
                    tag = "Our tag",
                    message = "our message",
                    logger = loggerMethod,
                    relatedCause = relatedCause
                )
            }, validateThrows = {
                it.message.assertNotNullAndEquals("our message")
                it.cause.assertNotNullAndEquals(relatedCause)
            })
        }
    }
}