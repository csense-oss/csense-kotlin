package csense.kotlin.logger

import csense.kotlin.tests.assertions.*
import kotlin.test.*

class CLTest {

    @Test
    fun testCL() {

        fun defaultSensitiveLoggingShouldBeDisabled() {
            CL.isSensitiveLoggingEnabled.assertFalse()
        }

        fun enableSensitiveLogging() {
            CL.disableSensitiveLogging()
            CL.isSensitiveLoggingEnabled.assertFalse()
            CL.enableSensitiveLogging()
            CL.isSensitiveLoggingEnabled.assertTrue()
        }

        fun disableSensitiveLogging() {
            CL.enableSensitiveLogging()
            CL.isSensitiveLoggingEnabled.assertTrue()
            CL.disableSensitiveLogging()
            CL.isSensitiveLoggingEnabled.assertFalse()
        }

        fun isSensitiveLoggingEnabled() {
            CL.disableSensitiveLogging()
            CL.isSensitiveLoggingEnabled.assertFalse()
            CL.enableSensitiveLogging()
            CL.isSensitiveLoggingEnabled.assertTrue()
            CL.disableSensitiveLogging()
            CL.isSensitiveLoggingEnabled.assertFalse()
        }

        defaultSensitiveLoggingShouldBeDisabled()
        enableSensitiveLogging()
        disableSensitiveLogging()
        isSensitiveLoggingEnabled()
    }

}