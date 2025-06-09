package org.csenseoss.kotlin.extensions.exceptions

import org.csenseoss.kotlin.logger.models.*
import org.csenseoss.kotlin.logger.operators.*
import org.csenseoss.kotlin.tests.assertions.collections.array.generic.*
import org.csenseoss.kotlin.tests.assertions.comparable.*
import org.csenseoss.kotlin.tests.assertions.general.*
import org.junit.jupiter.api.*

//TODO consider after migrating to "log" namespace
class JvmTryAndLogTest {

    @Nested
    class TryAndLogClazz {
        @Test
        fun callingClass() = assertCalled { shouldBeCalled ->
            val logger: CLLogFunction = CLLogFunction { tag, message, placeholders, exception, sensitivity ->
                tag.assert(this::class.simpleName)
                message.assert("message")
                placeholders.assert(emptyArray())
                exception.assertIs<Error>()
                exception.message.assert("1234")
                sensitivity.assert(LogSensitivity.Sensitive)
                shouldBeCalled()
            }
            tryAndLog(this::class.java, "message", logger = logger, sensitivity = LogSensitivity.Sensitive) {
                throw Error("1234")
            }
        }

    }

    class TryAndLogKClazz {
        @Test
        fun callingClass() = assertCalled { shouldBeCalled ->
            val logger: CLLogFunction = CLLogFunction { tag, message, placeholders, exception, sensitivity ->
                tag.assert(this::class.simpleName)
                message.assert("message")
                placeholders.assert(emptyArray())
                exception.assertIs<Error>()
                exception.message.assert("1234")
                sensitivity.assert(LogSensitivity.Sensitive)
                shouldBeCalled()
            }
            tryAndLog(this::class, "message", logger = logger, sensitivity = LogSensitivity.Sensitive) {
                throw Error("1234")
            }
        }
    }
}