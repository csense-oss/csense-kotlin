package csense.kotlin.logger.loggers

import csense.kotlin.extensions.mapping.*
import csense.kotlin.logger.*
import csense.kotlin.logger.models.*

public class SensitivityLogMessageLogger(
    private val logger: LogMessageLoggerInterface
) : LogMessageLoggerInterface by logger {

    public var isSensitiveLoggingEnabled: Boolean = false
        set(newValue) {
            field = newValue
            val logMessage: String = newValue.map(
                ifTrue = CL.sensitiveLoggingEnabledMessage,
                ifFalse = CL.sensitiveLoggingDisabledMessage
            )
            CL.logDebug(
                tag = CL.sensitiveLoggingTag,
                message = logMessage,
                exception = null,
                sensitivity = LogSensitivity.Insensitive
            )
        }

    public fun disableSensitiveLogging() {
        isSensitiveLoggingEnabled = false
    }

    public fun enableSensitiveLogging() {
        isSensitiveLoggingEnabled = true
    }

}