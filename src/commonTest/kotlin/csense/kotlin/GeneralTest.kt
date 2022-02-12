package csense.kotlin

import csense.kotlin.logger.*
import csense.kotlin.tests.assertions.*
import kotlin.test.*

class GeneralTest {

    class Unexpected {
        @Test
        fun noRelatedCause() = assertThrows<UnexpectedException>(
            testCode = {
                unexpected("message")
            },
            validateThrows = {
                it.cause.assertNull()
                it.message.assert("message")
            }
        )

        @Test
        fun relatedCause() {
            val relatedCause = Exception("myReason")
            assertThrows<UnexpectedException>(
                testCode = {
                    unexpected("message2", relatedCause)
                },
                validateThrows = {
                    it.message.assert("message2")
                    it.cause.assert(relatedCause)
                }
            )
        }

    }

    class UnexpectedWithLoggingMessage {
        @Test
        fun loggerNoRelatedCause() = assertCalled { shouldBeCalled ->
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert(UnexpectedException.unexpectedDefaultTag)
                message.assert("our message")
                ex.assertExceptionIsUnexpected()
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(
                testCode = {
                    unexpectedWithLogging(
                        message = "our message",
                        logger = loggerMethod
                    )
                }, validateThrows = {
                    it.message.assert("our message")
                    it.cause.assertNull()
                })
        }

        @Test
        fun loggerWithRelatedCause() = assertCalled { shouldBeCalled ->
            val relatedCause = IllegalArgumentException("wee")
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert(UnexpectedException.unexpectedDefaultTag)
                message.assert("our message")
                ex.assertExceptionIsUnexpected(relatedCause)
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(
                testCode = {
                    unexpectedWithLogging(
                        message = "our message",
                        logger = loggerMethod,
                        relatedCause = relatedCause
                    )
                }, validateThrows = {
                    it.message.assert("our message")
                    it.cause.assert(relatedCause)
                })
        }
    }


    class UnexpectedWithLoggingTag {
        @Test
        fun loggerNoRelatedCause() = assertCalled { shouldBeCalled ->
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Tag")
                message.assert("our message")
                ex.assertExceptionIsUnexpected()
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(
                testCode = {
                    unexpectedWithLogging(
                        tag = "Tag",
                        message = "our message",
                        logger = loggerMethod
                    )
                }, validateThrows = {
                    it.message.assert("our message")
                    it.cause.assertNull()
                })
        }

        @Test
        fun loggerWithRelatedCause() = assertCalled { shouldBeCalled ->
            val relatedCause = IllegalArgumentException("wee")
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Our tag")
                message.assert("our message")
                ex.assertExceptionIsUnexpected(relatedCause)
                shouldBeCalled()
            }
            assertThrows<UnexpectedException>(
                testCode = {
                    unexpectedWithLogging(
                        tag = "Our tag",
                        message = "our message",
                        logger = loggerMethod,
                        relatedCause = relatedCause
                    )
                }, validateThrows = {
                    it.message.assert("our message")
                    it.cause.assert(relatedCause)
                })
        }

    }

    @Test
    fun overloadResolutionShouldChooseMessageOverTagVariant() = assertCalled { shouldBeCalled ->
        val loggerMethod: LoggingFunctionType<*> = { _, message, _ ->
            message.assert("our message")
            shouldBeCalled()
        }
        assertThrows<UnexpectedException>(
            testCode = {
                unexpectedWithLogging(
                    "our message",
                    logger = loggerMethod
                )
            }, validateThrows = {
                it.message.assert("our message")
            })
    }

    class LogUnexpectedMessage {

        @Test
        fun defaultMessage() = assertCalled { shouldBeCalled ->
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert(UnexpectedException.unexpectedDefaultTag)
                message.assert(UnexpectedException.unexpectedDefaultMessage)
                ex.assertExceptionIsUnexpected()
                shouldBeCalled()
            }
            logUnexpected(logger = loggerMethod)
        }

        @Test
        fun noRelatedCause() = assertCalled { shouldBeCalled ->
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert(UnexpectedException.unexpectedDefaultTag)
                message.assert("our message")
                ex.assertExceptionIsUnexpected()
                shouldBeCalled()
            }
            logUnexpected(message = "our message", loggerMethod)
        }

        @Test
        fun withRelatedCause() = assertCalled { shouldBeCalled ->
            val relatedCause = IllegalArgumentException("wee")
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert(UnexpectedException.unexpectedDefaultTag)
                message.assert("our message")
                ex.assertExceptionIsUnexpected(relatedCause)
                shouldBeCalled()
            }
            logUnexpected(message = "our message", loggerMethod, relatedCause)
        }
    }

    class LogUnexpectedTag {

        @Test
        fun noRelatedCause() = assertCalled { shouldBeCalled ->
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Our tag")
                message.assert("our message")
                ex.assertExceptionIsUnexpected()
                shouldBeCalled()
            }
            logUnexpected("Our tag", "our message", loggerMethod)
        }

        @Test
        fun withRelatedCause() = assertCalled { shouldBeCalled ->
            val relatedCause = IllegalArgumentException("wee")
            val loggerMethod: LoggingFunctionType<*> = { tag, message, ex ->
                tag.assert("Our tag")
                message.assert("our message")
                ex.assertExceptionIsUnexpected(relatedCause)
                shouldBeCalled()
            }
            logUnexpected("Our tag", "our message", loggerMethod, relatedCause)
        }
    }

    companion object {
        fun Throwable?.assertExceptionIsUnexpected(relatedCause: Throwable? = null) {
            assertNotNull()
            assertIs<UnexpectedException>()
            if (relatedCause != null) {
                cause.assert(relatedCause)
            } else {
                cause.assertNull()
            }
        }
    }
}