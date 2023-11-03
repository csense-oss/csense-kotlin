package csense.kotlin.logger.extensions

import csense.kotlin.logger.loggers.*
import csense.kotlin.logger.models.*
import kotlinx.coroutines.*


@OptIn(DelicateCoroutinesApi::class)
public fun SharedFlowLogMessageLogger.printLogsToConsole(scope: CoroutineScope = GlobalScope): Job = scope.launch {
    allLoggers.collect { it: LogMessage ->
        println(it.toString())
    }
}

@OptIn(DelicateCoroutinesApi::class)
public fun SharedFlowLogMessageLogger.printLogsToConsoleAnsiColored(scope: CoroutineScope = GlobalScope): Job = scope.launch {
    allLoggers.collect { it: LogMessage ->
        println(it.toFullColoredLog())
    }
}
