package csense.kotlin.logger.loggers

import csense.kotlin.logger.models.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

public open class SharedFlowLogMessageLogger(
    maxStoredLogMessages: Int = 100
) : LogMessageLoggerInterface {
    private val _loggers: MutableSharedFlow<LogMessage> = MutableSharedFlow(
        extraBufferCapacity = maxStoredLogMessages,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    public override val allLoggers: Flow<LogMessage> = _loggers

    public override fun log(message: LogMessage) {
        _loggers.tryEmit(message)
    }
}

