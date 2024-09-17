package org.csenseoss.kotlin.logger.extensions

import kotlinx.coroutines.*
import org.csenseoss.kotlin.logger.loggers.*
import org.csenseoss.kotlin.logger.models.*


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