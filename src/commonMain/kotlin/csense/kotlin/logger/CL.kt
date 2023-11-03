package csense.kotlin.logger

import csense.kotlin.logger.factory.*
import csense.kotlin.logger.loggers.*
import csense.kotlin.logger.models.*
import kotlinx.coroutines.flow.*

public object CL {
    private val logger = SensitivityLogMessageLogger(
        SharedFlowLogMessageLogger(maxStoredLogMessages = 100)
    )

    public var isSensitiveLoggingEnabled: Boolean
        get() = logger.isSensitiveLoggingEnabled
        set(newValue) {
            logger.isSensitiveLoggingEnabled = newValue
        }

    public fun disableSensitiveLogging(): Unit = logger.disableSensitiveLogging()

    public fun enableSensitiveLogging(): Unit = logger.enableSensitiveLogging()

    public val debugLoggers: Flow<LogMessage.Debug> = logger.allLoggers.filterIsInstance()

    public val warningLoggers: Flow<LogMessage.Warning> = logger.allLoggers.filterIsInstance()

    public val errorLoggers: Flow<LogMessage.Error> = logger.allLoggers.filterIsInstance()

    public val logDebug: CsenseLoggerLogFactory by lazy {
        createFactoryFor(createLogMessage = LogMessage::Debug)
    }

    public val logWarning: CsenseLoggerLogFactory by lazy {
        createFactoryFor(createLogMessage = LogMessage::Warning)
    }
    public val logError: CsenseLoggerLogFactory by lazy {
        createFactoryFor(createLogMessage = LogMessage::Error)
    }

    private fun createFactoryFor(
        createLogMessage: CreateLogMessageFunction
    ): CsenseLoggerLogFactory {
        return CsenseLoggerLogFactory(
            log = logger::log,
            isSensitiveLoggingEnabled = ::isSensitiveLoggingEnabled,
            createLogMessage = createLogMessage
        )
    }

    public const val sensitiveLoggingTag: String = "CsenseLogger"
    public const val sensitiveLoggingEnabledMessage: String = "Sensitive logging enabled"
    public const val sensitiveLoggingDisabledMessage: String = "Sensitive logging enabled"
}