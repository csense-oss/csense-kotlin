package org.csenseoss.kotlin.logger.extensions

import org.csenseoss.kotlin.logger.models.*
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.*
import kotlin.test.*

class LogMessageTest {
    @Test
    fun containsSensitiveInformation() {
        insensitiveMessage().containsSensitiveInformation().assertFalse()
        sensitiveMessage().containsSensitiveInformation().assertTrue()
        sensitiveMessageInInsensitive().containsSensitiveInformation().assertTrue()
    }

    @Test
    fun doesNotContainSensitiveInformation() {
        insensitiveMessage().doesNotContainSensitiveInformation().assertTrue()
        sensitiveMessage().doesNotContainSensitiveInformation().assertFalse()
        sensitiveMessageInInsensitive().doesNotContainSensitiveInformation().assertFalse()
    }

    private fun sensitiveMessage(): LogMessage = LogMessage.Debug(
        tag = "tag",
        message = LogMessageFormat.SensitiveValues(message = "test"),
        exception = null
    )

    private fun insensitiveMessage(): LogMessage = LogMessage.Debug(
        tag = "tag",
        message = LogMessageFormat.InsensitiveValues(
            message = "test",
            placeholders = arrayOf(),
            expectedSensitivity = LogSensitivity.Insensitive
        ),
        exception = null
    )

    private fun sensitiveMessageInInsensitive(): LogMessage = LogMessage.Debug(
        tag = "tag",
        message = LogMessageFormat.InsensitiveValues(
            message = "test",
            placeholders = arrayOf(),
            expectedSensitivity = LogSensitivity.Sensitive
        ),
        exception = null
    )
}