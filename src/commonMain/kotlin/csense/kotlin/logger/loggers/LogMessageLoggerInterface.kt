package csense.kotlin.logger.loggers

import csense.kotlin.logger.models.*
import kotlinx.coroutines.flow.*

public interface LogMessageLoggerInterface {
    public val allLoggers: Flow<LogMessage>
    public fun log(message: LogMessage)
}