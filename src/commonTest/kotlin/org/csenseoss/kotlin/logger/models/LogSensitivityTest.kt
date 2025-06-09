package org.csenseoss.kotlin.logger.models

import org.csenseoss.kotlin.tests.assertions.primitives.boolean.assertFalse
import org.csenseoss.kotlin.tests.assertions.primitives.boolean.assertTrue
import kotlin.test.Test

class LogSensitivityTest {
    @Test
    fun isSensitive(){
        LogSensitivity.Sensitive.isSensitive().assertTrue()
        LogSensitivity.Insensitive.isSensitive().assertFalse()
    }
    @Test
    fun isNotSensitive(){
        LogSensitivity.Sensitive.isNotSensitive().assertFalse()
        LogSensitivity.Insensitive.isNotSensitive().assertTrue()
    }

}