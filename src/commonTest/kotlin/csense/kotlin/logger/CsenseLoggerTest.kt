package csense.kotlin.logger

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CsenseLoggerTest {

    @Test
    fun defaultSensitiveLoggingShouldBeDisabled() {
        val csenseLogger = CsenseLogger()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
    }

    @Test
    fun enableSensitiveLogging() {
        val csenseLogger = CsenseLogger()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
        csenseLogger.enableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertTrue()
    }


    @Test
    fun disableSensitiveLogging() {
        val csenseLogger = CsenseLogger()
        csenseLogger.enableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertTrue()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
    }

    @Test
    fun isSensitiveLoggingEnabled() {
        val csenseLogger = CsenseLogger()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
        csenseLogger.enableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertTrue()
        csenseLogger.disableSensitiveLogging()
        csenseLogger.isSensitiveLoggingEnabled.assertFalse()
    }

}