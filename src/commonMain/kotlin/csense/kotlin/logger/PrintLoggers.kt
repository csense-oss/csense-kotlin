package csense.kotlin.logger

import kotlinx.coroutines.*


@OptIn(DelicateCoroutinesApi::class)
public fun CsenseLogger.printLogsToConsole(scope: CoroutineScope = GlobalScope): Job = scope.launch {
    allLoggers.collect {
        println(it.toFullLogString())
    }
}

@OptIn(DelicateCoroutinesApi::class)
public fun CsenseLogger.printLogsToConsoleAnsiColored(scope: CoroutineScope = GlobalScope): Job = scope.launch {
    allLoggers.collect {
        println(it.toFullColoredLog())
    }
}
