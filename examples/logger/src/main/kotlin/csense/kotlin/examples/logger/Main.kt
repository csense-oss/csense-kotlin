package csense.kotlin.examples.logger

import csense.kotlin.extensions.coroutines.*
import csense.kotlin.extensions.coroutines.coroutineScope.*
import csense.kotlin.extensions.general.*
import csense.kotlin.extensions.throwable.*
import csense.kotlin.general.coroutine.*
import csense.kotlin.logger.*
import csense.kotlin.logger.extensions.*
import csense.kotlin.logger.models.*
import csense.kotlin.logger.operators.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

//Showcasing some examples of logging
suspend fun main(): Unit = withCurrentCoroutineScope {
    //Tells the logger to ALLOW sensitive logging. this should not be enabled in prod
//    CL.enableSensitiveLogging()
    //Prints the given logs (all levels) to the console (using println)
    //CL.printLogsToConsole()
    //uses ANSI colors (works in various terminals) to print colored output (using println)
//    CL.printLogsToConsoleAnsiColored()


    //an example on how to process logs e.g. for sending to a server
    sendNonSensitiveInformationToServer()

    //an example on how to log the stacktrace correctly.
//    logToConsoleSensitiveStacktrace()

    // an example on how to cache previous log statements
//    logToConsoleWithPreviousLogs()

    delay(500)
    MyClass.someFun()
//    generateSingleExceptionSensitiveLogMessage()
//    delay(50)
//    generateSingleExceptionSensitiveLogMessage()
//    delay(50)
//    generateSingleExceptionSensitiveLogMessage()

//    generateSingleExceptionInsensitiveLogMessage()
//    generateSomeSocialSecurityNumberLogsForever().join()
    generateSomeMixedLogsForever().join()

    //pause execution enough for processing to occur
    delay(1000)

}

object MyClass {

    fun someFun() {
        log.debug("this should not be called", sensitivity = LogSensitivity.Insensitive)
    }
}

fun generateSingleExceptionSensitiveLogMessage() {
    tryAndLog<Unit>(logger = CL::logDebug) { throw RuntimeException("didThrow at ${System.currentTimeMillis()}") }
}

fun generateSingleExceptionInsensitiveLogMessage() {
    tryAndLog<Unit>(
        logger = CL::logDebug,
        sensitivity = LogSensitivity.Insensitive
    ) { throw RuntimeException("some non personal information") }
}

fun CoroutineScope.generateSomeSocialSecurityNumberLogsForever() = launchDefault {
    while (true) {
        CL.logDebug(
            tag = "MyTag",
            message = "MyMessage using Person identifier {}",
            placeholders = arrayOf("000000-0000"),
            exception = RuntimeException()
        )
        delay(3000)
    }
}

fun CoroutineScope.generateSomeMixedLogsForever() = launchDefault {
    while (true) {
        log.debug(
            "Message {} {}",
            "someMessage", "mis",
            exception = RuntimeException("sensitive information potentially"),
            sensitivity = LogSensitivity.Sensitive
        )
        CL.logDebug(
            "FavoriteFood",
            "NonPersonal info: meal type={}",
            "meat",
            sensitivity = LogSensitivity.Insensitive,
            //TODO consideration: should be cause since that is the "most" normal and meaningful name for this parameter?
            exception = RuntimeException()
        )
        delay(3000)
    }
}

fun CoroutineScope.logToConsoleWithPreviousLogs() = launchDefault {
    val sharedIn = CL.debugLoggers.shareIn(
        scope = this,
        started = SharingStarted.Eagerly,
        replay = 3
    )
    sharedIn.collect {
        println("current log: ${it.toFullColoredLog()}. There are currently ${sharedIn.replayCache.size} previous logs available, which are : ")
        sharedIn.replayCache.forEach { replayLog ->
            println("\t - ${replayLog.toFullColoredLog()}")
        }
    }
}

fun CoroutineScope.logToConsoleSensitiveStacktrace() = launchDefault {
    CL.allLoggers.filter {
        it.exception != null
    }.collect {
        val sensitiveStackTrace = it.exception?.toSensitiveStackTraceString().orIfNull("-")
        println("Got log with stacktrace: $sensitiveStackTrace")
    }
}

fun CoroutineScope.sendNonSensitiveInformationToServer() = launchDefault {
    CL.allLoggers.filter {
        it.doesNotContainSensitiveInformation()
    }.collect {
        println("Sending insensitive log: ${it.toFullColoredLog()}")
        delay(1000) //simulate sending
    }
}
