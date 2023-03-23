package csense.kotlin.logger.extensions

import csense.kotlin.logger.*
import csense.kotlin.logger.models.*
import kotlinx.coroutines.*


@OptIn(DelicateCoroutinesApi::class)
public fun CsenseLogger.printLogsToConsole(scope: CoroutineScope = GlobalScope): Job = scope.launch {
    allLoggers.collect { it: LogMessage ->
        println(it.toString())
    }
}

@OptIn(DelicateCoroutinesApi::class)
public fun CsenseLogger.printLogsToConsoleAnsiColored(scope: CoroutineScope = GlobalScope): Job = scope.launch {
    allLoggers.collect { it: LogMessage ->
        println(it.toFullColoredLog())
    }
}
